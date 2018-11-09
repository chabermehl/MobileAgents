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

/**
 * this class handles calling of node initializations and building the GUI based
 * on them
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
        //animation timer to update nodes on gui
        AnimationTimer a = new AnimationTimer() {
            private long startTime = -1;

            @Override
            public void handle(long now) {
                if (startTime < 0) {
                    startTime = now;
                }

                Duration elapsed = Duration.ofNanos(now - startTime);
                double seconds = elapsed.toMillis() / 1000.0;
                if (seconds >= stepSpeed) {
                    updateGUI();
                    startTime = -1;
                }
            }
        };

        a.start();
    }

    /**
     * updates the nodes on the GUI based on current state
     */
    private void updateGUI() {
        for (Node n : nodes) {
            Circle circle = new Circle(15);
            circle.setCenterX((n.getX() + 10) * 100);
            circle.setCenterY((n.getY() + 10) * 100);
            if (n.getName().equals("HomeBase") && n.getState().equals(Node.State.FIRE)) {
                circle.setFill(Color.ORANGE);
            } else if (n.getName().equals("HomeBase")) {
                circle.setFill(Color.BLUE);
            } else if (n.hasAgent()) {
                circle.setFill(Color.BLACK);
            } else if (n.getState().equals(Node.State.DANGER)) {
                circle.setFill(Color.YELLOW);
            } else if (n.getState().equals(Node.State.FIRE)) {
                circle.setFill(Color.RED);
            } else {
                circle.setFill(Color.GREEN);
            }
            observableList.add(circle);
        }
    }

    /**
     * handles all the pieces of the GUI
     */
    private void startScene() {
        graph = new InitializeGraph();
        graph.graphInitialization("default");

        Button startNodes = new Button("Start Fire!");
        startNodes.setPrefSize(200, 100);
        startNodes.setStyle("-fx-background-color: red;" +
                "-fx-text-fill: black;" +
                "-fx-border-color: black;");
        startNodes.setOnAction(event -> {
            graph.startThreads();
        });

        makeGraph();
        neighborLabel();

        graphGrid = new BorderPane();
        graphGrid.setCenter(graphGroup);
        graphGrid.setBottom(startNodes);
        graphGrid.setRight(neighborPane);


        Scene background = new Scene(graphGrid, 1000, 1000);
        window.setScene(background);
        window.show();
    }

    /**
     * builds the node graph with all the edges.
     */
    private void makeGraph() {
        nodes = graph.getNodes();
        for (Node tempNode : nodes) {
            Circle circle = new Circle(15);
            circle.setCenterX((tempNode.getX() + 10) * 100);
            circle.setCenterY((tempNode.getY() + 10) * 100);
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

    /**
     * creates the label on the right hand side that shows the neighbors each node has
     */
    private void neighborLabel() {
        LinkedList<Node> nodes;
        LinkedList<Node> neighbors;
        LinkedList<String> neighborNames;
        neighborPane = new GridPane();
        neighborPane.setPadding(new Insets(5, 15, 5, 15));
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
            for (String str : neighborNames) {
                Label neighborName = new Label(str);
                neighborName.setPrefSize(200, 25);
                neighborPane.add(neighborName, 1, counter);
                counter++;
            }

        }
    }

}
