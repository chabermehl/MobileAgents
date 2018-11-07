package Fire_Agents;

/*
use this class to initialize agents and give them somewhere to report back to
possibly make a popup on the gui when a node is reported on fire?

or are we better off creating a home base node?

should we make a node interface instead?
 */

import java.util.LinkedList;

public class HomeBase extends Node {

    private LinkedList<Agent> agentsCreated = new LinkedList<>();

    public HomeBase() {
        super();
    }

    private void createAgent()
    {
        agentsCreated.add(new Agent(this));
    }

    @Override
    public void processMessage(Message message)
    {
        switch(message.getMessageType())
        {
            case CREATE_AGENT:
                // Create an agent and send them toward the next set of yellow nodes
                createAgent();
                Thread agentThread = new Thread(agentsCreated.getLast());
                agentThread.start();
                break;
            case NODE_DIED:
                System.out.println("a node died. gonna die soon");
        }
    }

}
