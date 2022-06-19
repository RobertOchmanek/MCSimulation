package simulation;

public class StepParameters {

    private final boolean changeAccepted;
    private final double deltaE;

    public StepParameters(boolean changeAccepted, double deltaE) {
        this.changeAccepted = changeAccepted;
        this.deltaE = deltaE;
    }

    public boolean changeAccepted() {
        return changeAccepted;
    }

    public double getDeltaE() {
        return deltaE;
    }
}
