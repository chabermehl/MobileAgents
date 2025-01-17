package Fire_Agents;

import java.util.LinkedList;

/**
 * ALL TEST CODE HERE
 */

public class Main {

    public static void main(String[] args) {
//        // Create test nodes
//        HomeBase homeBase = new HomeBase(0,0);
//        Node node1 = new Node(2,0);
//        Node node2 = new Node(4,0);
//        Node node3 = new Node (6,0, Node.State.FIRE);
//        Node node4 = new Node (6, 2);
//
//        // Add neighbors
//        homeBase.addNeighbor(node1);
//        node1.addNeighbor(node2);
//        node2.addNeighbor(node3);
//        node2.addNeighbor(node4);
//        node3.addNeighbor(node4);
//
//        // Create threads
//        Thread nodeThread1 = new Thread(node1);
//        Thread nodeThread2 = new Thread(node2);
//        Thread nodeThread3 = new Thread(node3);
//        Thread nodeThread4 = new Thread(node4);
//        Thread homeBaseThread = new Thread(homeBase);
//
//
//        // Start node threads
//        nodeThread1.start();
//        nodeThread2.start();
//        nodeThread3.start();
//        nodeThread4.start();
//        homeBaseThread.start();

        LinkedList<Node> nodes;
        LinkedList<Edge> edges;
        LinkedList<Node> neighbors;
        int largestCol = 0;
        int largestRow = 0;
        InitializeGraph graph;

        graph = new InitializeGraph();
        graph.graphInitialization("default");

        nodes = graph.getNodes();
        edges = graph.getEdges();
        largestCol = graph.getMaxCol();
        largestRow = graph.getMaxRow();

        for (Node tempNode : nodes) {
            System.out.println("Name of Node");
            System.out.println(tempNode.getName());
            neighbors = tempNode.getNeighbors();
            System.out.println("Starting Neighbors");
            for (Node tempNode2 : neighbors) {
                System.out.println(tempNode2.getName());
            }
        }

        System.out.println(largestCol);
        System.out.println(largestRow);


    }
}
