package Fire_Agents;

import java.util.LinkedList;

public class InitializeGraph {

    public LinkedList<Node> nodes;
    public LinkedList<Edge> edges;
    public int maxCol;
    public int maxRow;

    public void graphInitialization(String fileName) {
        InitializeNodes initializeNodes = new InitializeNodes();
        initializeNodes.initializeGraph(fileName);
        initializeNodes.buildNeighborLists();
        maxCol = initializeNodes.getLargestCol();
        maxRow = initializeNodes.getLargestRow();
        nodes = initializeNodes.getNodes();
        edges = initializeNodes.getEdges();
    }

    public void startThreads() {
        for(Node tempNode : nodes) {
            Thread threadedNode = new Thread(tempNode);
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
