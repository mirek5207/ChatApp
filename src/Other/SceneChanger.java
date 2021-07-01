package Other;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import sample.Main;

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
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pathScene));
            Parent root1 = fxmlLoader.load();
            Scene scene = new Scene(root1, (int) Screen.getPrimary().getBounds().getWidth(), (int) Screen.getPrimary().getBounds().getHeight());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(pathCss)).toExternalForm());
            Main.stage.setMaximized(true);
            Main.stage.setScene(scene);
            Main.stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
