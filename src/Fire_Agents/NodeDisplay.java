package Fire_Agents;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
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

    private BorderPane graphGrid;
    private FlowPane graphLoaderButtons;
    private Group graphGroup = new Group();
    private ObservableList<javafx.scene.Node> observableList = graphGroup.getChildren();


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
        graph.graphInitialization("default");
        graphList = graphLoader.graphList("Fire_Agents/graphs");

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
            startNodeThreads();
        });

        ToggleGroup optionsGroup = new ToggleGroup();
        startNodes.setToggleGroup(optionsGroup);
        getGraphs.setToggleGroup(optionsGroup);
        graphSelector.getChildren().addAll(changeGraph, getGraphs, startNodes);

        graphGrid = new BorderPane();
        graphGrid.setTop(graphGroup);
        graphGrid.setCenter(graphLoaderButtons);
        graphGrid.setBottom(graphSelector);

        Scene background = new Scene(graphGrid, 1000, 1000);
        window.setResizable(false);
        window.setScene(background);
        window.show();
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

    private void startNodeThreads() {
        Button startButton = new Button("Start Fire!");
        startButton.setOnAction(event -> {
            graph.startThreads();
        });
    }

}
