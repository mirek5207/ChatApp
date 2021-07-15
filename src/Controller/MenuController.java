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
    @FXML private VBox listOfFriends;


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
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        Client client = Client.getInstance();
        userName.setText("#"+client.getLogin());
        loadListOfFriends(client.getLogin());
    }
    private void loadListOfFriends(String login){
        List<Integer> idOfFriends;
        List<String> loginFriends = new LinkedList<>();
        List<Button> friendsList = new LinkedList<>();
        DataBase dataBase = new DataBase();
        Integer userId = dataBase.getIdOfUser(login);
        System.out.println("idUser:"+ userId);
        idOfFriends = dataBase.searchIdOfFriends(userId);
        for(int i = 0 ; i<idOfFriends.size();i++ )
        {
            loginFriends.add(dataBase.searchUserById(idOfFriends.get(i)));
            System.out.println(loginFriends.get(i));
        }
        for(int i=0;i<loginFriends.size();i++){
            String friendLogin =loginFriends.get(i);
            Button button = new Button();
            button.setText(friendLogin);
            button.getStyleClass().add("friendButton");
            //button.setOnAction((ActionEvent e) -> dataBase.addFriendship(userLogin,friendLogin));
            friendsList.add(button);
            listOfFriends.getChildren().add(friendsList.get(i));
        }
        dataBase.closeConnection();

    }

}
