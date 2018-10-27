package Fire_Agents;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Node implements Runnable, MessageSenderReciever {

    enum State
    {
        FIRE,
        DANGER,
        SAFE
    }

    //initialized variables for constructor
    private String name = "";
    private int x = 0;
    private int y = 0;
    private State state = State.SAFE;
    private LinkedList<Node> neighbors = new LinkedList<>();
    private Agent agent = null;
    private LinkedBlockingQueue<Message> messageQueue;

    // Static variable used for giving nodes unique names
    private static int nodeCount = 0;

    /**
     * added so we can extend node on our Homebase
     */
    public Node()
    {
    }

    /**
     * creates a node with a position, initialized states and that nodes neighbors
     * @param x x-coordinate for node position
     * @param y y-coordinate for node position
     * @param state alive, hot, dead
     * @param neighbors list of neighboring nodes
     */
    public Node(int x, int y, State state, LinkedList<Node> neighbors) {
        nodeCount++;
        this.name = "Node_" + nodeCount;
        this.x = x;
        this.y = y;
        this.state = state;
        this.neighbors = neighbors;
        this.agent = null;
        messageQueue = new LinkedBlockingQueue<>();
    }


    @Override
    public void run() {

        while (true) {
            try {
                processMessage(messageQueue.take());
            } catch (InterruptedException e) {
                System.out.println(this.name + "'s messaging thread was interrupted.");
                e.printStackTrace();
            }
        }

    }

    public boolean addNeighbor(Node neighbor)
    {
        // The nodes should add each other
        if(!neighbors.contains(neighbor))
        {
            neighbors.add(neighbor);

            // Have the neighbor do the same
            if(!neighbor.neighbors.contains(this))
            {
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
    public void processMessage(Message message)
    {
        if(state == State.FIRE)
        {
            // Dead nodes can't communicate. Return
            return;
        }

        System.out.println(name + " processing a message of type " + message.getMessageType().toString());

        switch(message.getMessageType())
        {
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
     * Send a message to a node
     * @param message to send
     * @param receiver to send message to
     */
    public void sendMessage(Message message, MessageSenderReciever receiver)
    {
        receiver.recieveMessage(message);
    }

    public void recieveMessage(Message message)
    {
        if(message != null)
        {
            messageQueue.add(message);
        }
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
    public void setState(State newState) {
        this.state = newState;
        Message.MessageType messageTypeToSend = null;

        switch(state)
        {
            case FIRE:
                messageTypeToSend = Message.MessageType.NODE_DIED;
                break;

            case DANGER:
                messageTypeToSend = Message.MessageType.NODE_IN_DANGER;

        }

        // Send a message based on what the new state is
        for(Node n : neighbors)
        {
            sendMessage(new Message(messageTypeToSend), n);
        }
    }

    /**
     * gets the list of neighbors the node has
     * @return neighbor list
     */
    private LinkedList<Node> getNeighbors() {
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
