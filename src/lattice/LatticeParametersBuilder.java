package lattice;

public class LatticeParametersBuilder {
    private double totalEnergy;
    private double orderParameter;
    private double nearestNeighbourOrder;
    private int[][] lattice;

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

    public LatticeParametersBuilder setLattice(int[][] lattice) {
        this.lattice = lattice;
        return this;
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

    public int[][] getLattice() {
        return lattice;
    }

    public LatticeContainer build() {
        return new LatticeContainer(this);
    }
}
