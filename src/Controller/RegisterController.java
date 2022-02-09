package Controller;
import DataBase.DataBase;
import Other.SceneChanger;
import Other.SupportForIconButtons;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterController extends SupportForIconButtons {

    @FXML private TextField login;
    @FXML private TextField password;
    @FXML private TextField email;

    @FXML void handleSignUpButtonAction() {
        DataBase connect = new DataBase();
        connect.addData("User",login.getText(),password.getText(),email.getText());
        connect.closeConnection();
        SceneChanger sceneChanger = new SceneChanger("../gui/fxml/login.fxml","../gui/css/login.css");
        sceneChanger.changeScene();

    }

}
