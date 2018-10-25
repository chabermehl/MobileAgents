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
     * added so we can extend node on our Homebase
     */
    public Node() {}

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

    /**
     * gets the name of the the node
     * @return name of the node
     */
    private String getName() {
        return this.name;
    }

    /**
     * gets the x-coordinate of the node
     * @return nodes x-coordinate
     */
    private int getX() {
        return this.x;
    }

    /**
     * gets the y-coordinate of the node
     * @return nodes y-coordinate
     */
    private int getY() {
        return this.y;
    }

    /**
     * gets the state of the node
     * @return node state
     */
    private String getState() {
        return this.state;
    }

    /**
     * sets the state of the node
     * @param newState nodes new state
     *                 alive
     *                 hot
     *                 dead
     */
    private void setState(String newState) {
        this.state = newState;
    }

    /**
     * gets the list of neighbors the node has
     * @return neighbor list
     */
    private LinkedList<String> getNeighbors() {
        return this.neighbors;
    }

    /**
     * says whether there is an agent on the node or not
     * @return boolean representing the state
     */
    private boolean getHasAgent() {
        return this.hasAgent;
    }

    /**
     * sets the agent state on the node
     * @param newHasAgent nodes agent state
     *                    True
     *                    False
     */
    private void setHasAgent(boolean newHasAgent) {
        this.hasAgent = newHasAgent;
    }
}
