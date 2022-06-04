package formulas;

import iterator.LatticeIterator;
import iterator.NeighboursLevel;
import lattice.LatticeContainer;

import java.util.List;

public class EnergyFormulas {

    public static double bigChangeDelta(int[][] afterChange, int[][] beforeChange) {
        return totalSystemEnergy(afterChange) - totalSystemEnergy(beforeChange);
    }

    public static double smallChangeDelta(int afterDirection, int beforeDirection) {
        return magnetEnergy(afterDirection) - magnetEnergy(beforeDirection);
    }

    public static double totalSystemEnergy(LatticeContainer latticeContainer, List<Double> parameters, int numOfLevels, double externalFieldAngle) {
        double totalEnergy = 0;
        for (int row = 0; row < latticeContainer.getSize(); row++) {
            for (int column = 0; column < latticeContainer.getSize(); column++) {
                double energyOfOne = magnetEnergy(latticeContainer, parameters, numOfLevels, externalFieldAngle, row, column);
                totalEnergy = totalEnergy + energyOfOne;
            }
        }
        return totalEnergy;
    }


    private static double magnetEnergy(LatticeContainer latticeContainer, List<Double> parameters, int numOfLevels, double externalFieldAngle, int row, int column) {
        double energyOfOne = 0;
        for (int n = 0; n < numOfLevels; n++) {
            LatticeIterator neighbourLevelIterator = latticeContainer.getLevelIterator(NeighboursLevel.values()[n], row, column);
            while (neighbourLevelIterator.hasNext()) {
                int neighbourMagnetValue = neighbourLevelIterator.getNext();
                energyOfOne = energyOfOne - (parameters.get(n + 1) * Math.cos(AngleFormulas.discreteToRadians(latticeContainer.getMagnetAngle(row, column), latticeContainer.getNumAngles()) - AngleFormulas.discreteToRadians(neighbourMagnetValue, latticeContainer.getNumAngles())));
            }
        }
        energyOfOne = energyOfOne * 0.5;
        energyOfOne = energyOfOne - parameters.get(0) * Math.cos(AngleFormulas.discreteToRadians(latticeContainer.getMagnetAngle(row, column), latticeContainer.getNumAngles()) - externalFieldAngle);
        return energyOfOne;
    }
}
