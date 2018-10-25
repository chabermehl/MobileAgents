package Fire_Agents;

import java.util.LinkedList;

public class Node {
    //initialized variables for constructor
    private String name;
    private int x;
    private int y;
    private String state;
    private LinkedList<String> neighbors;
    private boolean hasAgent;

    /**
     * creates a node with a position, initialized states and that nodes neighbors
     * @param name name of the node(A, B, C, etc.)
     * @param x x-coordinate for node position
     * @param y y-coordinate for node position
     * @param state alive, hot, dead
     * @param neighbors list of neighboring nodes
     * @param hasAgent boolean to say if there is an agent on the node
     */
    public Node(String name, int x, int y, String state, LinkedList<String> neighbors, boolean hasAgent) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.state = state;
        this.neighbors = neighbors;
        this.hasAgent = hasAgent;
    }
}
