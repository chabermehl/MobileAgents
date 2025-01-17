package Fire_Agents;

/*
use this class to initialize agents and give them somewhere to report back to
possibly make a popup on the gui when a node is reported on fire?

or are we better off creating a home base node?

should we make a node interface instead?
 */

import java.util.HashMap;
import java.util.Map;

public class HomeBase extends Node {

    private HashMap<String, String> agentMap = new HashMap<>();

    public HomeBase(int x, int y) {
        super();
        setX(x);
        setY(y);
        setName("HomeBase");
    }

    /**
     * Creates an agent and starts its random walk towards
     * fire.
     */
    public void createAgent() {
        Agent agent = new Agent(this);
        setAgent(agent);
        Thread agentThread = new Thread(agent);
        agentThread.start();
        moveAgent();
    }

    /**
     * Adds an agent name and location to the list of created agents
     *
     * @param agentName name of agent to add
     * @param nodeName  agent's location
     */
    private void addAgentInfo(String agentName, String nodeName) {
        agentMap.put(agentName, nodeName);
        System.out.println("Added " + agentName + " from location " + nodeName);
    }

    @Override
    public void run() {
        createAgent();
        super.run();
    }

    @Override
    protected void processMessage(Message message) {
        if (getState() == State.FIRE || message == null) {
            return;
        }

        System.out.println("HomeBase received message " + message.getMessageType() +
                " from " + message.getSender());

        switch (message.getMessageType()) {
            case NODE_DIED:
                setState(State.DANGER);
                System.out.println("Home Base in danger.");
                System.out.println("About to end with these nodes.");
                for (Map.Entry<String, String> entry : agentMap.entrySet()) {
                    System.out.println(entry.getKey() + ":" + entry.getValue());
                }
                break;
            case CREATE_AGENT:
                System.out.println("oi, we made an agent.");
                addAgentInfo((String) message.getData()[0], (String) message.getData()[1]);
                break;
            case TRAVERSE_AGENT:
                // Grab an agent if possible, and keep moving it if needed
                Node sender = getNeighborByName(message.getSender());
                grabAgent(sender);
                if(getState() == State.DANGER) {
                    // Clone this agent. Also, send a message back toward base
                    forwardMessageTowardBase(new Message(Message.MessageType.CREATE_AGENT,
                            getName(), getAgent().getName(), getName()));
                    cloneAgent();
                }
                else if(getState() == State.SAFE) {
                    // Keep moving this agent
                    moveAgent();
                }
                break;
        }
    }

}
