package Controller;

import DataBase.DataBase;
import Other.SupportForIconButtons;
import ServerClient.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Main;

import java.io.IOException;
import java.net.Socket;

public class SampleController extends SupportForIconButtons {
    private String login;

    @FXML private TextArea textfield;
    @FXML private TextArea textArea;
    @FXML private TextField user;

    @FXML void handleExitButtonAction(MouseEvent event) {
        exitWindow();
    }
    @FXML void handleMinimizeButtonAction(MouseEvent event) { minimizeWindow(); }
    @FXML void handleSendMessage(MouseEvent event) throws IOException {
        Client client = (Client) Main.stage.getUserData();
        login = client.getLogin();
        textArea.setText(login + "  " + textfield.getText());
        client.sendMessage(textfield.getText());
    }
}
