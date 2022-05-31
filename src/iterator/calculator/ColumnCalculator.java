package iterator.calculator;

import iterator.NeighboursLevel;

import static iterator.NeighboursLevel.*;

public class ColumnCalculator {

    public static int calculateColumn(NeighboursLevel iteratedLevel, int latticeSize, int currentPosition, int rootCol) {

        if (FIRST.equals(iteratedLevel)) {
            if (currentPosition == 1) {
                return rootCol + 1 == latticeSize ? 0 : rootCol + 1;
            } else if (currentPosition == 3) {
                return rootCol - 1 < 0 ? latticeSize - 1 : rootCol - 1;
            } else {
                return rootCol;
            }
        }

        else if (SECOND.equals(iteratedLevel)) {
            if (currentPosition == 1 || currentPosition == 2) {
                return rootCol + 1 == latticeSize ? 0 : rootCol + 1;
            } else {
                return rootCol - 1 < 0 ? latticeSize - 1 : rootCol - 1;
            }
        }

        else {
            return -1;
        }
    }
}