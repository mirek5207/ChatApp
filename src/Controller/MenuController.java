package Controller;

import DataBase.DataBase;
import Other.DynamicElementGuiBuilder;
import ServerClient.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


public class MenuController implements Initializable {


    @FXML private TextField searchedUserName;
    @FXML private TextField userName;
    @FXML private VBox vBoxForSearchedUserNames;
    @FXML private VBox vBoxListOfFriends;


   /* @FXML void handleSendMessage() {
        Client client = Client.getInstance();
        String login = client.getLogin();
        textArea.setText(login + "  " + textField.getText());
        client.sendMessage(textField.getText());
    }*/

    //method is used when searching for a friend to add him to friends
    @FXML void searchUserByLogin() {
        vBoxForSearchedUserNames.getChildren().clear();
        List<String> searchedUsersList;
        Client client = Client.getInstance();
        searchedUsersList= client.getListOfSearchedUsers(searchedUserName.getText());
        DynamicElementGuiBuilder elementGuiBuilder = new DynamicElementGuiBuilder();
        elementGuiBuilder.createButtons(searchedUsersList,"addUserButton",vBoxForSearchedUserNames,"addFriendship");
        client.getListOfFriends();
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        Client client = Client.getInstance();
        userName.setText("#"+client.getLogin());
        loadListOfFriends(client);
    }
    private void loadListOfFriends(Client client){
        List<String> listOfFriends = client.getListOfFriends();
        DataBase dataBase = new DataBase();
        DynamicElementGuiBuilder elementGuiBuilder = new DynamicElementGuiBuilder();
        elementGuiBuilder.createButtons(listOfFriends,"friendButton",vBoxListOfFriends,"openPrivateConversation");
        dataBase.closeConnection();

    }


}
