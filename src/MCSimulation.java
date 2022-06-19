import simulation.executor.decorators.MetricsSimulationDecorator;
import simulation.executor.MonteCarloSimulationExecutor;
import simulation.executor.SimulationExecutor;
import simulation.SimulationParameters;
import lattice.LatticeContainer;
import main.Simulation;

import java.util.List;

public class MCSimulation implements Simulation {

    private SimulationParameters simulationParameters;
    private SimulationExecutor simulationExecutor;

    public MCSimulation() {}

    @Override
    public void setLattice(int[][] lattice, int states) {
        simulationParameters = new SimulationParameters(states);
        simulationExecutor = new MetricsSimulationDecorator(new MonteCarloSimulationExecutor(simulationParameters, new LatticeContainer(lattice)));
    }

    @Override
    public void setEnergyParameters(List<Double> parameters, double externalFieldAngle) {
        simulationParameters.setNeighbourLevels(parameters.size() - 1);
        simulationParameters.setExternalFieldAngle(externalFieldAngle);
        simulationParameters.getInfluences().addAll(parameters);
    }

    @Override
    public void setProbabilityFormula(ProbabilityFormula formula) {
        simulationParameters.setProbabilityFormula(formula);
    }

    @Override
    public void setTkB(double TkB) {
        simulationParameters.setTkB(TkB);
    }

    @Override
    public LatticeParameters getState() {
        return simulationExecutor.executionSummary();
    }

    @Override
    public void executeMCSteps(int steps) {

        for (int stepNumber = 0; stepNumber < steps; ++stepNumber) {
            simulationExecutor.executeStep(stepNumber);
        }
    }
}
