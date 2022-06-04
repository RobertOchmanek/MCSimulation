import main.Simulation;

import java.util.Arrays;
import java.util.List;

import static formulas.EnergyFormulas.totalSystemEnergy;
import static formulas.OrderFormulas.nearestNeighboursOrder;
import static formulas.OrderFormulas.systemOrder;

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

        System.out.println("Initial state of lattice with size " + size + ":");
        System.out.println(Arrays.deepToString(lattice));;

        //Initialize container
        MCSimulation simulation = new MCSimulation();
        simulation.setLattice(lattice, 2);
        simulation.setEnergyParameters(List.of(0.0, 1.0), 0.0);
        simulation.setProbabilityFormula(Simulation.ProbabilityFormula.METROPOLIS);
        simulation.setTkB(2.5);

        System.out.println("Total system energy:");
        double totalEnergy = totalSystemEnergy(simulation.getLatticeContainer(), List.of(0.0, 1.0), 1, 0.0) / (32 * 32);
        System.out.println(totalEnergy);

        System.out.println("Total system order:");
        double totalOrder = systemOrder(simulation.getLatticeContainer());
        System.out.println(totalOrder);

        System.out.println("Neighbours order:");
        double neighboursOrder = nearestNeighboursOrder(simulation.getLatticeContainer());
        System.out.println(neighboursOrder);

        //Perform given number of steps
        System.out.println();
        int numSteps = 50_000;
        simulation.executeMCSteps(numSteps);

        System.out.println("State of lattice after " + numSteps + " steps:");
        System.out.println(Arrays.deepToString(simulation.getLatticeContainer().lattice()));

        System.out.println("Total system energy:");
        double afterTotalEnergy = totalSystemEnergy(simulation.getLatticeContainer(), List.of(0.0, 1.0), 1, 0.0) / (32 * 32);
        System.out.println(afterTotalEnergy);

        System.out.println("Total system order:");
        double afterTotalOrder = systemOrder(simulation.getLatticeContainer());
        System.out.println(afterTotalOrder);

        System.out.println("Neighbours order:");
        double afterNeighboursOrder = nearestNeighboursOrder(simulation.getLatticeContainer());
        System.out.println(afterNeighboursOrder);

        System.out.println(simulation.getState().totalEnergy() / (32 * 32));
        System.out.println(simulation.getState().orderParameter());
        System.out.println(simulation.getState().nearestNeighbourOrder());
    }
}
