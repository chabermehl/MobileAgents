package Fire_Agents;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.time.Duration;
import java.util.LinkedList;

/*
GUI code goes here
 */
public class NodeDisplay extends Application {

    private Stage window;
    private LinkedList<Node> nodes;
    private LinkedList<Node> neighbors;
    private InitializeGraph graph;

    private BorderPane graphGrid;
    private GridPane neighborPane;
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


        double frameRate = 1;
        double stepSpeed = 1.0 / frameRate;
        // Start our core game loop
        AnimationTimer a = new AnimationTimer() {
            private long startTime = -1;
            @Override
            public void handle ( long now ) {
                if(startTime < 0)
                {
                    startTime = now;
                }

                Duration elapsed = Duration.ofNanos (now - startTime );
                double seconds = elapsed.toMillis() / 1000.0;
                if(seconds >= stepSpeed)
                {
                    updateShiz();
                    System.out.println("Updatinig this shiz");
                    startTime = -1;
                }
            }
        };

        a.start();
    }

    private void updateShiz()
    {
        for(Node n : nodes) {
            Circle circle = new Circle(10);
            circle.setCenterX((n.getX() + 10) * 100);
            circle.setCenterY((n.getY() + 10) * 100);
            if (n.getName().equals("HomeBase")) {
                circle.setFill(Color.BLUE);
            } else if (n.getState().equals(Node.State.FIRE)) {
                circle.setFill(Color.RED);
            } else if (n.getState().equals(Node.State.DANGER)) {
                circle.setFill(Color.YELLOW);
            } else {
                circle.setFill(Color.GREEN);
            }
            observableList.add(circle);
        }
    }

    private void startScene() {
        graph = new InitializeGraph();
        graph.graphInitialization("default");

        Button startNodes = new Button("Start Fire!");
        startNodes.setOnAction(event -> {
            graph.startThreads();
        });

        makeGraph();
        neighborLabel();

        graphGrid = new BorderPane();
        graphGrid.setCenter(graphGroup);
        graphGrid.setBottom(startNodes);
        graphGrid.setRight(neighborPane);


        Scene background = new Scene(graphGrid, 800, 800);
        window.setResizable(false);
        window.setScene(background);
        //graph.startThreads();
        window.show();
    }

    private void makeGraph() {
        nodes = graph.getNodes();
        for (Node tempNode : nodes) {
            Circle circle = new Circle(10);
            circle.setCenterX((tempNode.getX() + 10) * 100);
            circle.setCenterY((tempNode.getY() + 10) * 100);
            System.out.println(tempNode.getX());
            System.out.println(tempNode.getY());
            if (tempNode.getName().equals("HomeBase")) {
                circle.setFill(Color.BLUE);
            } else if (tempNode.getStartingState().equals(Node.State.FIRE)) {
                circle.setFill(Color.YELLOW);
            } else {
                circle.setFill(Color.GREEN);
            }
            neighbors = tempNode.getNeighbors();
            for (Node neighbor : neighbors) {
                Line newEdge = new Line((tempNode.getX() + 10) * 100, (tempNode.getY() + 10) * 100, (neighbor.getX() + 10) * 100, (neighbor.getY() + 10) * 100);
                observableList.add(newEdge);
            }
            observableList.add(circle);
        }

    }

    private void neighborLabel() {
        LinkedList<Node> nodes;
        LinkedList<Node> neighbors;
        LinkedList<String> neighborNames;
        neighborPane = new GridPane();
        neighborPane.setPadding(new Insets(5,15,5,15));
        nodes = graph.getNodes();
        int totalNeighbors = 0;
        int counter = 0;
        for (Node tempNode : nodes) {
            neighbors = tempNode.getNeighbors();
            neighborNames = tempNode.getNeighborNames();
            Label nodeName = new Label(tempNode.getName());
            nodeName.setPrefSize(100, 25);
            neighborPane.add(nodeName, 0, totalNeighbors);
            totalNeighbors += neighbors.size();
            for(String str : neighborNames) {
                Label neighborName = new Label(str);
                neighborName.setPrefSize(200, 25);
                neighborPane.add(neighborName,1,counter);
                counter++;
            }

        }
    }

}
