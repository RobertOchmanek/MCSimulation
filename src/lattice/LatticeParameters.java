package lattice;

import main.Simulation;

public class LatticeParameters implements Simulation.LatticeParameters {

    private final int[][] lattice;
    private final double totalSystemEnergy;
    private final double totalSystemOrder;
    private final double neighboursOrder;

    public LatticeParameters(LatticeParametersBuilder builder) {
        this.lattice = builder.getLattice();
        this.neighboursOrder= builder.getNearestNeighbourOrder();
        this.totalSystemOrder = builder.getOrderParameter();
        this.totalSystemEnergy = builder.getTotalEnergy();
    }

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
        return lattice;
    }
}
