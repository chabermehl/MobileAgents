package Fire_Agents;

import java.util.LinkedList;

public class Node {
    private String name;
    private int x;
    private int y;
    private String state;
    private LinkedList<String> neighbors;
    private boolean hasAgent;

    public Node(String name, int x, int y, String state, LinkedList<String> neighbors, boolean hasAgent) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.state = state;
        this.neighbors = neighbors;
        this.hasAgent = hasAgent;
    }
}
