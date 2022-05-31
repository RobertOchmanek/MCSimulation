package lattice;

import iterator.LatticeIterator;
import iterator.NeighboursLevel;

public interface Lattice {

    int getSize();

    int getMagnetAngle(int row, int col);

    LatticeIterator getLevelIterator(NeighboursLevel iteratedLevel, int startingRow, int startingCol);
}
