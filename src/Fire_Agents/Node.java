package Fire_Agents;
import java.util.LinkedList;

public class Node extends MessageProcessor implements Runnable {

    enum State {
        FIRE,
        DANGER,
        SAFE
    }

    // Initialized variables for constructor
    private String name = "";
    private int x = 0;
    private int y = 0;
    private State state = State.SAFE;
    private LinkedList<Node> neighbors = new LinkedList<>();
    private Agent agent = null;

    // Static variable used for giving nodes unique names
    private static int nodeCount = 0;
    private final double fireSpreadRate = 2; // in seconds

    /**
     * added so we can extend node on our HomeBase
     */
    public Node()
    {
    }

    /**
     * creates a node with a position, initialized states and that nodes neighbors
     * @param x x-coordinate for node position
     * @param y y-coordinate for node position
     * @param neighbors list of neighboring nodes
     */
    public Node(int x, int y, LinkedList<Node> neighbors) {
        nodeCount++;
        this.name = "Node_" + nodeCount;
        this.x = x;
        this.y = y;
        this.neighbors = neighbors;
        this.agent = null;
    }

    public Node(int x, int y) {
        nodeCount++;
        this.name = "Node_" + nodeCount;
        this.x = x;
        this.y = y;
        this.agent = null;
    }


    @Override
    public void run() {
        long time = System.currentTimeMillis();
        double fireCounter = 0;

        float deathCounter = 0;
        float currentTime = System.currentTimeMillis();

        while (true) {
            try {
                long dt = System.currentTimeMillis() - time;
                switch(state)
                {
                    // If we're "in danger", keep track of when we should turn red.
                    case DANGER:
                        processMessage(pollNextMessage());
                        fireCounter += dt / 1000.0;
                        //System.out.println(fireCounter);
                        if(fireCounter >= fireSpreadRate)
                        {
                            setState(State.FIRE);
                        }
                        break;

                    // Otherwise just block the thread until we get a message for
                    // efficiency
                    default:
                        processMessage(waitNextMessage());
                        break;
                }

                time = System.currentTimeMillis();

            } catch (InterruptedException e) {
                System.out.println(this.name + "'s messaging thread was interrupted.");
                e.printStackTrace();
            }
            currentTime = System.currentTimeMillis();
        }

    }

    public boolean addNeighbor(Node neighbor) {
        // The nodes should add each other
        if(!neighbors.contains(neighbor)) {
            neighbors.add(neighbor);

            // Have the neighbor do the same
            if(!neighbor.neighbors.contains(this)) {
                neighbor.neighbors.add(this);
            }
            return true;
        }
        return false;
    }

    /**
     * Process the message and perform an action depending on the type of message recieved
     * @param message to process
     */

    public void processMessage(Message message) {
        if(state == State.FIRE || message == null) {
            // Dead nodes can't communicate. Return
            return;
        }

        System.out.println(name + " processing a message of type " + message.getMessageType().toString());

        switch(message.getMessageType()) {
            case NODE_IN_DANGER:
                System.out.println("A neighbor has turned yellow");
                break;

            case NODE_DIED:
                // Set state to "in danger"
                System.out.println("A neighbor has turned red. Setting state to yellow");
                setState(State.DANGER);

                break;

            case CREATE_AGENT:
                // Forward the message to nodes that are close to home base
                System.out.println(("An agent is needing to be created"));

            case TRAVERSE_AGENT:
                // Agent hasn't reached danger zone unless this node is yellow
        }
    }

    /**
     * gets the name of the the node
     * @return name of the node
     */
    public String getName() {
        return this.name;
    }

    /**
     * gets the x-coordinate of the node
     * @return nodes x-coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * gets the y-coordinate of the node
     * @return nodes y-coordinate
     */
    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * gets the state of the node
     * @return node state
     */
    public State getState() {
        return this.state;
    }

    /**
     * sets the state of the node
     * @param newState nodes new state
     *                 alive
     *                 hot
     *                 dead
     */
    public void setState(State newState) {

        // Don't send messages if the state isn't different
        if(this.state == newState)
            return;

        this.state = newState;
        Message.MessageType messageTypeToSend = null;

        switch(state) {
            case FIRE:
                messageTypeToSend = Message.MessageType.NODE_DIED;
                agent = null;
                break;

            case DANGER:
                messageTypeToSend = Message.MessageType.NODE_IN_DANGER;
        }

        // Send a message based on what the new state is
        for(Node n : neighbors) {
                sendMessage(new Message(messageTypeToSend, this.name), n);
        }
    }

    /**
     * gets the list of neighbors the node has
     * @return neighbor list
     */
    protected LinkedList<Node> getNeighbors() {
        return this.neighbors;
    }

    /**
     * Returns neighbor with the given name
     * @param name to search for
     * @return Node with name, null if not found
     */
    protected Node getNeighborByName(String name)
    {
        for(Node node : neighbors)
        {
            if(node.getName().equals(name))
                return node;
        }
        return null;
    }

    /**
     * says whether there is an agent on the node or not
     * @return boolean representing the state
     */
    protected boolean hasAgent() {
        return agent == null;
    }

    /**
     * sets the agent on the node
     * @param agent nodes agent state
     *                    True
     *                    False
     */
    protected void setAgent(Agent agent) {
        this.agent = agent;
    }
}
