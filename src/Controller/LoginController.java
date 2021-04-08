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

    private InetAddress ip;
    private Socket socket;
    private PrintWriter printWriter;

    @FXML private TextField login;
    @FXML private TextField password;
    @FXML void handleRegisterButtonAction()
    {
        SceneChanger sceneChanger = new SceneChanger("../gui/register.fxml","../gui/login.css");
        sceneChanger.changeScene();
    }
    @FXML void handleLoginButtonAction() throws IOException {
        ip = InetAddress.getByName("localhost");
        socket = new Socket(ip,5056);
        printWriter = new PrintWriter(socket.getOutputStream());
        DataBase connection = new DataBase();
        if(connection.searchData(login.getText(), password.getText())) {
            SceneChanger sceneChanger = new SceneChanger("../gui/sample.fxml","../gui/style.css");
            sceneChanger.changeScene();
            Client client = new Client(login.getText(),socket,printWriter);
            Main.stage.setUserData(client);
        }
        else {
            System.out.println("Dane nie poprawne");
        }
        connection.closeConnection();
    }
    @FXML void handleExitButtonAction()
    {
        exitWindow();
    }
    @FXML void handleMinimizeButtonAction() { minimizeWindow(); }
    @FXML void handleResizeButtonAction() { resizeWindow(); }
}
