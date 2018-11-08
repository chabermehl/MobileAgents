package Fire_Agents;

import java.util.Arrays;
import java.util.LinkedList;

/*
just gonna use this file to instantiate our gui so we can have multiple instances
also could be used to test if everything is working.
 */

public class Main {

    public static void main(String[] args) {

        // Create test nodes
        HomeBase homeBase = new HomeBase();
        Node node1 = new Node(2,0, new LinkedList<>());
        Node node2 = new Node(4,0, new LinkedList<>());
        Node node3 = new Node (6,0, new LinkedList<>());
        Node node4 = new Node (6, 2, new LinkedList<>());

        // Add neighbors
        homeBase.addNeighbor(node1);
        node1.addNeighbor(node2);
        node2.addNeighbor(node3);
        node2.addNeighbor(node4);
        node3.addNeighbor(node4);

        // Create threads
        Thread homeBaseThread = new Thread(homeBase);
        Thread nodeThread1 = new Thread(node1);
        Thread nodeThread2 = new Thread(node2);
        Thread nodeThread3 = new Thread(node3);
        Thread nodeThread4 = new Thread(node4);

        // Start node threads
        homeBaseThread.start();
        nodeThread1.start();
        nodeThread2.start();
        nodeThread3.start();
        nodeThread4.start();


        // Set the last node on fire
        node3.setState(Node.State.FIRE);
        homeBase.processMessage(new Message(Message.MessageType.CREATE_AGENT, "the all-knowing"));

    }
}
