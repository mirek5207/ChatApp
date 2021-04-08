package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class RegisterController extends SupportForIconButtons {

    private static Scene scene;

    @FXML
    void handleRegisterButtonAction() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/sample.fxml"));
            Parent root1 = fxmlLoader.load();
            scene = new Scene(root1,Main.width,Main.height);
            scene.getStylesheets().add(getClass().getResource("../gui/style.css").toExternalForm());
            Main.stage.setScene(scene);
            Main.stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void handleExitButtonAction(MouseEvent event) {
        exitWindow();
    }
    @FXML
    void handleMinimizeButtonAction(MouseEvent event) { minimizeWindow(); }

}
