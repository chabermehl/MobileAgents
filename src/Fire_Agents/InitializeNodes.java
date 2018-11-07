package Fire_Agents;

import java.util.LinkedList;

public class InitializeNodes {

    private int largestRow;
    private int largetCol;
    private LinkedList<Node> nodes;
    private LinkedList<Edge> edges;
    private boolean validGraph;

    public boolean initializeGraph(String mapFile) {
        validGraph = true;
        nodes = new LinkedList<>();
        edges = new LinkedList<>();
        largetCol = 0;
        largestRow = 0;
        TextFileReader textReader = new TextFileReader();
        LinkedList<String> fileLines = textReader.FileToList(mapFile);
        for(String line : fileLines) {
            String[] lines = line.split(" ");
            for(int i = 0; i < lines.length; i++) {
                if("station".equals(lines[0])) {
                    HomeBase station = new HomeBase();
                    station.setX(Integer.parseInt(lines[1]));
                    station.setY(Integer.parseInt(lines[2]));
                    nodes.add(station);
                }
                else if("node".equals(lines[0])) {
                    Node node = new Node(Integer.parseInt(lines[1]), Integer.parseInt(lines[2]), new LinkedList<>());
                    nodes.add(node);
                }
                else if("edge".equals(lines[0])) {

                }
                else if("fire".equals(lines[0])) {

                } else {
                    System.out.println("Invalid Config File\n");
                }
            }
        }
        return validGraph;
    }
}
