package formulas;

import main.Simulation.ProbabilityFormula;

import java.util.Random;

import static java.lang.Math.exp;
import static main.Simulation.ProbabilityFormula.METROPOLIS;

public class ProbabilityFormulas {

    private static final Random RANDOM_GENERATOR = new Random();

    //Method used to determine if a change in the state of the magnet should be accepted
    public static boolean changeAccepted(double deltaE, double TkB, ProbabilityFormula probabilityFormula) {

        double R = RANDOM_GENERATOR.nextDouble();
        double P = METROPOLIS.equals(probabilityFormula) ?
                metropolisProbability(deltaE, TkB) : glauberProbability(deltaE, TkB);

        return R < P;
    }

    public static double metropolisProbability(double deltaE, double TkB) {
        if (deltaE > 0) {
            return exp(((-1 * deltaE) / TkB));
        } else {
            return 1;
        }
    }

    public static double glauberProbability(double deltaE, double TkB) {
        return exp(((-1 * deltaE) / TkB)) / (1 + exp(((-1 * deltaE) / TkB)));
    }
}
