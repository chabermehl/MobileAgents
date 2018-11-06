package Fire_Agents;

public class Agent extends MessageProcessor implements Runnable {

    private String name;
    private Node currentNode;
    private HomeBase homeBase;
    private static int agentCount = 0;

    public Agent(HomeBase base) {
        agentCount++;
        name = "Agent_" + agentCount;
        currentNode = base;
        homeBase = base;
    }

    public Node getCurrentNode()
    {
        return currentNode;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public void run() {
        while(true) {
            try {
                processMessage(waitNextMessage());
            } catch (InterruptedException e) {
                System.out.println(this.name + "'s thread was interrupted.");
                e.printStackTrace();
            }
        }
    }

    public void processMessage(Message message) {

    }

}
