package Fire_Agents;

import java.util.LinkedList;

public class InitializeNodes {

    public int largestRow = 0;
    public int largestCol = 0;
    public LinkedList<Node> nodes;
    public LinkedList<Edge> edges;

    /**
     * initializes all the nodes and edges for the gui
     *
     * @param mapFile config file that is passed in
     */
    public void initializeGraph(String mapFile) {
        nodes = new LinkedList<>();
        edges = new LinkedList<>();
        TextFileReader textReader = new TextFileReader();
        LinkedList<String> fileLines = textReader.FileToList(mapFile);
        for (String line : fileLines) {
            String[] lines = line.split(" ");
            if ("station".equals(lines[0])) {
                HomeBase station = new HomeBase(Integer.parseInt(lines[1]), Integer.parseInt(lines[2]));
                nodes.add(station);
            } else if ("node".equals(lines[0])) {
                boolean alreadySet = false;
                for(Node testNode : nodes) {
                    if(testNode.getX() == Integer.parseInt(lines[1]) && testNode.getY() == Integer.parseInt(lines[2])) {
                        alreadySet = true;
                    }
                }
                if(!alreadySet) {
                    Node node = new Node(Integer.parseInt(lines[1]), Integer.parseInt(lines[2]));
                    nodes.add(node);
                }
            } else if ("edge".equals(lines[0])) {
                Edge edge = new Edge(Integer.parseInt(lines[1]), Integer.parseInt(lines[2]), Integer.parseInt(lines[3]), Integer.parseInt(lines[4]));
                edges.add(edge);
            } else if ("fire".equals(lines[0])) {
                if (nodes.isEmpty()) {
                    Node node = new Node(Integer.parseInt(lines[1]), Integer.parseInt(lines[2]), Node.State.FIRE);
                    nodes.add(node);
                } else {
                    boolean notFound = true;
                    for (Node tempNode : nodes) {
                        if ((tempNode.getX() == Integer.parseInt(lines[1])) && (tempNode.getY() == Integer.parseInt(lines[2]))) {
                            tempNode.setStartingState(Node.State.FIRE);
                            notFound = false;
                        }
                    }
                    if (notFound) {
                        Node node = new Node(Integer.parseInt(lines[1]), Integer.parseInt(lines[2]), Node.State.FIRE);
                        nodes.add(node);
                    }
                }
            } else {
                System.out.println("Invalid Config File\n");
            }
        }
    }

    /**
     * goes through all of the nodes checking to see if they have any neighbors
     * and adding those neighbors to their neighbor lists
     */
    public void buildNeighborLists() {
        for (Node tempNode : nodes) {
            if(tempNode.getX() > largestRow) {
                largestRow = tempNode.getX();
            }
            if(tempNode.getY() > largestCol) {
                largestCol = tempNode.getY();
            }
            for (Edge tempEdge : edges) {
                if ((tempNode.getX() == tempEdge.getStartX()) && (tempNode.getY() == tempEdge.getStartY())) {
                    for (Node tempNode2 : nodes) {
                        if ((tempNode2.getX() == tempEdge.getEndX()) && tempNode2.getY() == tempEdge.getEndY()) {
                            tempNode.addNeighbor(tempNode2);
                        }
                    }
                }
            }
        }
    }

    public LinkedList<Node> getNodes() {
        return nodes;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public int getLargestCol() {
        return largestCol;
    }

    public int getLargestRow() {
        return largestRow;
    }
}
