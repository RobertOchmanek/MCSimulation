import lattice.LatticeContainer;
import lattice.LatticeParametersBuilder;
import main.Simulation;

import java.util.LinkedList;
import java.util.List;

public class MCSimulation implements Simulation {

    private LatticeContainer latticeContainer;

    //Parameters used in simulation
    private int neighbourLevels;
    private double TkB;
    private double externalFieldAngle;
    private double externalFieldInfluence;
    private List<Double> influencePerLevel;
    private ProbabilityFormula formula;

    public MCSimulation() {
        this.influencePerLevel = new LinkedList<>();
    }

    @Override
    public void setLattice(int[][] lattice, int states) {
        this.latticeContainer = new LatticeContainer(lattice, states);
    }

    @Override
    public void setEnergyParameters(List<Double> parameters, double externalFieldAngle) {

        this.neighbourLevels = parameters.size() - 1;
        this.externalFieldInfluence = parameters.get(0);
        this.externalFieldAngle = externalFieldAngle;

        for (int i = 1; i < parameters.size(); ++i) {
            this.influencePerLevel.add(parameters.get(i));
        }
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

        for (int step = 0; step < steps; ++step) {
            //Perform one MC step and save result if approved
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
}
