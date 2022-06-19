package formulas;

import simulation.SimulationParameters;
import iterator.LatticeIterator;
import iterator.NeighboursLevel;
import lattice.LatticeContainer;

import static formulas.AngleFormulas.discreteToRadians;

public class EnergyFormulas {

    public static double bigChangeDelta(LatticeContainer afterChange, LatticeContainer beforeChange, SimulationParameters simulationParameters) {
        return totalSystemEnergy(afterChange, simulationParameters) - totalSystemEnergy(beforeChange, simulationParameters);
    }

    public static double smallChangeDelta(LatticeContainer afterChange, LatticeContainer beforeChange, SimulationParameters simulationParameters, int row, int column) {
        return magnetEnergy(afterChange, simulationParameters, row, column) - magnetEnergy(beforeChange, simulationParameters, row, column);
    }

    public static double totalSystemEnergy(LatticeContainer latticeContainer, SimulationParameters simulationParameters) {
        double totalEnergy = 0;
        for (int row = 0; row < latticeContainer.getSize(); row++) {
            for (int column = 0; column < latticeContainer.getSize(); column++) {
                double energyOfOne = magnetEnergy(latticeContainer, simulationParameters, row, column);
                totalEnergy = totalEnergy + energyOfOne;
            }
        }
        return totalEnergy;
    }


    private static double magnetEnergy(LatticeContainer latticeContainer, SimulationParameters simulationParameters, int row, int column) {
        double energyOfOne = 0;
        for (int n = 0; n < simulationParameters.getNeighbourLevels(); n++) {
            LatticeIterator neighbourLevelIterator = latticeContainer.getLevelIterator(NeighboursLevel.values()[n], row, column);
            while (neighbourLevelIterator.hasNext()) {
                int neighbourMagnetValue = neighbourLevelIterator.getNext();
                energyOfOne = energyOfOne - (simulationParameters.getInfluences().get(n + 1) * Math.cos(discreteToRadians(latticeContainer.getMagnetAngle(row, column), simulationParameters.getNumAngles()) - discreteToRadians(neighbourMagnetValue, simulationParameters.getNumAngles())));
            }
        }
        energyOfOne = energyOfOne * 0.5;
        energyOfOne = energyOfOne - simulationParameters.getInfluences().get(0) * Math.cos(discreteToRadians(latticeContainer.getMagnetAngle(row, column), simulationParameters.getNumAngles()) - simulationParameters.getExternalFieldAngle());
        return energyOfOne;
    }
}
