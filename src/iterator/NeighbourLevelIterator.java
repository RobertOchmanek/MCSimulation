package iterator;

import iterator.calculator.ColumnCalculator;
import iterator.calculator.RowCalculator;
import lattice.LatticeContainer;

import static iterator.NeighboursLevel.*;

//Iterates clockwise through given neighbours level
public class NeighbourLevelIterator implements LatticeIterator {

    private final LatticeContainer latticeContainer;
    private final NeighboursLevel iteratedLevel;
    private final int rootRow;
    private final int rootCol;
    private int currentPosition;

    public NeighbourLevelIterator(LatticeContainer latticeContainer, NeighboursLevel iteratedLevel, int rootRow, int rootCol) {
        this.latticeContainer = latticeContainer;
        this.rootRow = rootRow;
        this.rootCol = rootCol;
        this.iteratedLevel = iteratedLevel;
        this.currentPosition = 0;
    }

    @Override
    //TODO: fourth level iterator
    public int getNext() {

        if (FIRST.equals(iteratedLevel) || THIRD.equals(iteratedLevel)) {
            return firstAndThirdNext();
        } else {
            return secondFourthAndFifthNext();
        }
    }

    @Override
    public boolean hasNext() {
        return currentPosition < positionsOnLevel();
    }

    private int positionsOnLevel() {

        if (FOURTH.equals(iteratedLevel)) {
            return 8;
        }
        return 4;
    }

    private int calculateRow() {
        return RowCalculator.calculateRow(iteratedLevel, latticeContainer.getSize(), currentPosition, rootRow);
    }

    private int calculateCol() {
        return ColumnCalculator.calculateColumn(iteratedLevel, latticeContainer.getSize(), currentPosition, rootCol);
    }

    private int firstAndThirdNext() {

        int result;

        if (currentPosition == 0) {
            result = latticeContainer.getMagnetAngle(calculateRow(), rootCol);
        } else if (currentPosition == 1) {
            result = latticeContainer.getMagnetAngle(rootRow, calculateCol());
        } else if (currentPosition == 2) {
            result = latticeContainer.getMagnetAngle(calculateRow(), rootCol);
        } else {
            result = latticeContainer.getMagnetAngle(rootRow, calculateCol());
        }

        ++currentPosition;
        return result;
    }

    private int secondFourthAndFifthNext() {

        int result = latticeContainer.getMagnetAngle(calculateRow(), calculateCol());

        ++currentPosition;
        return result;
    }
}
