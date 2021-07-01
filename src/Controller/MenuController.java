package Controller;

import DataBase.DataBase;
import Other.SceneChanger;
import ServerClient.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import sample.Main;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


public class MenuController implements Initializable {

    @FXML private TextArea textField;
    @FXML private TextArea textArea;
    @FXML private TextField searchedUserName;
    @FXML private TextArea clientData;
    @FXML private TextField userName;
    @FXML private VBox VBoxForSearchedUserNames;


   /* @FXML void handleSendMessage() {
        Client client = Client.getInstance();
        String login = client.getLogin();
        textArea.setText(login + "  " + textField.getText());
        client.sendMessage(textField.getText());
    }*/

    @FXML
    void searchUserByLogin(MouseEvent event) {
        List<Button> userList = new LinkedList<>();
        List<String> userData = new LinkedList<>();
        DataBase dataBase = new DataBase();
        userData=dataBase.searchUserByLogin(searchedUserName.getText());
        dataBase.closeConnection();

        for(int i=0;i<userData.size();i++){
           Button button = new Button();
           button.setText(userData.get(i));
           button.getStyleClass().add("addUserButton");
           userList.add(button);
           VBoxForSearchedUserNames.getChildren().add(userList.get(i));
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Client client = Client.getInstance();
        userName.setText("#"+client.getLogin());
    }

}
