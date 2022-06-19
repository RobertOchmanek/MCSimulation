package simulation.executor;

import lattice.LatticeContainer;
import lattice.LatticeParameters;
import lattice.LatticeParametersBuilder;
import simulation.SimulationParameters;
import simulation.StepParameters;

import java.util.concurrent.ThreadLocalRandom;

import static formulas.EnergyFormulas.*;
import static formulas.OrderFormulas.nearestNeighboursOrder;
import static formulas.OrderFormulas.systemOrder;
import static formulas.ProbabilityFormulas.changeAccepted;

public class MonteCarloSimulationExecutor implements SimulationExecutor {

    //Cycle defines number of iterations after which change frequency is calculated
    private static final int CYCLE = 10;
    private final SimulationParameters simulationParameters;
    private LatticeContainer latticeContainer;
    private int numChanged = 1;
    private int acceptedChanges = 0;

    public MonteCarloSimulationExecutor(SimulationParameters simulationParameters, LatticeContainer latticeContainer) {
        this.simulationParameters = simulationParameters;
        this.latticeContainer = latticeContainer;
    }
    @Override
    public StepParameters executeStep(int stepNumber) {

        int[][] modifiedLattice = latticeContainer.copy();
        LatticeContainer modifiedContainer;
        double deltaE;

        if (numChanged == 1) { //Modify single magnet
            int[] modifiedIndexes = smallChange(modifiedLattice);

            modifiedContainer = new LatticeContainer(modifiedLattice);
            deltaE = smallChangeDelta(modifiedContainer, latticeContainer, simulationParameters, modifiedIndexes[0], modifiedIndexes[1]);

        } else { //Modify multiple magnets
            for (int i = 0; i < numChanged; ++i) {
                smallChange(modifiedLattice);
            }

            modifiedContainer = new LatticeContainer(modifiedLattice);
            deltaE = bigChangeDelta(modifiedContainer, latticeContainer, simulationParameters);
        }

        boolean changeAccepted = changeAccepted(deltaE, simulationParameters.getTkB(), simulationParameters.getProbabilityFormula());

        //Use deltaE to check if change is accepted
        if (changeAccepted) {
            //If accepted modified copy becomes the current lattice
            latticeContainer = modifiedContainer;
            ++acceptedChanges;
        }

        //After each cycle check if changes threshold was suppressed
        if (stepNumber != 0 && stepNumber % CYCLE == 0) {

            //If threshold was suppressed increment number of modified magnets
            //Threshold determines whether number of changed magnets should be increased or decreased
            if (acceptedChanges >= CYCLE / 2) {
                ++numChanged;
            }
            acceptedChanges = 0;
        }

        return new StepParameters(changeAccepted, deltaE);
    }

    private int[] smallChange(int[][] modifiedLattice) {
        int modifiedRow = ThreadLocalRandom.current().nextInt(0, latticeContainer.getSize());
        int modifiedCol = ThreadLocalRandom.current().nextInt(0, latticeContainer.getSize());

        //Small change - add/subtract 1 from random magnet
        //Make lattice copy and apply change on lattice
        int modifier = ThreadLocalRandom.current().nextInt(0, 2) == 0 ? -1 : 1;

        if (modifier == -1 && modifiedLattice[modifiedRow][modifiedCol] == 0) {
            modifiedLattice[modifiedRow][modifiedCol] = simulationParameters.getNumAngles() - 1;
        } else if (modifier == 1 && modifiedLattice[modifiedRow][modifiedCol] == simulationParameters.getNumAngles() - 1) {
            modifiedLattice[modifiedRow][modifiedCol] = 0;
        } else {
            modifiedLattice[modifiedRow][modifiedCol] += modifier;
        }

        int[] modifiedIndexes = new int[2];
        modifiedIndexes[0] = modifiedRow;
        modifiedIndexes[1] = modifiedCol;

        return modifiedIndexes;
    }

    @Override
    public LatticeParameters executionSummary() {
        return new LatticeParametersBuilder()
                .setLattice(latticeContainer.copy())
                .setTotalEnergy(totalSystemEnergy(latticeContainer, simulationParameters))
                .setOrderParameter(systemOrder(latticeContainer, simulationParameters))
                .setNearestNeighbourOrder(nearestNeighboursOrder(latticeContainer, simulationParameters))
                .build();
    }

    @Override
    public SimulationParameters getSimulationParameters() {
        return simulationParameters;
    }

    @Override
    public LatticeContainer getLatticeContainer() {
        return latticeContainer;
    }
}
