package iterator.calculator;

import iterator.NeighboursLevel;

import static iterator.NeighboursLevel.*;

public class RowCalculator {

    public static int calculateRow(NeighboursLevel iteratedLevel, int latticeSize, int currentPosition, int rootRow) {

        if (FIRST.equals(iteratedLevel)) {
            if (currentPosition == 0) {
                return rootRow - 1 < 0 ? latticeSize - 1 : rootRow - 1;
            } else if (currentPosition == 2) {
                return rootRow + 1 == latticeSize ? 0 : rootRow + 1;
            } else {
                return rootRow;
            }
        }

        else if (SECOND.equals(iteratedLevel)) {
            if (currentPosition == 0 || currentPosition == 1) {
                return rootRow - 1 < 0 ? latticeSize - 1 : rootRow - 1;
            } else {
                return rootRow + 1 == latticeSize ? 0 : rootRow + 1;
            }
        }

        else {
            return -1;
        }
    }
}
