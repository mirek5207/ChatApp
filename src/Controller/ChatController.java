package Controller;


import ServerClient.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

import java.net.URL;
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
    void addGroup(ActionEvent event) {
        super.addGroup(event);
    }

    public void sendMessage(ActionEvent actionEvent) {
        Client client = Client.getInstance();
        client.sendMessageToChat("Przesyłam wiadomosć");
    }
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);
        Client client = Client.getInstance();
        displayMessages(client);
    }
    private void displayMessages(Client client){
        List <String> list = client.getChatMessages();
        textArea.clear();
        for(int i = 0 ; i<list.size(); i ++){
            textArea.setText(textArea.getText() + "\n\n" + list.get(i));
        }

    }
}
