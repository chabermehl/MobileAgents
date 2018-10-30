package Fire_Agents;

public class Agent extends MessageProcessor implements Runnable {

    private String name;
    private Node currentNode;
    private static int agentCount = 0;

    public Agent(Node startingNode)
    {
        agentCount++;
        name = "Agent_" + agentCount;
        currentNode = startingNode;
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
        while (true) {
            try {
                processMessage(getNextMessage());
            } catch (InterruptedException e) {
                System.out.println(this.name + "'s messaging thread was interrupted.");
                e.printStackTrace();
            }
        }

    }

    @Override
    public void processMessage(Message message) {

    }

}
