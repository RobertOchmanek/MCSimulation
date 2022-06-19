package lattice;

import iterator.LatticeIterator;
import iterator.NeighbourLevelIterator;
import iterator.NeighboursLevel;

public class LatticeContainer {

    //Current system state and parameters updated after each approved step
    private final int[][] lattice;

    public LatticeContainer(int[][] lattice) {
        this.lattice = new int[lattice.length][];
        for (int i = 0; i < lattice.length; ++i) {
            this.lattice[i] = lattice[i].clone();
        }
    }

    public int getSize() {
        return lattice.length;
    }

    public int getMagnetAngle(int row, int col) {
        return lattice[row][col];
    }

    public int[][] copy() {

        int[][] latticeCopy = new int[lattice.length][];
        for (int i = 0; i < lattice.length; ++i) {
            latticeCopy[i] = lattice[i].clone();
        }

        return latticeCopy;
    }

    public LatticeIterator getLevelIterator(NeighboursLevel iteratedLevel, int rootRow, int rootCol) {
        return new NeighbourLevelIterator(this, iteratedLevel, rootRow, rootCol);
    }
}
