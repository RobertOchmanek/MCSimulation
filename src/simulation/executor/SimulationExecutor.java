package simulation.executor;

import lattice.LatticeContainer;
import lattice.LatticeParameters;
import simulation.SimulationParameters;
import simulation.StepParameters;

public interface SimulationExecutor {

    StepParameters executeStep(int stepNumber);

    LatticeParameters executionSummary();

    SimulationParameters getSimulationParameters();

    LatticeContainer getLatticeContainer();
}
