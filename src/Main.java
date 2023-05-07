/**
 * Name - Rukshan Dias
 * UoW ID - w1912792
 * IIT ID - 20210046
 */

public class Main {
    public static void main(String[] args) {
        GraphAdjacencyList graphList = new GraphAdjacencyList();
        /*
        Acyclic data files:
            acyclic_data/a_5.txt    acyclic_data/a_20.txt    acyclic_data/a_40.txt
            acyclic_data/a_80.txt   acyclic_data/a_160.txt
            acyclic_data/a_5_selfLoop.txt        acyclic_data/a_5_multiEdge.txt
        Cyclic data files:
            cyclic_data/c_5.txt     cyclic_data/c_20.txt     cyclic_data/c_40.txt
            cyclic_data/c_80.txt     cyclic_data/c_160.txt
         */

        graphList.importFromFile("cyclic_data/c_160.txt");
        graphList.displayGraph();

        // calculating duration
        long start = System.nanoTime();
        System.out.println("\n--------Sink elimination--------");
        graphList.sinkEliminationAlgorithm();
        long now = System.nanoTime();
        long elapsed = (now - start);
        double elapsedTime = (double) elapsed / 1_000_000;

        System.out.println("\n--------Final decision--------");
        graphList.viewGraphResults();

        System.out.println("\n--------Duration for Sink elimination--------");
        System.out.println("Elapsed time = " + elapsedTime + " milliseconds");
    }
}