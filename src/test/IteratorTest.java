package test;

import iterator.LatticeIterator;
import iterator.NeighboursLevel;
import lattice.LatticeContainer;

import java.util.LinkedList;
import java.util.List;

import static iterator.NeighboursLevel.*;

public class IteratorTest {

    public static void main(String[] args) {

        int latticeSize = 10;

        int[][] lattice = new int[latticeSize][];
        for (int i = 0; i < latticeSize; ++i) {
            lattice[i] = new int[10];
        }

        for (int i = 0; i < latticeSize; ++i) {
            for (int j = 0; j < latticeSize; ++j) {
                lattice[i][j] = (j + 1) + (10 * i);
            }
        }

        for (int i = 0; i < latticeSize; ++i) {
            for (int j = 0; j < latticeSize; ++j) {
                String separator = (lattice[i][j] < 10) ? "  " : " ";
                System.out.print(lattice[i][j] + separator);
            }
            System.out.println();
        }

        LatticeContainer latticeContainer = new LatticeContainer(lattice);

        System.out.println();
        System.out.println("---------- First level validation ----------");
        validateIterator(latticeSize, latticeContainer, FIRST);

        System.out.println();
        System.out.println("---------- Second level validation ----------");
        validateIterator(latticeSize, latticeContainer, SECOND);

        System.out.println();
        System.out.println("---------- Third level validation ----------");
        validateIterator(latticeSize, latticeContainer, THIRD);

        System.out.println();
        System.out.println("---------- Fifth level validation ----------");
        validateIterator(latticeSize, latticeContainer, FIFTH);
    }

    private static void validateIterator(int latticeSize, LatticeContainer latticeContainer, NeighboursLevel neighboursLevel) {
        List<LatticeIterator> levelIterators = new LinkedList<>();
        levelIterators.add(latticeContainer.getLevelIterator(neighboursLevel, 0, 0));
        levelIterators.add(latticeContainer.getLevelIterator(neighboursLevel, 0, latticeSize - 1));
        levelIterators.add(latticeContainer.getLevelIterator(neighboursLevel, latticeSize - 1, latticeSize - 1));
        levelIterators.add(latticeContainer.getLevelIterator(neighboursLevel, latticeSize - 1, 0));
        levelIterators.add(latticeContainer.getLevelIterator(neighboursLevel, 2, 2));

        for (LatticeIterator iterator : levelIterators) {
            while (iterator.hasNext()) {
                System.out.print(iterator.getNext() + " ");
            }
            System.out.println();
        }
    }
}
