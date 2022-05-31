package iterator.calculator;

import iterator.NeighboursLevel;

import static iterator.NeighboursLevel.*;

public class RowCalculator {

    public static int calculateRow(NeighboursLevel iteratedLevel, int latticeSize, int currentPosition, int rootRow) {

        if (FIRST.equals(iteratedLevel) || THIRD.equals(iteratedLevel)) {
            if (currentPosition == 0) {
                return getUpperRow(iteratedLevel, latticeSize, rootRow);
            } else if (currentPosition == 2) {
                return getLowerRow(iteratedLevel, latticeSize, rootRow);
            } else {
                return rootRow;
            }
        }

        else if (SECOND.equals(iteratedLevel) || FIFTH.equals(iteratedLevel)) {
            if (currentPosition == 0 || currentPosition == 1) {
                return getUpperRow(iteratedLevel, latticeSize, rootRow);
            } else {
                return getLowerRow(iteratedLevel, latticeSize, rootRow);
            }
        }

        else {
            throw new IllegalStateException("Fourth level iterator not implemented.");
        }
    }

    private static int getUpperRow(NeighboursLevel iteratedLevel, int latticeSize, int rootRow) {
        int rowDiff = FIRST.equals(iteratedLevel) || SECOND.equals(iteratedLevel) ? 1 : 2;
        return rootRow - rowDiff < 0 ? latticeSize + (rootRow - rowDiff) : rootRow - rowDiff;
    }

    private static int getLowerRow(NeighboursLevel iteratedLevel, int latticeSize, int rootRow) {
        int rowDiff = FIRST.equals(iteratedLevel) || SECOND.equals(iteratedLevel) ? 1 : 2;
        return rootRow + rowDiff >= latticeSize ? (rootRow + rowDiff) - latticeSize : rootRow + rowDiff;
    }
}
