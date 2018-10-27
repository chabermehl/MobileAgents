package Fire_Agents;

public class Agent implements MessageSenderReciever {

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
    public void processMessage(Message message) {

    }

    @Override
    public void sendMessage(Message message, MessageSenderReciever receiver) {

    }

    @Override
    public void recieveMessage(Message message) {

    }
}
