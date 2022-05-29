package iterator;

import lattice.LatticeContainer;

//Iterates clockwise through given neighbours level
public class NeighbourLevelIterator implements LatticeIterator {

    private final LatticeContainer latticeContainer;
    private final int rootRow;
    private final int rootCol;
    private final int iteratedLevel;
    private int currentPosition;

    public NeighbourLevelIterator(LatticeContainer latticeContainer, int rootRow, int rootCol, int iteratedLevel) {
        this.latticeContainer = latticeContainer;
        this.rootRow = rootRow;
        this.rootCol = rootCol;
        this.iteratedLevel = iteratedLevel;
        this.currentPosition = 0;
    }

    @Override
    //TODO: take lattice boundaries into account
    public int getNext() {

        if (iteratedLevel == 1) {
            return firstLevelClockwiseNext();
        } else if (iteratedLevel == 2) {
            return secondLevelClockwiseNext();
        } else if (iteratedLevel == 3) {
            return thirdLevelClockwiseNext();
        } else if (iteratedLevel == 4) {
            return fourthLevelClockwiseNext();
        } else {
            return fifthLevelClockwiseNext();
        }
    }

    @Override
    public boolean hasNext() {
        return currentPosition < positionsOnLevel();
    }

    private int positionsOnLevel() {

        if (iteratedLevel == 4) {
            return 8;
        }
        return 4;
    }

    private int firstLevelClockwiseNext() {

        int result;

        if (currentPosition == 0) {
            result = latticeContainer.getMagnetAngle(rootRow - 1, rootCol);
        } else if (currentPosition == 1) {
            result = latticeContainer.getMagnetAngle(rootRow, rootCol + 1);
        } else if (currentPosition == 2) {
            result = latticeContainer.getMagnetAngle(rootRow + 1, rootCol);
        } else {
            result = latticeContainer.getMagnetAngle(rootRow, rootCol - 1);
        }

        ++currentPosition;
        return result;
    }

    private int secondLevelClockwiseNext() {

        int result;

        if (currentPosition == 0) {
            result = latticeContainer.getMagnetAngle(rootRow - 1, rootCol - 1);
        } else if (currentPosition == 1) {
            result = latticeContainer.getMagnetAngle(rootRow - 1, rootCol + 1);
        } else if (currentPosition == 2) {
            result = latticeContainer.getMagnetAngle(rootRow + 1, rootCol + 1);
        } else {
            result = latticeContainer.getMagnetAngle(rootRow + 1, rootCol - 1);
        }

        ++currentPosition;
        return result;
    }

    private int thirdLevelClockwiseNext() {

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

    private int fourthLevelClockwiseNext() {

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

    private int fifthLevelClockwiseNext() {

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
