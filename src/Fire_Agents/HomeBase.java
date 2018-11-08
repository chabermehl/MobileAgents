package Fire_Agents;

/*
use this class to initialize agents and give them somewhere to report back to
possibly make a popup on the gui when a node is reported on fire?

or are we better off creating a home base node?

should we make a node interface instead?
 */

import java.util.HashMap;

public class HomeBase extends Node {

    private HashMap<String, String> agentMap = new HashMap<>();

    public HomeBase() {
        super();
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
     * @param agentName name of agent to add
     * @param nodeName agent's location
     */
    private void addAgentInfo(String agentName, String nodeName) {
        agentMap.put(agentName, nodeName);
        System.out.println("Added " + agentName + " from location " + nodeName);
    }

    @Override
    protected void processMessage(Message message) {
        if(getState() == State.FIRE || message == null) {
            return;
        }

        System.out.println("HomeBase received message " + message.getMessageType() +
                " from " + message.getSender());

        switch(message.getMessageType()) {
            case NODE_DIED:
                setState(State.DANGER);
                System.out.println("Home Base in danger.");
                break;
        }
    }

}
