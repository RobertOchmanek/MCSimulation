package formulas;

import iterator.LatticeIterator;
import iterator.NeighboursLevel;
import lattice.LatticeContainer;

public class OrderFormulas {

    public static double systemOrder(LatticeContainer latticeContainer) {

        double xAvg = 0;
        int size = latticeContainer.getSize();
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                xAvg = xAvg + Math.cos(AngleFormulas.discreteToRadians(latticeContainer.getMagnetAngle(row, column), latticeContainer.getNumAngles()));
            }
        }

        xAvg = xAvg / (size * size);


        double yAvg = 0;
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                yAvg = yAvg + Math.sin(AngleFormulas.discreteToRadians(latticeContainer.getMagnetAngle(row, column), latticeContainer.getNumAngles()));
            }
        }
        yAvg = yAvg / (size * size);
        return Math.sqrt(xAvg * xAvg + yAvg * yAvg);
    }

    public static double nearestNeighboursOrder(LatticeContainer latticeContainer) {
        int size = latticeContainer.getSize();
        int amountOfNN = 4;
        double totalScore = 0;
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                double orderOfOne = 0;
                LatticeIterator neighbourLevelIterator = latticeContainer.getLevelIterator(NeighboursLevel.FIRST, row, column);
                while (neighbourLevelIterator.hasNext()) {
                    int neighbourMagnetValue = neighbourLevelIterator.getNext();
                    orderOfOne = orderOfOne + Math.cos(AngleFormulas.discreteToRadians(latticeContainer.getMagnetAngle(row, column), latticeContainer.getNumAngles()) - AngleFormulas.discreteToRadians(neighbourMagnetValue, latticeContainer.getNumAngles()));
                }
                totalScore = totalScore + orderOfOne;
            }
        }
        totalScore = totalScore / (size * size * amountOfNN);
        return totalScore;
    }
}
