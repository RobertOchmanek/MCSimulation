package metrics;

import static formulas.EnergyFormulas.totalSystemEnergy;

//TODO: refactor this class as a decorator?
public class TotalEnergyMetric {

    private double energyChange;

    public TotalEnergyMetric(int[][] lattice) {
        this.energyChange = totalSystemEnergy(lattice);
    }

    public void recordChange(double deltaE) {
        energyChange += deltaE;
    }

    public double getEnergyChange() {
        return energyChange;
    }
}
