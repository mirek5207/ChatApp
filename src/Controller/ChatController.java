package Controller;

import java.util.Collections;

import Other.DynamicElementGuiBuilder;
import ServerClient.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


public class ChatController extends MenuController {
    @FXML
    private VBox vBoxListOfFriends;

    @FXML
    private TextField userName;

    @FXML
    private TextArea textArea;

    @FXML
    private TextArea textField;

    @FXML
    private TextArea clientData;

    @FXML
    private VBox vBoxChat;



    @FXML
    void addGroup(ActionEvent event) {
        super.addGroup(event);
    }


    @FXML
    void logout(ActionEvent event) {
        super.logout(event);
    }

    public void sendMessage(ActionEvent actionEvent) {
        Client client = Client.getInstance();
        client.sendMessageToChat(textField.getText());
        displayMessages(client);
        textField.clear();
    }
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);
        Client client = Client.getInstance();
        displayMessages(client);
    }
    private void displayMessages(Client client){
        List <String> list = new LinkedList<>();
        list = client.getChatMessages();
        DynamicElementGuiBuilder guiBuilder = new DynamicElementGuiBuilder();
        guiBuilder.creatTextBox(list,vBoxChat,"messageBox");
    }
}
