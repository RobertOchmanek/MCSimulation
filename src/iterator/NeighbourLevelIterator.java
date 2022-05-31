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
    //TODO: take lattice boundaries into account
    public int getNext() {

        if (FIRST.equals(iteratedLevel)) {
            return firstLevelNext();
        } else if (SECOND.equals(iteratedLevel)) {
            return secondLevelNext();
        } else if (THIRD.equals(iteratedLevel)) {
            return thirdLevelNext();
        } else if (FOURTH.equals(iteratedLevel)) {
            return fourthLevelNext();
        } else {
            return fifthLevelNext();
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

    private int firstLevelNext() {

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

    private int secondLevelNext() {

        int result = latticeContainer.getMagnetAngle(calculateRow(), calculateCol());

        ++currentPosition;
        return result;
    }

    private int thirdLevelNext() {

        int result;

        if (currentPosition == 0) {
            result = latticeContainer.getMagnetAngle(rootRow - 2, rootCol);
        } else if (currentPosition == 1) {
            result = latticeContainer.getMagnetAngle(rootRow, rootCol + 2);
        } else if (currentPosition == 2) {
            result = latticeContainer.getMagnetAngle(rootRow + 2, rootCol);
        } else {
            result = latticeContainer.getMagnetAngle(rootRow, rootCol - 2);
        }

        ++currentPosition;
        return result;
    }

    private int fourthLevelNext() {

        int result;

        if (currentPosition == 0) {
            result = latticeContainer.getMagnetAngle(rootRow - 2, rootCol - 1);
        } else if (currentPosition == 1) {
            result = latticeContainer.getMagnetAngle(rootRow - 2, rootCol + 1);
        } else if (currentPosition == 2) {
            result = latticeContainer.getMagnetAngle(rootRow - 1, rootCol + 2);
        } else if (currentPosition == 3) {
            result = latticeContainer.getMagnetAngle(rootRow + 1, rootCol + 2);
        } else if (currentPosition == 4) {
            result = latticeContainer.getMagnetAngle(rootRow + 2, rootCol + 1);
        } else if (currentPosition == 5) {
            result = latticeContainer.getMagnetAngle(rootRow + 2, rootCol -1);
        } else if (currentPosition == 6) {
            result = latticeContainer.getMagnetAngle(rootRow + 1, rootCol - 2);
        } else {
            result = latticeContainer.getMagnetAngle(rootRow - 1, rootCol - 2);
        }

        ++currentPosition;
        return result;
    }

    private int fifthLevelNext() {

        int result;

        if (currentPosition == 0) {
            result = latticeContainer.getMagnetAngle(rootRow - 2, rootCol - 2);
        } else if (currentPosition == 1) {
            result = latticeContainer.getMagnetAngle(rootRow - 2, rootCol + 2);
        } else if (currentPosition == 2) {
            result = latticeContainer.getMagnetAngle(rootRow + 2, rootCol + 2);
        } else {
            result = latticeContainer.getMagnetAngle(rootRow + 2, rootCol - 2);
        }

        ++currentPosition;
        return result;
    }
}
