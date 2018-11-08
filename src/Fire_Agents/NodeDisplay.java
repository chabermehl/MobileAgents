package Fire_Agents;

import javafx.application.Application;
import javafx.stage.Stage;

/*
GUI code goes here
 */
public class NodeDisplay extends Application {

    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Mobile Agents");
        startScene();
    }

    public void startScene() {

    }
}
