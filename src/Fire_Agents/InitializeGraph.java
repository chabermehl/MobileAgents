package Fire_Agents;

import java.util.LinkedList;

public class InitializeGraph {

    public LinkedList<Node> nodes;
    public LinkedList<Edge> edges;
    public int maxCol;
    public int maxRow;

    /**
     * initializes graph based on config file that is passed in
     * @param fileName text file with node/edge definitions
     */
    public void graphInitialization(String fileName) {
        InitializeNodes initializeNodes = new InitializeNodes();
        initializeNodes.initializeGraph(fileName);
        initializeNodes.buildNeighborLists();
        maxCol = initializeNodes.getLargestCol();
        maxRow = initializeNodes.getLargestRow();
        nodes = initializeNodes.getNodes();
        edges = initializeNodes.getEdges();
    }

    /**
     * loops through the nodes list and creates and starts the threads
     */
    public void startThreads() {
        for(Node tempNode : nodes) {
            Thread threadedNode = new Thread(tempNode);
            threadedNode.setDaemon(true);
            threadedNode.start();
        }
    }

    public LinkedList<Node> getNodes() {
        return nodes;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public int getMaxCol() {
        return maxCol;
    }

    public int getMaxRow() {
        return maxRow;
    }
}
