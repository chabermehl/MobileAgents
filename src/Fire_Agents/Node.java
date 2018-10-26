package Fire_Agents;

import java.util.LinkedList;

public class Node {

    enum State
    {
        FIRE,
        DANGER,
        SAFE
    }

    //initialized variables for constructor
    private String name;
    private int x;
    private int y;
    private State state;
    private LinkedList<String> neighbors;
    private Agent agent;

    // Static variable used for giving nodes unique names
    private static int nodeCount = 0;

    /**
     * added so we can extend node on our Homebase
     */
    public Node() {}

    /**
     * creates a node with a position, initialized states and that nodes neighbors
     * @param x x-coordinate for node position
     * @param y y-coordinate for node position
     * @param state alive, hot, dead
     * @param neighbors list of neighboring nodes
     */
    public Node(int x, int y, State state, LinkedList<String> neighbors) {
        nodeCount++;
        this.name = "Node_" + nodeCount;
        this.x = x;
        this.y = y;
        this.state = state;
        this.neighbors = neighbors;
        this.agent = null;
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
    private State getState() {
        return this.state;
    }

    /**
     * sets the state of the node
     * @param newState nodes new state
     *                 alive
     *                 hot
     *                 dead
     */
    private void setState(State newState) {
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
    private boolean hasAgent() {
        return agent == null;
    }

    /**
     * sets the agent on the node
     * @param agent nodes agent state
     *                    True
     *                    False
     */
    private void setAgent(Agent agent) {
        this.agent = agent;
    }
}
