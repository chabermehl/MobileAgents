package Fire_Agents;

public class Agent extends MessageProcessor implements Runnable {

    private String name;
    private Node currentNode;
    private String lastNodeVisited;
    private static int agentCount = 0;

    public Agent(HomeBase base) {
        agentCount++;
        name = "Agent_" + agentCount;
        currentNode = base;
    }

    public Node getCurrentNode()
    {
        return currentNode;
    }

    public String getName()
    {
        return name;
    }

    public String getLastNodeVisited() {return lastNodeVisited;}

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
            case CLONE_AGENT:
                break;
        }
    }
}
