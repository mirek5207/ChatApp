package Controller;

import DataBase.DataBase;
import Other.DynamicElementGuiBuilder;
import Other.SceneChanger;
import ServerClient.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MenuController implements Initializable {


    @FXML private TextField searchedUserName;
    @FXML private TextField userName;
    @FXML private VBox vBoxForSearchedUserNames;
    @FXML private VBox vBoxListOfFriends;

    @FXML
    void addGroup(ActionEvent event) {
        SceneChanger sceneChanger = new SceneChanger("../gui/fxml/group.fxml","../gui/css/group.css");
        sceneChanger.createSecondStage();
    }

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
        DynamicElementGuiBuilder elementGuiBuilder = new DynamicElementGuiBuilder();
        userName.setText("#"+client.getLogin());
        loadListOfFriends(client,elementGuiBuilder);
    }
    private void loadListOfFriends(Client client,DynamicElementGuiBuilder elementGuiBuilder){
        List<String> listOfFriends = client.getListOfFriends();
        DataBase dataBase = new DataBase();
        elementGuiBuilder.createButtons(listOfFriends,"friendButton",vBoxListOfFriends,"openPrivateConversation");
        dataBase.closeConnection();
    }
    @FXML
    void logout(ActionEvent event) {
        System.exit(0);
    }

}
