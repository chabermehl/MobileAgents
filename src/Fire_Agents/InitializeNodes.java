package Fire_Agents;

import java.util.LinkedList;

public class InitializeNodes {

    int largestRow;
    int largetCol;
    LinkedList<Node> nodes;
    LinkedList<Edge> edges;
    boolean validGraph;

    public boolean initializeGraph(String mapFile) {
        validGraph = true;
        nodes = new LinkedList<>();
        edges = new LinkedList<>();
        largetCol = 0;
        largestRow = 0;
        return validGraph;
    }
}
