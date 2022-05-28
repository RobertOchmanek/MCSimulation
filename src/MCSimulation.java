import main.Simulation;

import java.util.LinkedList;
import java.util.List;

public class MCSimulation implements Simulation {

    //Current system state and parameters updated after each approved step
    private int[][] lattice;
    private double totalSystemEnergy;
    private double totalSystemOrder;
    private double neighboursOrder;

    //Parameters used in simulation
    private int numAngles;
    private int neighbourLevels;
    private double TkB;
    private double externalFieldAngle;
    private double externalFieldInfluence;
    private List<Double> influencePerLevel;
    private ProbabilityFormula formula;

    public MCSimulation() {
        this.totalSystemEnergy = 0;
        this.totalSystemOrder = 0;
        this.neighboursOrder = 0;
        this.influencePerLevel = new LinkedList<>();
    }

    @Override
    public void setLattice(int[][] lattice, int states) {

        this.lattice = new int[lattice.length][];
        for (int i = 0; i < lattice.length; ++i) {
            this.lattice[i] = lattice[i].clone();
        }

        this.numAngles = states;
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

        //TODO: refactor this as a builder
        return new LatticeParameters() {

            @Override
            public double totalEnergy() {
                return totalSystemEnergy;
            }

            @Override
            public double orderParameter() {
                return totalSystemOrder;
            }

            @Override
            public double nearestNeighbourOrder() {
                return neighboursOrder;
            }

            @Override
            public int[][] lattice() {

                int[][] latticeCopy = new int[lattice.length][];
                for (int i = 0; i < lattice.length; ++i) {
                    latticeCopy[i] = lattice[i].clone();
                }

                return latticeCopy;
            }
        };
    }
}
