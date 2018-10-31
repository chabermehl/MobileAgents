package Fire_Agents;

import java.util.Arrays;
import java.util.LinkedList;

/*
just gonna use this file to instantiate our gui so we can have multiple instances
also could be used to test if everything is working.
 */

// 2. Where should I store node threads? Can I do that in the main class, and run them all
// once the map has been loaded?

// 3. Maybe every node should just start in "Safe" mode, and the node that should start on fire will
// start the chain of events.

public class Main {

    public static void main(String[] args) {

        // Create test nodes
        Node node1 = new Node(0,0, new LinkedList<>());
        Node node2 = new Node(0,0, new LinkedList<>());
        Agent agent = new Agent(node1);


        node1.addNeighbor(node2);

        // Create threads
        Thread nodeThread1 = new Thread(node1);
        Thread nodeThread2 = new Thread(node2);
        Thread agentThread = new Thread(agent);

        // Start node threads
        nodeThread1.start();
        nodeThread2.start();

        node1.setState(Node.State.FIRE);

    }
}
