/**
 * Name - Rukshan Dias
 * UoW ID - w1912792
 * IIT ID - 20210046
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GraphAdjacencyList implements Graph{
    private int nodesCount = 0;
    private boolean isAcyclic = false;
    private ArrayList<Integer> nodesList = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> graphList = new ArrayList<>();

    // constructor
    public GraphAdjacencyList() {}

    /**
     * adding nodes to graph arrayList
     * @param firstVal - index of the array
     * @param secVal - value to include
     */
    @Override
    public void addEdge(int firstVal, int secVal) {
        int maxVal = Math.max(firstVal,secVal);
        int missingListCount = (maxVal+1)-(graphList.size());
        for (int i=0; i<missingListCount; i++){
            graphList.add(new ArrayList<Integer>());
        }
        graphList.get(firstVal).add(secVal);
    }

    /**
     * remove node from the graphList array
     * @param node - node to remove
     */
    @Override
    public void removeEdge(int node) {
        graphList.set(node, null); // make index with node null

        // remove node values in array lists
        for (int i=0; i< graphList.size(); i++) {
            if (graphList.get(i) != null) {
                for (int j = 0; j < graphList.get(i).size(); j++) {
                    if (graphList.get(i).get(j) == node){
                        graphList.get(i).remove(j);
                    }
                }
            }
        }
        System.out.println("eliminated: Node " + node);
        // displayGraph();
        sinkEliminationAlgorithm(); // recursion
    }

    /**
     * Displaying the graph
     */
    @Override
    public void displayGraph() {
        System.out.println("-----Displaying Graph----");
        for (int i=0; i< graphList.size(); i++) {
            if (graphList.get(i) != null) {
                for (int j = 0; j < graphList.get(i).size(); j++) {
                    System.out.println(i + " -> " + graphList.get(i).get(j));
                }
            }
        }
    }

    /**
     * Add node to nodeList
     * @param values - values to include in nodeList
     */
    public void addNodeList(int values[]){
        for (int i:values){
            if(! nodesList.contains(i)){
                nodesList.add(i);
                setNodesCount(nodesList.size());
            }
        }
    }

    /**
     * remove extra nodes which is not in node list
     */
    private void removeExtraNodes(){
        for (int i=0; i<graphList.size(); i++){
            if (!nodesList.contains(i)){
                graphList.set(i, null);
            }
        }
    }

    /**
     * getting file data & putting in the graphList
     * @param filename - name of the file to get data
     */
    @Override
    public void importFromFile(String filename) {
        Scanner graph_file = null;
        try {
            graph_file = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + filename);
            System.exit(0);
        }

        while (graph_file.hasNextLine()) {
            int firstVal =0;
            int secVal = 0;
            int[] vals = new int[0];
            try {
                firstVal = graph_file.nextInt();
                secVal = graph_file.nextInt();
                vals = new int[]{firstVal, secVal};
            }catch (InputMismatchException e){
                System.out.println("the File should only contain numbers..");
                System.exit(0);
            }catch (Exception e){
                System.out.println("error in the input file.. - ");
                System.exit(0);
            }

            addNodeList(vals);
            addEdge(firstVal,secVal);
        }
        removeExtraNodes();
    }

    /**
     * sink elimination algorithm
     */
    public void sinkEliminationAlgorithm(){
        int sinkNode;

        for (int i=0; i< graphList.size(); i++){
            if (graphList.get(i)!=null && graphList.get(i).isEmpty()){
                sinkNode = i;
                System.out.println("Sink found: " + sinkNode);
                removeEdge(sinkNode);
            }
        }

    }

    /**
     * display the results after sink elimination
     */
    public void viewGraphResults(){
        boolean isGraphAcyclic = graphList.stream().allMatch(element -> element==null);
        if (isGraphAcyclic){
            isAcyclic = true;
            System.out.println("graph is empty..\nSo the Graph is acyclic");
        }else {
            int startingNode;
            for (int i = 0; i < graphList.size(); i++) {
                if (graphList.get(i) != null) {
                    startingNode = i;

                    ArrayList<Integer> visitedPath = new ArrayList<Integer>();
                    ArrayList<Integer> cyclePath = findCycle(visitedPath, startingNode);

                    if (cyclePath.size() > 3){
                        isAcyclic = false;
                        System.out.println("Graph is cyclic");
                    }else {
                        isAcyclic = true;
                        System.out.println("Graph is not cyclic.. \nIt has a self loop or It is an Multi-Edge graph..");
                    }

                    // displaying Cycle path
                    System.out.println("\nCycle path:");
                    for (int j=0; j<cyclePath.size(); j++){
                        if (j == cyclePath.size()-1){
                            System.out.print(cyclePath.get(j));
                        }else {
                            System.out.print(cyclePath.get(j) + " -> ");
                        }
                    }
                    System.out.println();

                    break;
                }
            }
        }
    }

    /**
     * find the cycle path if its a cyclic graph
     * @param visitedPath - visited path
     * @param vertex - node next to visit
     * @return - cycle path of graph
     */
    public ArrayList<Integer> findCycle(ArrayList<Integer> visitedPath, int vertex) {
        //check if the cycle is found
        boolean foundCycle = false;

        for (int i = 0; i < visitedPath.size(); i++) {
            //checks the visitedPath list already contains the vertex
            // which means it has a cycle
            if (visitedPath.get(i) == vertex) {
                foundCycle = true;
                break;
            }
        }


        if (foundCycle) {
            visitedPath.add(vertex);

            // extracting the cycle path
            int lastVertex = visitedPath.get(visitedPath.size()-1);
            int firstVertex = visitedPath.indexOf(lastVertex);

            // adding the cycle path to new array
            ArrayList<Integer> finalCycle = new ArrayList<>();
            for (int i=firstVertex; i<visitedPath.size();i++){
                finalCycle.add(visitedPath.get(i));
            }
            //returns the cycle path
            return finalCycle;
        } else {
            visitedPath.add(vertex);
            int next = graphList.get(vertex).get(0);
            // recursive call with updated list and next node
            return findCycle(visitedPath, next);
        }
    }


    //-------- Getters -------
    public int getNodesCount() {
        return nodesCount;
    }
    public ArrayList<Integer> getNodesList() {
        return nodesList;
    }
    public ArrayList<ArrayList<Integer>> getGraphList() {
        return graphList;
    }
    public boolean isAcyclic() {
        return isAcyclic;
    }

    //---------- Setters ----------
    public void setNodesCount(int nodesCount) {
        this.nodesCount = nodesCount;
    }
    public void setNodesList(ArrayList<Integer> nodesList) {
        this.nodesList = nodesList;
    }
    public void setGraphList(ArrayList<ArrayList<Integer>> graphList) {
        this.graphList = graphList;
    }
}
