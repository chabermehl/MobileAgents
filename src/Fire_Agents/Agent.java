package Fire_Agents;

/**
 * The agent class is used to traverse agents and surround the fire
 * to simulate finding where the threat is
 */
public class Agent extends MessageProcessor implements Runnable {

    private String name;
    private Node currentNode;
    private String lastNodeVisited;
    private static int agentCount = 0;

    public Agent(Node startingNode) {
        incAgentCount();
        name = "Agent_" + getAgentCount();
        currentNode = startingNode;
        lastNodeVisited = currentNode.getName();
    }

    /**
     * Sets the node that this agent is referencing
     * also updates the last node it visited
     * @param node to set as current.
     */
    synchronized public void setCurrentNode(Node node)
    {
        lastNodeVisited = currentNode.getName();
        currentNode = node;
    }

    /**
     * Returns the unique name of this agent
     * @return name of this agent
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the name of the last node that this agent visited
     * @return name of the last node visited
     */
    synchronized public String getLastNodeVisited() {return lastNodeVisited;}

    @Override
    public void run() {
        while(true) {
            try {
                processMessage(pollNextMessage());
            } catch (InterruptedException e) {
                System.out.println(this.name + "'s thread was interrupted.");
                e.printStackTrace();
            }
        }
    }

    protected void processMessage(Message message) {
        if(message == null) {return;}

        switch(message.getMessageType()) {
            case TRAVERSE_AGENT:
                break;
        }
    }

    // Create synchronized methods for the agent since several could be made at the same time
    private synchronized void incAgentCount() {agentCount++;}
    private synchronized int getAgentCount() {return agentCount;}
}
