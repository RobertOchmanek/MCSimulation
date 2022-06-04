import formulas.ProbabilityFormulas;
import lattice.LatticeContainer;
import lattice.LatticeParametersBuilder;
import main.Simulation;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static formulas.EnergyFormulas.*;
import static formulas.OrderFormulas.nearestNeighboursOrder;
import static formulas.OrderFormulas.systemOrder;

public class MCSimulation implements Simulation {

    private LatticeContainer latticeContainer;

    //Parameters used in simulation
    private int neighbourLevels;
    private double TkB;
    private double externalFieldAngle;
    private List<Double> influences;
    private ProbabilityFormula formula;

    public MCSimulation() {
        this.influences = new LinkedList<>();
    }

    @Override
    public void setLattice(int[][] lattice, int states) {
        this.latticeContainer = new LatticeContainer(lattice, states);
    }

    @Override
    public void setEnergyParameters(List<Double> parameters, double externalFieldAngle) {

        this.neighbourLevels = parameters.size() - 1;
        this.externalFieldAngle = externalFieldAngle;
        this.influences.addAll(parameters);
    }

    @Override
    public void setProbabilityFormula(ProbabilityFormula formula) {
        this.formula = formula;
    }

    @Override
    public void setTkB(double TkB) {
        this.TkB = TkB;
    }

    @Override
    public void executeMCSteps(int steps) {

        //Initially modify only one magnet
        int numChanged = 1;
        int acceptedChanges = 0;
        int cycle = 10;
        int threshold = cycle / 2;

        for (int step = 0; step < steps; ++step) {

            int[][] modifiedLattice = latticeContainer.copy();
            LatticeContainer modifiedContainer;
            double deltaE;

            if (numChanged == 1) {
                int modifiedRow = ThreadLocalRandom.current().nextInt(0, latticeContainer.getSize());
                int modifiedCol = ThreadLocalRandom.current().nextInt(0, latticeContainer.getSize());

                //Small change - add/subtract 1 from random magnet
                //Make lattice copy and apply change on lattice
                int modifier = ThreadLocalRandom.current().nextInt(0, 2) == 0 ? -1 : 1;

                if (modifier == -1 && modifiedLattice[modifiedRow][modifiedCol] == 0) {
                    modifiedLattice[modifiedRow][modifiedCol] = latticeContainer.getNumAngles() - 1;
                } else if (modifier == 1 && modifiedLattice[modifiedRow][modifiedCol] == latticeContainer.getNumAngles() - 1) {
                    modifiedLattice[modifiedRow][modifiedCol] = 0;
                } else {
                    modifiedLattice[modifiedRow][modifiedCol] += modifier;
                }

                modifiedContainer = new LatticeContainer(modifiedLattice, latticeContainer.getNumAngles());
                deltaE = smallChangeDelta(modifiedContainer, latticeContainer, influences, neighbourLevels, externalFieldAngle, modifiedRow, modifiedCol);

                //Use deltaE to check if change is accepted
                if (ProbabilityFormulas.changeAccepted(deltaE, TkB, formula)) {
                    //If accepted modified copy becomes the current lattice
                    latticeContainer = modifiedContainer;
                    ++acceptedChanges;
                }
            } else {
                for (int i = 0; i < numChanged; ++i) {
                    int modifiedRow = ThreadLocalRandom.current().nextInt(0, latticeContainer.getSize());
                    int modifiedCol = ThreadLocalRandom.current().nextInt(0, latticeContainer.getSize());

                    //Small change - add/subtract 1 from random magnet
                    //Make lattice copy and apply change on lattice
                    int modifier = ThreadLocalRandom.current().nextInt(0, 2) == 0 ? -1 : 1;

                    if (modifier == -1 && modifiedLattice[modifiedRow][modifiedCol] == 0) {
                        modifiedLattice[modifiedRow][modifiedCol] = latticeContainer.getNumAngles() - 1;
                    } else if (modifier == 1 && modifiedLattice[modifiedRow][modifiedCol] == latticeContainer.getNumAngles() - 1) {
                        modifiedLattice[modifiedRow][modifiedCol] = 0;
                    } else {
                        modifiedLattice[modifiedRow][modifiedCol] += modifier;
                    }
                }

                modifiedContainer = new LatticeContainer(modifiedLattice, latticeContainer.getNumAngles());
                deltaE = bigChangeDelta(modifiedContainer, latticeContainer, influences, neighbourLevels, externalFieldAngle);

                //Use deltaE to check if change is accepted
                if (ProbabilityFormulas.changeAccepted(deltaE, TkB, formula)) {
                    //If accepted modified copy becomes the current lattice
                    latticeContainer = modifiedContainer;
                    ++acceptedChanges;
                }
            }

            //After each cycle check if changes threshold was suppressed
            if (step != 0 && step % cycle == 0) {

                //If threshold was suppressed increment number of modified magnets
                if (acceptedChanges >= threshold) {
                    ++numChanged;
                }
                acceptedChanges = 0;
            }
        }

        //Calculate metrics
        latticeContainer.setTotalSystemEnergy(totalSystemEnergy(latticeContainer, influences, neighbourLevels, externalFieldAngle));
        latticeContainer.setTotalSystemOrder(systemOrder(latticeContainer));
        latticeContainer.setNeighboursOrder(nearestNeighboursOrder(latticeContainer));
    }

    @Override
    public LatticeParameters getState() {
        return new LatticeParametersBuilder()
                .setLattice(latticeContainer.copy())
                .setOrderParameter(latticeContainer.orderParameter())
                .setTotalEnergy(latticeContainer.totalEnergy())
                .setNearestNeighbourOrder(latticeContainer.nearestNeighbourOrder())
                .build();
    }

    public LatticeContainer getLatticeContainer() {
        return latticeContainer;
    }
}
