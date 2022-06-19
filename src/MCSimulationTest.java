import main.Simulation;
import main.Simulation.LatticeParameters;

import java.util.Arrays;
import java.util.List;

public class MCSimulationTest {

    public static void main(String[] args) {

        int size = 32;
        int[][] lattice = new int[size][];

        for (int i = 0; i < size; ++i) {
            lattice[i] = new int[size];
            for (int j = 0; j < size; ++j) {
                //Starting from perfectly ordered lattice - same angle for each magnet
                lattice[i][j] = 1;
            }
        }

        //Initialize container
        MCSimulation simulation = new MCSimulation();
        simulation.setLattice(lattice, 2);
        simulation.setEnergyParameters(List.of(0.0, 1.0), 0.0);
        simulation.setProbabilityFormula(Simulation.ProbabilityFormula.METROPOLIS);
        simulation.setTkB(2.5);

        System.out.println("Initial state of lattice with size " + size + ":");
        LatticeParameters beforeState = simulation.getState();
        System.out.println(Arrays.deepToString(beforeState.lattice()));

        System.out.println("Parameters from state:");
        System.out.println("Total system energy:");
        System.out.println(beforeState.totalEnergy() / (32 * 32));
        System.out.println("Total system order:");
        System.out.println(beforeState.orderParameter());
        System.out.println("Neighbours order:");
        System.out.println(beforeState.nearestNeighbourOrder());

        //Perform given number of steps
        System.out.println();
        int numSteps = 50_000;
        simulation.executeMCSteps(numSteps);

        System.out.println("State of lattice after " + numSteps + " steps:");
        LatticeParameters afterState = simulation.getState();
        System.out.println(Arrays.deepToString(afterState.lattice()));

        System.out.println("Parameters from state:");
        System.out.println("Total system energy:");
        System.out.println(afterState.totalEnergy() / (32 * 32));
        System.out.println("Total system order:");
        System.out.println(afterState.orderParameter());
        System.out.println("Neighbours order:");
        System.out.println(afterState.nearestNeighbourOrder());
    }
}
