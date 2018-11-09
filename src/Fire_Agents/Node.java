package Fire_Agents;
import java.util.LinkedList;
import java.util.Random;

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
    private State startingState = State.SAFE;
    private LinkedList<Node> neighbors = new LinkedList<>();
    private Agent agent = null;
    private boolean leadsToBase = false;

    // Static variable used for giving nodes unique names
    private static int nodeCount = 0;
    private final double fireSpreadRate = 1; // in seconds

    /**
     * added so we can extend node on our HomeBase
     */
    public Node() {}

    /**
     * creates a node with a position, initialized states and that nodes neighbors
     * @param x x-coordinate for node position
     * @param y y-coordinate for node position
     * @param startingState state to start in once run
     */
    public Node(int x, int y, State startingState) {
        nodeCount++;
        this.name = "Node_" + nodeCount;
        this.x = x;
        this.y = y;
        this.startingState = startingState;
    }

    public Node(int x, int y) {
        nodeCount++;
        this.name = "Node_" + nodeCount;
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the state to switch to when run
     * @param state to switch to on startup
     */
    public void setStartingState(State state) {
        this.startingState = state;
    }

    @Override
    public void run() {
        setState(startingState);
        long time = System.currentTimeMillis();
        double fireCounter = 0;

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
                        if(fireCounter >= fireSpreadRate) {
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
        }
    }

    /**
     * Adds the given node to list of neighbors. The given Node also
     * adds this node to its list of neighbors
     * @param neighbor neighbor to add
     * @return whether or not adding the neighbor was successful
     */
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
    protected void processMessage(Message message) {
        if(state == State.FIRE || message == null) {
            // Dead nodes can't communicate. Return
            return;
        }
        //System.out.println(name + " processing a message of type " +
        //        message.getMessageType().toString() + " from " + message.getSender());
        switch(message.getMessageType()) {
            case NODE_IN_DANGER:
                break;

            case NODE_DIED:
                // Set state to "in danger"
                setState(State.DANGER);
                break;

            case CREATE_AGENT:
                // Forward the message to nodes that are close to home base
                forwardMessageTowardBase(message);
                break;

            case TRAVERSE_AGENT:
                // Grab an agent if possible, and keep moving it if needed
                Node sender = getNeighborByName(message.getSender());
                grabAgent(sender);
                if(state == State.DANGER) {
                    // Clone this agent. Also, send a message back toward base
                    forwardMessageTowardBase(new Message(Message.MessageType.CREATE_AGENT,
                            getName(), agent.getName(), getName()));
                    cloneAgent();
                }
                else if(state == State.SAFE) {
                    // Keep moving this agent
                    moveAgent();
                }
                break;

            case CLONE_AGENT:
                cloneAgent();
                break;
        }
    }

    /**
     * Moves the agent attached to this node to a new node, prioritizing
     * nodes that are in danger
     */
    protected void moveAgent()
    {
        if(agent == null) { return; }
        Node nodeToMoveTo = null;

        // Get all neighbors and move the agent to the neighbor that's yellow or is closest
        for(Node n : neighbors) {
            if (!n.hasAgent() && n.getState() == State.DANGER) {
                nodeToMoveTo = n;
                break;
            }
        }

        // Search for and add all nodes that haven't been visited yet and aren't red.
        if(nodeToMoveTo == null && neighbors.size() > 1){
            LinkedList<Node> list = new LinkedList<>();
            for(Node n : neighbors) {
                if(!n.hasAgent() && !agent.getName().equals(n.getName()) &&
                        n.getState() != State.FIRE) {
                    list.add(n);
                }
            }
            // Pick a random node out of these to try next
            Random rand = new Random();
            int ranNodeIndex = rand.nextInt(list.size());
            nodeToMoveTo = list.get(ranNodeIndex);
        }
        else if(nodeToMoveTo == null && neighbors.size() == 1 &&
                !neighbors.get(0).getName().equals(agent.getLastNodeVisited())){
                nodeToMoveTo = neighbors.get(0);
        }
        // Go back to previous node if no others are available
        else if(nodeToMoveTo == null) {
            nodeToMoveTo = getNeighborByName(agent.getLastNodeVisited());
        }

        // Send agent new message and wait for it to pick up this agent
        if (nodeToMoveTo != null && agent != null) {
            System.out.println(this.getName() + " moving agent to " + nodeToMoveTo.getName());
            sendMessage(new Message(Message.MessageType.TRAVERSE_AGENT, this.name), nodeToMoveTo);
        }
    }

    /**
     * Grabs an agent from the given node
     * @param from node to take agent from
     */
    protected void grabAgent(Node from)
    {
        if(from != null && from.agent != null) {
            setAgent(from.agent);
            from.agent = null;
            agent.setCurrentNode(this);
        }
    }

    /**
     * sets the state of the node
     * @param newState nodes new state
     *                 alive
     *                 hot
     *                 dead
     */
    protected void setState(State newState) {

        // Don't send messages if the state isn't different
        if(this.state == newState)
            return;

        this.state = newState;
        Message.MessageType messageTypeToSend = null;

        switch(state) {
            case FIRE:
                messageTypeToSend = Message.MessageType.NODE_DIED;
                agent = null; // kill agent
                System.out.println("Node " + name + " died.");
                break;

            case DANGER:
                cloneAgent();
                messageTypeToSend = Message.MessageType.NODE_IN_DANGER;
                break;
        }

        // Send a message based on what the new state is
        for(Node n : neighbors) {
            sendMessage(new Message(messageTypeToSend, this.name), n);
        }
    }

    /**
     * Clones the current agent to any surrounding nodes
     */
    private void cloneAgent() {
        // Check each neighbor and clone current agent to surrounding nodes (with new ID?)
        if(!hasAgent()) {return;}
        for(Node n : neighbors) {
            if(!n.hasAgent() && n.getState() != State.FIRE) {
                Agent agentClone = new Agent(n);
                n.setAgent(agentClone);
                System.out.println("Cloning " + agentClone.getName() + " from " + getName() + " to " + n.getName());
                forwardMessageTowardBase(new Message(Message.MessageType.CREATE_AGENT,
                        getName(), agentClone.getName(), n.getName()));

                // If one of the nodes is yellow, make sure to keep cloning from that node
                if(n.getState() == State.DANGER ) {
                    sendMessage(new Message(Message.MessageType.CLONE_AGENT, this.getName()), n);
                }
            }
        }
    }

    /**
     * Forwards the given message to a node that isn't the
     * one that sent the message and leads to base
     * @param message to forward
     */
    private void forwardMessageTowardBase(Message message) {
        for(Node n : neighbors) {
            if(n.getState() != State.FIRE && n.leadsToBase() && !n.getName().equals(message.getSender())) {
                sendMessage(new Message(message.getMessageType(), getName(), message.getData()), n);
                break;
            }
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

    /**
     * Set the X coordinate of this node
     * @param x x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the Y coordinate of this node
     * @param y y-coordinate
     */
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
        for(Node node : neighbors) {
            if(node.getName().equals(name))
                return node;
        }
        return null;
    }

    public LinkedList<String> getNeighborNames() {
        LinkedList<String> neighborNames = new LinkedList<>();
        for(Node node :  neighbors) {
            neighborNames.add(node.getName());
        }
        return neighborNames;
    }

    /**
     * says whether there is an agent on the node or not
     * @return boolean representing the state
     */
    protected boolean hasAgent() {
        return agent != null;
    }

    /**
     * sets the agent on the node
     * @param agent nodes agent state
     *                    True
     *                    False
     */
    synchronized protected void setAgent(Agent agent) {
        if(agent != null) {
            this.agent = agent;
            leadsToBase = true;
        }
    }

    /**
     * Sets the name of this node.
     * @param name to set
     */
    protected void setName(String name) {this.name = name;}

    /**
     * @return whether this node connects to a path back to the base station
     */
    protected boolean leadsToBase() {return leadsToBase;}
}
