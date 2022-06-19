package simulation;

import main.Simulation.ProbabilityFormula;

import java.util.LinkedList;
import java.util.List;

//Container for constants used during simulation
public class SimulationParameters {

    //Constant simulation parameters
    private final int numAngles;
    private final List<Double> influences;
    private int neighbourLevels;
    private double TkB;
    private double externalFieldAngle;
    private ProbabilityFormula probabilityFormula;

    public SimulationParameters(int numAngles) {
        this.numAngles = numAngles;
        this.influences = new LinkedList<>();
    }

    public int getNumAngles() {
        return numAngles;
    }

    public List<Double> getInfluences() {
        return influences;
    }

    public int getNeighbourLevels() {
        return neighbourLevels;
    }

    public void setNeighbourLevels(int neighbourLevels) {
        this.neighbourLevels = neighbourLevels;
    }

    public double getTkB() {
        return TkB;
    }

    public void setTkB(double TkB) {
        this.TkB = TkB;
    }

    public double getExternalFieldAngle() {
        return externalFieldAngle;
    }

    public void setExternalFieldAngle(double externalFieldAngle) {
        this.externalFieldAngle = externalFieldAngle;
    }

    public ProbabilityFormula getProbabilityFormula() {
        return probabilityFormula;
    }

    public void setProbabilityFormula(ProbabilityFormula probabilityFormula) {
        this.probabilityFormula = probabilityFormula;
    }
}
