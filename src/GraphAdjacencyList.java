import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GraphAdjacencyList implements Graph{
    private String graphName;
    private int numberOfVertices = 0;
    private ArrayList<ArrayList<Integer>> adjacencyList;

    public GraphAdjacencyList(String graphName) {
        this.graphName = graphName;
    }

    @Override
    public void addEdge() {

    }

    @Override
    public void removeEdge() {

    }

    @Override
    public void displayGraph() {

    }

    @Override
    public void importFromFile(String filename) {
        Scanner graphFile = null;
        try {
            graphFile = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + filename);
            System.exit(0);
        }

    }

    public void sinkEliminationAlgorithm(){

    }
    // Getters
    public String getGraphName() {
        return graphName;
    }

    // Setters
    public void setGraphName(String graphName) {
        this.graphName = graphName;
    }
    public void setNumberOfVertices(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
    }
}
