package Fire_Agents;

import java.util.LinkedList;

public class InitializeNodes {

    public int largestRow;
    public int largetCol;
    public LinkedList<Node> nodes;
    public LinkedList<Edge> edges;
    public boolean validGraph;

    /**
     * initializes all the nodes and edges for the gui
     * @param mapFile config file that is passed in
     */
    //TODO: fix unhandled exceptions
    public void initializeGraph(String mapFile) {
        validGraph = true;
        nodes = new LinkedList<>();
        edges = new LinkedList<>();
        largetCol = 0;
        largestRow = 0;
        TextFileReader textReader = new TextFileReader();
        LinkedList<String> fileLines = textReader.FileToList(mapFile);
        for (String line : fileLines) {
            String[] lines = line.split(" ");
            for (int i = 0; i < lines.length; i++) {
                if ("station".equals(lines[0])) {
                    HomeBase station = new HomeBase();
                    station.setX(Integer.parseInt(lines[1]));
                    station.setY(Integer.parseInt(lines[2]));
                    nodes.add(station);
                } else if ("node".equals(lines[0])) {
                    Node node = new Node(Integer.parseInt(lines[1]), Integer.parseInt(lines[2]), new LinkedList<>());
                    nodes.add(node);
                } else if ("edge".equals(lines[0])) {
                    Edge edge = new Edge(Integer.parseInt(lines[1]), Integer.parseInt(lines[2]), Integer.parseInt(lines[3]), Integer.parseInt(lines[4]));
                    edges.add(edge);
                } else if ("fire".equals(lines[0])) {
                    if (nodes.isEmpty()) {
                        Node node = new Node(Integer.parseInt(lines[1]), Integer.parseInt(lines[2]), new LinkedList<>());
                        //need to handle the exception in setState
                        node.setState(Node.State.FIRE);
                        nodes.add(node);
                    } else {
                        boolean notFound = true;
                        for (Node tempNode : nodes) {
                            if ((tempNode.getX() == Integer.parseInt(lines[1])) && (tempNode.getY() == Integer.parseInt(lines[2])) && (notFound)) {
                                tempNode.setState(Node.State.FIRE);
                                notFound = false;
                            }
                        }
                        if (!notFound) {
                            Node node = new Node(Integer.parseInt(lines[1]), Integer.parseInt(lines[2]), new LinkedList<>());
                            node.setState(Node.State.FIRE);
                            nodes.add(node);
                        }
                    }
                } else {
                    System.out.println("Invalid Config File\n");
                }
            }
        }
    }
}
