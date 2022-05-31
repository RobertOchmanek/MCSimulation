package lattice;

import iterator.LatticeIterator;
import iterator.NeighbourLevelIterator;
import iterator.NeighboursLevel;

public class LatticeContainer implements Lattice {

    //Current system state and parameters updated after each approved step
    private int[][] lattice;
    private double totalSystemEnergy;
    private double totalSystemOrder;
    private double neighboursOrder;

    public LatticeContainer(int[][] lattice) {
        this.totalSystemEnergy = 0;
        this.totalSystemOrder = 0;
        this.neighboursOrder = 0;

        this.lattice = new int[lattice.length][];
        for (int i = 0; i < lattice.length; ++i) {
            this.lattice[i] = lattice[i].clone();
        }
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

    public double getTotalSystemEnergy() {
        return totalSystemEnergy;
    }

    public void setTotalSystemEnergy(double totalSystemEnergy) {
        this.totalSystemEnergy = totalSystemEnergy;
    }

    public double getTotalSystemOrder() {
        return totalSystemOrder;
    }

    public void setTotalSystemOrder(double totalSystemOrder) {
        this.totalSystemOrder = totalSystemOrder;
    }

    public double getNeighboursOrder() {
        return neighboursOrder;
    }

    public void setNeighboursOrder(double neighboursOrder) {
        this.neighboursOrder = neighboursOrder;
    }
}
