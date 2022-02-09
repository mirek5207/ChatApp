package Controller;

import Other.SceneChanger;
import Other.SupportForIconButtons;
import ServerClient.Client;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;


public class LoginController extends SupportForIconButtons {

    @FXML private TextField login;
    @FXML private PasswordField password;
    @FXML void handleRegisterButtonAction()
    {
        SceneChanger sceneChanger = new SceneChanger("../gui/fxml/register.fxml","../gui/css/login.css");
        sceneChanger.changeScene();
    }
    @FXML void handleLoginButtonAction() throws IOException {
        Client client = Client.getInstance();
        client.initializeReadWriteBuffer();
        if(client.checkSignInData(login.getText(), password.getText())){
            client.setLogin(login.getText());
            SceneChanger sceneChanger = new SceneChanger("../gui/fxml/menu.fxml","../gui/css/style.css");
            sceneChanger.changeScene();
        }
    }



}
