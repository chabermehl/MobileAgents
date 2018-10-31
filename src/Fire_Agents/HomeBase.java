package Fire_Agents;

/*
use this class to initialize agents and give them somewhere to report back to
possibly make a popup on the gui when a node is reported on fire?

or are we better off creating a home base node?

should we make a node interface instead?
 */

import java.util.LinkedList;

public class HomeBase extends Node {

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

                break;
        }
    }

    private LinkedList<Agent> agentsCreated = new LinkedList<>();
}
