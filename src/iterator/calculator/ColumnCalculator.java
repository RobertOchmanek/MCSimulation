package iterator.calculator;

import iterator.NeighboursLevel;

import static iterator.NeighboursLevel.*;

public class ColumnCalculator {

    public static int calculateColumn(NeighboursLevel iteratedLevel, int latticeSize, int currentPosition, int rootCol) {

        if (FIRST.equals(iteratedLevel) || THIRD.equals(iteratedLevel)) {
            if (currentPosition == 1) {
                return getRightCol(getDiff(iteratedLevel), latticeSize, rootCol);
            } else if (currentPosition == 3) {
                return getLeftCol(getDiff(iteratedLevel), latticeSize, rootCol);
            } else {
                return rootCol;
            }
        }

        else if (SECOND.equals(iteratedLevel) || FIFTH.equals(iteratedLevel)) {
            if (currentPosition == 1 || currentPosition == 2) {
                return getRightCol(getDiff(iteratedLevel), latticeSize, rootCol);
            } else {
                return getLeftCol(getDiff(iteratedLevel), latticeSize, rootCol);
            }
        }

        else {
            if (currentPosition == 0 || currentPosition == 5) {
                return getLeftCol(1, latticeSize, rootCol);
            } else if (currentPosition == 1 || currentPosition == 4) {
                return getRightCol(1, latticeSize, rootCol);
            } else if (currentPosition == 2 || currentPosition == 3) {
                return getRightCol(2, latticeSize, rootCol);
            } else {
                return getLeftCol(2, latticeSize, rootCol);
            }
        }
    }

    private static int getRightCol(int colDiff, int latticeSize, int rootCol) {
        return rootCol + colDiff >= latticeSize ? (rootCol + colDiff) - latticeSize : rootCol + colDiff;
    }

    private static int getLeftCol(int colDiff, int latticeSize, int rootCol) {
        return rootCol - colDiff < 0 ? latticeSize + (rootCol - colDiff) : rootCol - colDiff;
    }

    private static int getDiff(NeighboursLevel iteratedLevel) {
        return FIRST.equals(iteratedLevel) || SECOND.equals(iteratedLevel) ? 1 : 2;
    }
}
