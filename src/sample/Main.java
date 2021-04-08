package sample;
import Other.SceneChanger;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.initStyle(StageStyle.UNDECORATED);
        SceneChanger sceneChanger = new SceneChanger("../gui/login.fxml","../gui/login.css");
        sceneChanger.changeScene();
    }

    public static void main(String[] args) {
        launch(args);
        
    }

}

