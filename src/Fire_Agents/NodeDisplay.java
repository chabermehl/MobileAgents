package Fire_Agents;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.LinkedList;

/*
GUI code goes here
 */
public class NodeDisplay extends Application {

    private Stage window;
    private LinkedList<Node> nodes;
    private LinkedList<Edge> edges;
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
        graph = new InitializeGraph();
        graph.graphInitialization("graphs/default");

    }

    private void sendAgent() {

    }

}
