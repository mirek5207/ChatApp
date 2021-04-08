package sample;

import Controller.SupportForIconButtons;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private static Scene scene;
    public static Stage stage;
    public static int width;
    public static int height;



    @Override
    public void start(Stage primaryStage) throws IOException {

        this.stage = primaryStage;
        this.width = (int) Screen.getPrimary().getBounds().getWidth();
        this.height = (int) Screen.getPrimary().getBounds().getHeight();
        Parent root = FXMLLoader.load(getClass().getResource("../gui/login.fxml"));
        scene = new Scene(root,width,height);
        scene.getStylesheets().add(getClass().getResource("../gui/login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
