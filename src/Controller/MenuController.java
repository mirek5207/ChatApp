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


public class MenuController implements Initializable,InnerController {


    @FXML private TextField searchedUserName;
    @FXML private TextField userName;
    @FXML private VBox vBoxForSearchedUserNames;
    @FXML private VBox vBoxListOfFriends;
    @FXML private VBox activeUsers;
    @FXML private VBox notActiveUsers;

    @FXML
    public void addGroup(ActionEvent event) {
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
        loadListOfOnlineFriends(client,elementGuiBuilder);
        loadListOfOfflineFriends(client,elementGuiBuilder);
    }
    private void loadListOfFriends(Client client,DynamicElementGuiBuilder elementGuiBuilder){
        List<String> listOfFriends = client.getListOfFriends();
        DataBase dataBase = new DataBase();
        elementGuiBuilder.createButtons(listOfFriends,"friendButton",vBoxListOfFriends,"openPrivateConversation");
        dataBase.closeConnection();
    }
    private void loadListOfOnlineFriends(Client client,DynamicElementGuiBuilder elementGuiBuilder){
        List<String> onlineListOfFriends = client.getListOfOnlineFriends();
        if(onlineListOfFriends.size()>0){
            DataBase dataBase = new DataBase();
            elementGuiBuilder.createButtons(onlineListOfFriends,"onlineUser",activeUsers,"openPrivateConversation");
            dataBase.closeConnection();
        }

    }
    private void loadListOfOfflineFriends(Client client,DynamicElementGuiBuilder elementGuiBuilder){
        List<String> offlineListOfFriends = client.getListOfOfflineFriends();
        System.out.println("Lista offline użytkowników:");
        for(int i = 0 ;i< offlineListOfFriends.size();i++){
            System.out.println(offlineListOfFriends.size());
            System.out.println(offlineListOfFriends.get(i));
        }
        if(offlineListOfFriends.size()>0){
            DataBase dataBase = new DataBase();
            elementGuiBuilder.createButtons(offlineListOfFriends,"offlineUser",notActiveUsers,"openPrivateConversation");
            dataBase.closeConnection();
        }
    }
    @FXML
    public void logout(ActionEvent event) {
        Client client = Client.getInstance();
        client.logout();
        System.exit(0);
    }


}
