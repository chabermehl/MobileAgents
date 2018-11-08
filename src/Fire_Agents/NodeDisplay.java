package Fire_Agents;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.util.LinkedList;

/*
GUI code goes here
 */
public class NodeDisplay extends Application {

    private Stage window;
    private LinkedList<Node> nodes;
    private LinkedList<Edge> edges;
    private LinkedList<String> graphList;
    private InitializeGraph graph;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Mobile Agents");
        startScene();
    }

    private void startScene() {
        GraphLoader graphLoader = new GraphLoader();
        graph = new InitializeGraph();
        graph.graphInitialization("graphs/default");
        final File folder = new File(getClass().getClassLoader().getResource("graphs").getFile());
        graphList = graphLoader.graphList(folder);

    }

    private void sendAgent() {

    }

}
