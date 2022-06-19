package simulation.executor;

import lattice.LatticeContainer;
import lattice.LatticeParameters;
import simulation.SimulationParameters;
import simulation.StepParameters;

import static formulas.EnergyFormulas.totalSystemEnergy;

public class MetricsStepExecutor implements SimulationExecutor {

    private final SimulationExecutor wrappedExecutor;
    private double attemptedChanges = 0;
    private double acceptedChanges = 0;
    private double energyChange;

    public MetricsStepExecutor(SimulationExecutor wrappedExecutor) {
        this.wrappedExecutor = wrappedExecutor;
    }

    @Override
    public StepParameters executeStep(int stepNumber) {

        if (stepNumber == 0) {
            energyChange = totalSystemEnergy(getLatticeContainer(), getSimulationParameters());
        }

        StepParameters stepParameters = wrappedExecutor.executeStep(stepNumber);

        if (stepParameters.changeAccepted()) {
            ++acceptedChanges;
            energyChange += stepParameters.getDeltaE();
        }
        ++attemptedChanges;

        return stepParameters;
    }

    @Override
    public LatticeParameters executionSummary() {
        System.out.println("Accepted changes frequency: " + acceptedChanges / attemptedChanges);
        System.out.println("Total system energy: " + (energyChange / (getLatticeContainer().getSize() * getLatticeContainer().getSize())));

        return wrappedExecutor.executionSummary();
    }

    @Override
    public SimulationParameters getSimulationParameters() {
        return wrappedExecutor.getSimulationParameters();
    }

    @Override
    public LatticeContainer getLatticeContainer() {
        return wrappedExecutor.getLatticeContainer();
    }
}
