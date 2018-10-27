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
        Node node1 = new Node(0,0, Node.State.SAFE, new LinkedList<>());
        Node node2 = new Node(0,0, Node.State.SAFE, new LinkedList<>());

        node1.addNeighbor(node2);

        // Create threads
        Thread nodeThread1 = new Thread(node1);
        Thread nodeThread2 = new Thread(node2);

        // Start node threads
        nodeThread1.start();
        nodeThread2.start();

        node1.setState(Node.State.FIRE);

    }
}
