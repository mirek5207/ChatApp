package sample;

import Other.SceneChanger;
import ServerClient.Client;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        SceneChanger sceneChanger = new SceneChanger("../gui/fxml/login.fxml","../gui/css/login.css");
        sceneChanger.changeScene();
        Client client = Client.getInstance();
        stage.setOnCloseRequest(e->client.logout());
    }

    public static void main(String[] args) {
        launch(args);

    }

}

