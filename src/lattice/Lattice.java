package lattice;

import iterator.LatticeIterator;

public interface Lattice {

    int getSize();

    int getMagnetAngle(int row, int col);

    LatticeIterator getLevelIterator(int startingRow, int startingCol, int iteratedLevel);
}
