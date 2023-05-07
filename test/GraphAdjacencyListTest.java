import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GraphAdjacencyListTest {
    GraphAdjacencyList graph = new GraphAdjacencyList();
    ArrayList<ArrayList<Integer>> acyclicGraphList = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(1,2)),
                    new ArrayList<>(Arrays.asList(2,3)),
                    new ArrayList<>(Arrays.asList()),
                    new ArrayList<>(Arrays.asList(2)),
                    new ArrayList<>(Arrays.asList(0,3))
            )
    );
    ArrayList<ArrayList<Integer>> cyclicGraphList = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(1,2)),
                    new ArrayList<>(Arrays.asList(2,3,4)),
                    new ArrayList<>(Arrays.asList()),
                    new ArrayList<>(Arrays.asList(2)),
                    new ArrayList<>(Arrays.asList(0,3))
            )
    );
    ArrayList<ArrayList<Integer>> selfLoopGraphList = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(2,0)),
                    new ArrayList<>(Arrays.asList(2,3,4)),
                    new ArrayList<>(Arrays.asList()),
                    new ArrayList<>(Arrays.asList(2)),
                    new ArrayList<>(Arrays.asList(0,3))
            )
    );

    ArrayList<ArrayList<Integer>> multiEdgeGraphList = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(2,4)), // 4 in here
                    new ArrayList<>(Arrays.asList(2,3,4)),
                    new ArrayList<>(Arrays.asList()),
                    new ArrayList<>(Arrays.asList(2)),
                    new ArrayList<>(Arrays.asList(0,3))  // 0 in here
            )
    );
    @org.junit.jupiter.api.Test
    void acyclicGraph(){
        System.out.println(acyclicGraphList);
        graph.setGraphList(acyclicGraphList);
        graph.sinkEliminationAlgorithm();
        graph.viewGraphResults();
        assertTrue(graph.isAcyclic());
    }

    @org.junit.jupiter.api.Test
    void cyclicGraph(){
        System.out.println(cyclicGraphList);
        graph.setGraphList(cyclicGraphList);
        graph.sinkEliminationAlgorithm();
        graph.viewGraphResults();
        assertFalse(graph.isAcyclic());
    }

    @org.junit.jupiter.api.Test
    void selfLoopGraph(){
        System.out.println(selfLoopGraphList);
        graph.setGraphList(selfLoopGraphList);
        graph.sinkEliminationAlgorithm();
        graph.viewGraphResults();
        assertTrue(graph.isAcyclic());
    }

    @org.junit.jupiter.api.Test
    void multiEdgeGraph(){
        System.out.println(multiEdgeGraphList);
        graph.setGraphList(multiEdgeGraphList);
        graph.sinkEliminationAlgorithm();
        graph.viewGraphResults();
        assertTrue(graph.isAcyclic());
    }
}