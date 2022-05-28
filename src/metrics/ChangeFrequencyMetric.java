package metrics;

//TODO: refactor this class as a decorator?
public class ChangeFrequencyMetric {

    private double numAttemptedChanges = 0;
    private double numAcceptedChanges = 0;

    public void recordChange(boolean wasAccepted) {
        if (wasAccepted) {
            ++numAcceptedChanges;
        }
        ++numAttemptedChanges;
    }

    public double acceptedChangeFrequency() {
        return numAcceptedChanges / numAttemptedChanges;
    }
}
