package Controller;

import DataBase.DataBase;
import Other.SceneChanger;
import Other.SupportForIconButtons;
import ServerClient.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        DataBase connection = new DataBase();
        Client client = Client.getInstance();
        client.setData(printWriter, bufferedReader);
        if(client.checkSignInData(login.getText(), password.getText())){
            client.setLogin(login.getText());
            SceneChanger sceneChanger = new SceneChanger("../gui/menu.fxml","../gui/style.css");
            sceneChanger.changeScene();
        }
        connection.closeConnection();
    }

}
