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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.File;
import java.util.LinkedList;

/*
GUI code goes here
 */
public class NodeDisplay extends Application {

    private Stage window;
    private LinkedList<Node> nodes;
    private LinkedList<Node> neighbors;
    //private LinkedList<String> graphList;
    private InitializeGraph graph;

    private BorderPane graphGrid;
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
        graph = new InitializeGraph();
        graph.graphInitialization("default");

        Button startNodes = new Button("Start Fire!");
        startNodes.setOnAction(event -> {
            startNodeThreads();
        });

        makeGraph();

        graphGrid = new BorderPane();
        graphGrid.setCenter(graphGroup);
        graphGrid.setBottom(startNodes);


        Scene background = new Scene(graphGrid, 1000, 1000);
        window.setResizable(false);
        window.setScene(background);
        window.show();
    }

    private void makeGraph() {
        nodes = graph.getNodes();
        for(Node tempNode : nodes) {
            Circle circle = new Circle(10);
            circle.setCenterX((tempNode.getX()+10)*100);
            circle.setCenterY((tempNode.getY()+10)*100);
            System.out.println(tempNode.getX());
            System.out.println(tempNode.getY());
            if(tempNode.getName().equals("HomeBase")) {
                System.out.println("BLUE");
                circle.setFill(Color.BLUE);
            }
            else if(tempNode.getState().equals(Node.State.FIRE)) {
                circle.setFill(Color.RED);
                System.out.println("RED");
            } else {
                circle.setFill(Color.GREEN);
                System.out.println("GREEN");
            }
            neighbors = tempNode.getNeighbors();
            for(Node neighbor : neighbors) {
                Line newEdge = new Line((tempNode.getX()+10)*100, (tempNode.getY()+10)*100, (neighbor.getX()+10)*100, (neighbor.getY()+10)*100);
                observableList.add(newEdge);
            }

            observableList.add(circle);
        }

    }

//    private void loadGraph(LinkedList<String> list) {
//        for(String buttonName : list) {
//            Button button = new Button(buttonName);
//            button.setId(buttonName);
//            button.setOnAction(event -> {
//                makeGraph("graphs/" + button.getId());
//            });
//            graphLoaderButtons.setPadding(new Insets(0, 10, 0, 10));
//            graphLoaderButtons.getChildren().add(button);
//        }
//    }

    private void startNodeThreads() {
        Button startButton = new Button("Start Fire!");
        startButton.setOnAction(event -> {
            graph.startThreads();
        });
    }

    private void neighborLabel() {
        LinkedList<Node> nodes;
        LinkedList<Edge> edges;
        LinkedList<Node> neighbors;
        InitializeGraph graph;

        graph = new InitializeGraph();
        graph.graphInitialization("default");

        nodes = graph.getNodes();
        edges = graph.getEdges();

        for(Node tempNode : nodes) {
            System.out.println("Name of Node");
            System.out.println(tempNode.getName());
            neighbors = tempNode.getNeighbors();
            System.out.println("Starting Neighbors");
            for(Node tempNode2 : neighbors) {
                System.out.println(tempNode2.getName());
            }
        }
    }

}
