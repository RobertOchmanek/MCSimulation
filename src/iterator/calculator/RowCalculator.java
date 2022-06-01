package iterator.calculator;

import iterator.NeighboursLevel;

import static iterator.NeighboursLevel.*;

public class RowCalculator {

    public static int calculateRow(NeighboursLevel iteratedLevel, int latticeSize, int currentPosition, int rootRow) {

        if (FIRST.equals(iteratedLevel) || THIRD.equals(iteratedLevel)) {
            if (currentPosition == 0) {
                return getUpperRow(getDiff(iteratedLevel), latticeSize, rootRow);
            } else if (currentPosition == 2) {
                return getLowerRow(getDiff(iteratedLevel), latticeSize, rootRow);
            } else {
                return rootRow;
            }
        }

        else if (SECOND.equals(iteratedLevel) || FIFTH.equals(iteratedLevel)) {
            if (currentPosition == 0 || currentPosition == 1) {
                return getUpperRow(getDiff(iteratedLevel), latticeSize, rootRow);
            } else {
                return getLowerRow(getDiff(iteratedLevel), latticeSize, rootRow);
            }
        }

        else {
            if (currentPosition == 0 || currentPosition == 1) {
                return getUpperRow(2, latticeSize, rootRow);
            } else if (currentPosition == 2 || currentPosition == 7) {
                return getUpperRow(1, latticeSize, rootRow);
            } else if (currentPosition == 3 || currentPosition == 6) {
                return getLowerRow(1, latticeSize, rootRow);
            } else {
                return getLowerRow(2, latticeSize, rootRow);
            }
        }
    }

    private static int getUpperRow(int rowDiff, int latticeSize, int rootRow) {
        return rootRow - rowDiff < 0 ? latticeSize + (rootRow - rowDiff) : rootRow - rowDiff;
    }

    private static int getLowerRow(int rowDiff, int latticeSize, int rootRow) {
        return rootRow + rowDiff >= latticeSize ? (rootRow + rowDiff) - latticeSize : rootRow + rowDiff;
    }

    private static int getDiff(NeighboursLevel iteratedLevel) {
        return FIRST.equals(iteratedLevel) || SECOND.equals(iteratedLevel) ? 1 : 2;
    }
}
