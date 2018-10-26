package Fire_Agents;

/*
just gonna use this file to instantiate our gui so we can have multiple instances
also could be used to test if everything is working.
 */
public class Main {

    public static void main(String[] args) {

        // Create test nodes
        Node node1 = new Node(0,0,Node.State.SAFE, null);
        Node node2 = new Node(0,0,Node.State.SAFE, null);

        // Create threads
        Thread nodeThread1 = new Thread(node1);
        Thread nodeThread2 = new Thread(node2);

        // Start node threads
        nodeThread1.start();
        nodeThread2.start();

        // Send a test message
        node1.sendMessage(new Message(Message.MessageType.CREATE_AGENT), node2);

    }
}
