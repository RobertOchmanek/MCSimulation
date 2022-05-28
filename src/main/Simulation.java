package main;

import java.util.List;

public interface Simulation {

    /**
     * Typ wyliczeniowy umożliwiający wskazanie wzoru stosowanego w obliczeniach.
     */
    enum ProbabilityFormula {
        GLAUBER,
        METROPOLIS
    }

    /**
     * Interfejs zawierający informacje o parametrach sieci.
     */
    interface LatticeParameters {

        /**
         * Energia całkowita układu magnesów
         *
         * @return energia całkowita
         */
        double totalEnergy();

        /**
         * Parametr uporządkowania
         *
         * @return porządek
         */
        double orderParameter();

        /**
         * Parametr uporządkowania dla najbliższych sąsiadów.
         *
         * @return uporządkowanie najbliższych sąsiadów
         */
        double nearestNeighbourOrder();

        /**
         * Stan sieci magnesów. Kierunki zapisane w tablicy mogą zawierać się wyłącznie
         * w zakresie od 0 do states-1.
         *
         * @return stan magnesów
         */
        int[][] lattice();
    }

    /**
     * Metoda ustawia początkowy stan sieci magnesów oraz liczbę stanów (kierunków),
     * które może przyjąć każdy z magnesów. Sieć jest siecią kwadratowa. Metoda
     * powinna przekazana tablicę skopiować.
     *
     * @param lattice stan sieci
     * @param states  liczba stanów jakie może przyjąć kierunek magnesu
     */
    void setLattice(int[][] lattice, int states);

    /**
     * Ustawienie parametrów oddziaływania magnesów. Lista zawiera na pozycji o
     * indeksie 0 parametr oddziaływania z zewnętrznym polem. Na pozycjach od 1 do
     * parameters.size()-1 parametry oddziaływania z sąsiadami poziomu 1 i
     * kolejnymi.
     *
     * @param parameters        parametry oddziaływania
     * @param externaFieldAngle kąt ustawienia zewnętrznego pola. Wartość w zakresie
     *                          od 0 do 2xPI.
     */
    void setEnergyParameters(List<Double> parameters, double externaFieldAngle);

    /**
     * Wybór formuły używanej do wyznaczania prawdopodobieństwa zmiany stanu układu.
     *
     * @param formula nazwa używanej formuły
     */
    void setProbabilityFormula(ProbabilityFormula formula);

    /**
     * Ustalenie aktualnej temperatury.
     *
     * @param TkB temperatura w postaci iloczynu T i stałej Boltzmanna.
     */
    void setTkB(double TkB);

    /**
     * Zlecenie wykonania określonej liczby kroków MC. Stan początkowy sieci
     * magnesów to albo stan ustawiony za pomocą setLattice() lub stan po
     * wcześniejszym wykonaniu executeMCSteps().
     *
     * @param steps liczba kroków do wykonania.
     */
    void executeMCSteps(int steps);

    /**
     * Pobranie stanu sieci magnesów.
     *
     * @return obiekt zgodny z interfejsem LatticeParameters.
     */
    LatticeParameters getState();
}