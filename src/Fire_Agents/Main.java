package Fire_Agents;

import java.util.Arrays;
import java.util.LinkedList;

/*
just gonna use this file to instantiate our gui so we can have multiple instances
also could be used to test if everything is working.
 */

// Questions:

// 1. Should I create a fire class and have it spread to nodes, or just run a timer
// on yellow nodes and set their state to "on fire" after so much time

// 2. Where should I store node threads? Can I do that in the main class, and run them all
// once the map has been loaded?

// 3. Maybe every node should just start in "Safe" mode, and the node that should start on fire will
// start the chain of events.

// 4. Are agents supposed to run at different rates? (ex: Move closer to fire every second

// 5. Or is everything based on just the fire?

public class Main {

    public static void main(String[] args) {

        // Test Agent
        Agent agent = new Agent(null);

        // Create test nodes
        Node node1 = new Node(0,0, Node.State.SAFE, new LinkedList<>());
        Node node2 = new Node(0,0, Node.State.SAFE, new LinkedList<>());

        node1.addNeighbor(node2);

        // Create threads
        Thread nodeThread1 = new Thread(node1);
        Thread nodeThread2 = new Thread(node2);
        Thread agentThread = new Thread(agent);

        // Start node threads
        nodeThread1.start();
        nodeThread2.start();
        agentThread.start();

        node1.setState(Node.State.FIRE);

    }
}
