package formulas;

import simulation.SimulationParameters;
import iterator.LatticeIterator;
import lattice.LatticeContainer;

import static formulas.AngleFormulas.discreteToRadians;
import static iterator.NeighboursLevel.FIRST;

public class OrderFormulas {

    public static double systemOrder(LatticeContainer latticeContainer, SimulationParameters simulationParameters) {

        int size = latticeContainer.getSize();
        int numAngles = simulationParameters.getNumAngles();

        double xAvg = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                xAvg += Math.cos(discreteToRadians(latticeContainer.getMagnetAngle(row, col), numAngles));
            }
        }
        xAvg = xAvg / (size * size);

        double yAvg = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                yAvg += Math.sin(discreteToRadians(latticeContainer.getMagnetAngle(row, col), numAngles));
            }
        }
        yAvg = yAvg / (size * size);

        return Math.sqrt(xAvg * xAvg + yAvg * yAvg);
    }

    public static double nearestNeighboursOrder(LatticeContainer latticeContainer, SimulationParameters simulationParameters) {

        int size = latticeContainer.getSize();
        int numAngles = simulationParameters.getNumAngles();
        int firstLevelNeighbours = 4;
        double totalScore = 0;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                double currentOrder = 0;
                LatticeIterator neighbourLevelIterator = latticeContainer.getLevelIterator(FIRST, row, col);

                while (neighbourLevelIterator.hasNext()) {
                    currentOrder += Math.cos(discreteToRadians(latticeContainer.getMagnetAngle(row, col), numAngles) - discreteToRadians(neighbourLevelIterator.getNext(), numAngles));
                }
                totalScore += currentOrder;
            }
        }

        totalScore = totalScore / (size * size * firstLevelNeighbours);
        return totalScore;
    }
}
