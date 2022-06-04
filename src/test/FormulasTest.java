package test;

import lattice.LatticeContainer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static formulas.EnergyFormulas.totalSystemEnergy;
import static formulas.OrderFormulas.nearestNeighboursOrder;
import static formulas.OrderFormulas.systemOrder;

public class FormulasTest {

    public static void main(String[] args) {

        // -------------------- Uniform lattice test --------------------

        int[][] uniformLattice = new int[4][];

        for (int i = 0; i < 4; ++i) {
            uniformLattice[i] = new int[4];
            for (int j = 0; j < 4; ++j) {
                uniformLattice[i][j] = 0;
            }
        }

        System.out.println("---------- Uniform lattice ----------");
        validateFormulas(uniformLattice);
        System.out.println();

        // -------------------- Cross lattice test --------------------

        int[][] crossLattice = new int[4][];

        for (int i = 0; i < 4; ++i) {
            crossLattice[i] = new int[4];
            for (int j = 0; j < 4; ++j) {
                if (i % 2 == 0) {
                    crossLattice[i][j] = j % 2 == 0 ? 0 : 1;
                } else {
                    crossLattice[i][j] = j % 2 == 0 ? 1 :0;
                }
            }
        }

        System.out.println("---------- Cross lattice ----------");
        validateFormulas(crossLattice);
        System.out.println();

        // -------------------- Random lattice test --------------------

        int[][] randomLattice = new int[4][];

        for (int i = 0; i < 4; ++i) {
            randomLattice[i] = new int[4];
            for (int j = 0; j < 4; ++j) {
                randomLattice[i][j] = ThreadLocalRandom.current().nextInt(0, 2);
            }
        }

        System.out.println("---------- Random lattice ----------");
        validateFormulas(randomLattice);
        System.out.println();
    }

    static void validateFormulas(int[][] lattice) {

        LatticeContainer container = new LatticeContainer(lattice, 2);
        System.out.println();

        System.out.println("Total system energy:");
        double totalEnergy = totalSystemEnergy(container, List.of(0.0, 1.0), 1, 0.0) / (4.0 * 4.0);
        System.out.println(totalEnergy);

        System.out.println("Total system order:");
        double totalOrder = systemOrder(container);
        System.out.println(totalOrder);

        System.out.println("Neighbours order:");
        double neighboursOrder = nearestNeighboursOrder(container);
        System.out.println(neighboursOrder);
    }
}
