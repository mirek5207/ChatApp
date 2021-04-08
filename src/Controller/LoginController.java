package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Main;

import java.io.IOException;


public class LoginController extends SupportForIconButtons {
    private static Scene scene;
    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    void handleRegisterButtonAction(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/register.fxml"));
            Parent root1 = fxmlLoader.load();
            scene = new Scene(root1,Main.width,Main.height);
            scene.getStylesheets().add(getClass().getResource("../gui/login.css").toExternalForm());
            Main.stage.setScene(scene);
            Main.stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void handleLoginButtonAction(ActionEvent event) {
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
