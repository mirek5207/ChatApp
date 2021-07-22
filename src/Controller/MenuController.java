package Controller;

import DataBase.DataBase;
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
        List<Button> userList = new LinkedList<>();
        List<String> userData;
        DataBase dataBase = new DataBase();
        userData=dataBase.searchUserByLogin(searchedUserName.getText());
        Client client = Client.getInstance();
        String userLogin = client.getLogin();

        for(int i=0;i<userData.size();i++){
            String friendLogin =userData.get(i);
            Button button = new Button();
            button.setText(friendLogin);
            button.getStyleClass().add("addUserButton");
            button.setOnAction((ActionEvent e) -> dataBase.addFriendship(userLogin,friendLogin));
            userList.add(button);
            vBoxForSearchedUserNames.getChildren().add(userList.get(i));
        }
        client.getListOfFriends();
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        Client client = Client.getInstance();
        userName.setText("#"+client.getLogin());
        loadListOfFriends(client);
    }
    private void loadListOfFriends(Client client){
        List<String> listOfFriends = new LinkedList<>();
        listOfFriends = client.getListOfFriends();
        List<Button> friendsList = new LinkedList<>();
        DataBase dataBase = new DataBase();

        for(int i=0;i<listOfFriends.size();i++){
            String friendLogin =listOfFriends.get(i);
            Button button = new Button();
            button.setText(friendLogin);
            button.getStyleClass().add("friendButton");
            //button.setOnAction((ActionEvent e) -> dataBase.addFriendship(userLogin,friendLogin));
            friendsList.add(button);
            vBoxListOfFriends.getChildren().add(friendsList.get(i));
        }
        dataBase.closeConnection();

    }

}
