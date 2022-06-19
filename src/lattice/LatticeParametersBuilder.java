package lattice;

public class LatticeParametersBuilder {

    private int[][] lattice;
    private double totalEnergy;
    private double orderParameter;
    private double nearestNeighbourOrder;

    public LatticeParametersBuilder setLattice(int[][] lattice) {
        this.lattice = lattice;
        return this;
    }

    public LatticeParametersBuilder setTotalEnergy(double totalEnergy) {
        this.totalEnergy = totalEnergy;
        return this;
    }

    public LatticeParametersBuilder setOrderParameter(double orderParameter) {
        this.orderParameter = orderParameter;
        return this;
    }

    public LatticeParametersBuilder setNearestNeighbourOrder(double nearestNeighbourOrder) {
        this.nearestNeighbourOrder = nearestNeighbourOrder;
        return this;
    }

    public int[][] getLattice() {
        return lattice;
    }

    public double getTotalEnergy() {
        return totalEnergy;
    }

    public double getOrderParameter() {
        return orderParameter;
    }

    public double getNearestNeighbourOrder() {
        return nearestNeighbourOrder;
    }

    public LatticeParameters build() {
        return new LatticeParameters(this);
    }
}
