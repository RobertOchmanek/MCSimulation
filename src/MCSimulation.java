import formulas.ProbabilityFormulas;
import lattice.LatticeContainer;
import lattice.LatticeParametersBuilder;
import main.Simulation;
import metrics.ChangeFrequencyMetric;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static formulas.EnergyFormulas.*;

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

        ChangeFrequencyMetric frequencyMetric = new ChangeFrequencyMetric();

        for (int step = 0; step < steps; ++step) {

            int[][] modifiedLattice = latticeContainer.copy();
            LatticeContainer modifiedContainer;
            double deltaE;

            int modifiedRow = ThreadLocalRandom.current().nextInt(0, latticeContainer.getSize());
            int modifiedCol = ThreadLocalRandom.current().nextInt(0, latticeContainer.getSize());

            if (frequencyMetric.acceptedChangeFrequency() > 0.5) {
                //Big change - add/subtract 1 from many magnets at once or add/subtract 2 (or bigger value) from random magnet
                //Make lattice copy and apply change on lattice
                int modifier = ThreadLocalRandom.current().nextInt(0, 2) == 0 ? -2 : 2;

                if (modifiedLattice[modifiedRow][modifiedCol] + modifier < 0) {
                    modifiedLattice[modifiedRow][modifiedCol] = (latticeContainer.getNumAngles()) + (modifiedLattice[modifiedRow][modifiedCol] - modifier);
                } else if (modifiedLattice[modifiedRow][modifiedCol] + modifier >= latticeContainer.getNumAngles()) {
                    modifiedLattice[modifiedRow][modifiedCol] = (modifiedLattice[modifiedRow][modifiedCol] + modifier) - latticeContainer.getNumAngles();
                } else {
                    modifiedLattice[modifiedRow][modifiedCol] += modifier;
                }

                modifiedLattice[modifiedRow][modifiedCol] += modifier;

                modifiedContainer = new LatticeContainer(modifiedLattice, latticeContainer.getNumAngles());
                deltaE = bigChangeDelta(modifiedContainer, latticeContainer, influences, neighbourLevels, externalFieldAngle);
            } else {
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
            }

            //Use deltaE to check if change is accepted
            if (ProbabilityFormulas.changeAccepted(deltaE, TkB, formula)) {
                //If accepted modified copy becomes the current lattice
                latticeContainer = modifiedContainer;
            }
        }
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
