package sample;

import Other.SceneChanger;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        SceneChanger sceneChanger = new SceneChanger("../gui/fxml/login.fxml","../gui/css/login.css");
        sceneChanger.changeScene();
    }

    public static void main(String[] args) {
        launch(args);

    }

}

