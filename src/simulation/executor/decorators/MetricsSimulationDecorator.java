package simulation.executor.decorators;

import lattice.LatticeParameters;
import simulation.StepParameters;
import simulation.executor.SimulationExecutor;

import static formulas.EnergyFormulas.totalSystemEnergy;

public class MetricsSimulationDecorator extends SimulationDecorator {

    private double attemptedChanges = 0;
    private double acceptedChanges = 0;
    private double energyChange;

    public MetricsSimulationDecorator(SimulationExecutor wrappedExecutor) {
        super(wrappedExecutor);
    }

    @Override
    public StepParameters executeStep(int stepNumber) {

        if (stepNumber == 0) {
            energyChange = totalSystemEnergy(getLatticeContainer(), getSimulationParameters());
        }

        StepParameters stepParameters = super.executeStep(stepNumber);

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

        return super.executionSummary();
    }
}
