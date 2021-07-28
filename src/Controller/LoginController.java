package Controller;

import DataBase.DataBase;
import Other.SceneChanger;
import Other.SupportForIconButtons;
import ServerClient.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;


public class LoginController extends SupportForIconButtons {

    @FXML private TextField login;
    @FXML private TextField password;
    @FXML void handleRegisterButtonAction()
    {
        SceneChanger sceneChanger = new SceneChanger("../gui/register.fxml","../gui/login.css");
        sceneChanger.changeScene();
    }
    @FXML void handleLoginButtonAction() throws IOException {
        Client client = Client.getInstance();
        client.initializeReadWriteBuffer();
        if(client.checkSignInData(login.getText(), password.getText())){
            client.setLogin(login.getText());
            SceneChanger sceneChanger = new SceneChanger("../gui/menu.fxml","../gui/style.css");
            sceneChanger.changeScene();
        }
    }

}
