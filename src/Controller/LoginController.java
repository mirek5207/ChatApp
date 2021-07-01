package Controller;

import DataBase.DataBase;
import Other.SceneChanger;
import Other.SupportForIconButtons;
import ServerClient.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.Main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class LoginController extends SupportForIconButtons {

    @FXML private TextField login;
    @FXML private TextField password;
    @FXML void handleRegisterButtonAction()
    {
        SceneChanger sceneChanger = new SceneChanger("../gui/register.fxml","../gui/login.css");
        sceneChanger.changeScene();
    }
    @FXML void handleLoginButtonAction() throws IOException {
        InetAddress ip = InetAddress.getByName("localhost");
        Socket socket = new Socket(ip, 5056);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        DataBase connection = new DataBase();
        if(connection.searchData(login.getText(), password.getText())) {
            Client client = Client.getInstance();
            client.setData(login.getText(), printWriter);
            SceneChanger sceneChanger = new SceneChanger("../gui/menu.fxml","../gui/style.css");
            sceneChanger.changeScene();
        }

        connection.closeConnection();
    }

    @FXML
    void menuButtonClicked() {


    }

}
