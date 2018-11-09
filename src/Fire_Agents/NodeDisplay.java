package Fire_Agents;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
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

    private FlowPane graphLoaderButtons;


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

        graphLoaderButtons = new FlowPane();
        graphLoaderButtons.setAlignment(Pos.CENTER);
        Label changeGraph = new Label("Graph Options:   ");
        FlowPane graphSelector = new FlowPane();
        graphSelector.setAlignment(Pos.CENTER);

        RadioButton getGraphs = new RadioButton("Get Graph Options");
        getGraphs.setOnAction(event -> {
            graphLoaderButtons.getChildren().clear();
            loadGraph(graphList);
        });

        RadioButton startNodes = new RadioButton("Start Fire!");
        startNodes.setOnAction(event -> {
            graphLoaderButtons.getChildren().clear();
        });

        ToggleGroup optionsGroup = new ToggleGroup();
        startNodes.setToggleGroup(optionsGroup);
        getGraphs.setToggleGroup(optionsGroup);
        graphSelector.getChildren().addAll(changeGraph, getGraphs, startNodes);

    }

    private void makeGraph(String mapFile) {
        graph.graphInitialization(mapFile);
    }

    private void loadGraph(LinkedList<String> list) {
        for(String buttonName : list) {
            Button button = new Button(buttonName);
            button.setId(buttonName);
            button.setOnAction(event -> {
                makeGraph("graphs/" + button.getId());
            });
            graphLoaderButtons.setPadding(new Insets(0, 10, 0, 10));
            graphLoaderButtons.getChildren().add(button);
        }
    }

}
