package formulas;

import static java.lang.Math.PI;

public class AngleFormulas {

    //Method used to convert from discrete angle value to value in radians
    public static double discreteToRadians(int discreteValue, int numValues) {
        return 2 * PI * discreteValue / numValues;
    }
}
