package formulas;

public class EnergyFormulas {

    public static double bigChangeDelta(int[][] afterChange, int[][] beforeChange) {
        return totalSystemEnergy(afterChange) - totalSystemEnergy(beforeChange);
    }

    public static double smallChangeDelta(int afterDirection, int beforeDirection) {
        return magnetEnergy(afterDirection) - magnetEnergy(beforeDirection);
    }

    //TODO
    public static double totalSystemEnergy(int[][] lattice) {
        return 1;
    }

    //TODO
    private static double magnetEnergy(int direction) {
        return 1;
    }
}
