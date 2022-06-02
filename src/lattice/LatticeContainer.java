package lattice;

import iterator.LatticeIterator;
import iterator.NeighbourLevelIterator;
import iterator.NeighboursLevel;
import main.Simulation;

public class LatticeContainer implements Lattice, Simulation.LatticeParameters {

    //Current system state and parameters updated after each approved step
    private final int[][] lattice;
    private final double totalSystemEnergy;
    private final double totalSystemOrder;
    private final double neighboursOrder;

    public LatticeContainer(int[][] lattice) {
        this.totalSystemEnergy = 0;
        this.totalSystemOrder = 0;
        this.neighboursOrder = 0;

        this.lattice = new int[lattice.length][];
        for (int i = 0; i < lattice.length; ++i) {
            this.lattice[i] = lattice[i].clone();
        }
    }

    public LatticeContainer(LatticeParametersBuilder builder) {
        this.lattice = builder.getLattice();
        this.neighboursOrder= builder.getNearestNeighbourOrder();
        this.totalSystemOrder = builder.getOrderParameter();
        this.totalSystemEnergy = builder.getTotalEnergy();
    }

    public int[][] copy() {

        int[][] latticeClone = new int[lattice.length][];
        for (int i = 0; i < lattice.length; ++i) {
            latticeClone[i] = lattice[i].clone();
        }

        return latticeClone;
    }

    @Override
    public int getSize() {
        return lattice.length;
    }

    @Override
    public int getMagnetAngle(int row, int col) {
        return lattice[row][col];
    }

    @Override
    public LatticeIterator getLevelIterator(NeighboursLevel iteratedLevel, int rootRow, int rootCol) {
        return new NeighbourLevelIterator(this, iteratedLevel, rootRow, rootCol);
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
        return copy();
    }
}
