package Other;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Main;
import javafx.geometry.Rectangle2D;
import java.io.IOException;
import java.util.Objects;

public class SceneChanger  {
    private final String pathScene;
    private final String pathCss;

    public SceneChanger (String pathToScene,String pathToCss){
        this.pathScene = pathToScene;
        this.pathCss =  pathToCss;
    }
    public void changeScene()
    {
        // get screensize of monitor
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pathScene));
            Parent root1 = fxmlLoader.load();
            Scene scene = new Scene(root1,screenSize.getWidth(),screenSize.getHeight());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(pathCss)).toExternalForm());
            Main.stage.setMaximized(true);
            Main.stage.setScene(scene);
            Main.stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createSecondStage(){
        Stage stage2 = new Stage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pathScene));
            Parent root2 = fxmlLoader.load();
            Scene scene2 = new Scene(root2, 411, 400);
            scene2.getStylesheets().add(Objects.requireNonNull(getClass().getResource(pathCss)).toExternalForm());
            stage2.setScene(scene2);
            stage2.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
