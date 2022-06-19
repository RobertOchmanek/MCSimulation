package simulation.executor.decorators;

import lattice.LatticeContainer;
import lattice.LatticeParameters;
import simulation.SimulationParameters;
import simulation.StepParameters;
import simulation.executor.SimulationExecutor;

public class SimulationDecorator implements SimulationExecutor  {

    protected final SimulationExecutor wrappedExecutor;

    public SimulationDecorator(SimulationExecutor wrappedExecutor) {
        this.wrappedExecutor = wrappedExecutor;
    }

    @Override
    public StepParameters executeStep(int stepNumber) {
        return wrappedExecutor.executeStep(stepNumber);
    }

    @Override
    public LatticeParameters executionSummary() {
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
