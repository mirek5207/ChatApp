package ServerClient;

import DataBase.DataBase;
import javafx.scene.control.Button;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;


public class ClientHandler extends Thread{

    final BufferedReader bufferedReader;
    final PrintWriter printWriter;
    final Socket socket;
    private String login;
    private String friendLogin = null;
    private String message = null;
    private Integer userId;

    public ClientHandler(Socket socket, BufferedReader bufferedReader,PrintWriter printWriter) {
        this.socket = socket;
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
    }

    @Override
    public void run() {
        DataBase connection = new DataBase();
        String message ;
        checkSignInData(connection);
        while (true){
            try {
                message = bufferedReader.readLine();
            } catch (IOException e) {
                message = null;
            }

            switch (message){
                case "getId":
                    printWriter.println(connection.getIdOfUser(login));
                    printWriter.flush();
                    break;
                case "getListOfFriends":
                    List<Integer> idOfFriends;
                    List<String> listOfFriends = new LinkedList<>();
                    userId = connection.getIdOfUser(login);
                    idOfFriends = connection.searchIdOfFriends(userId);
                    for(int i = 0 ; i<idOfFriends.size();i++ )
                    {
                        listOfFriends.add(connection.searchUserById(idOfFriends.get(i)));
                    }
                    printWriter.println(listOfFriends);
                    printWriter.flush();
                    break;
                case "getListOfSearchedUsers":
                    String searchedUserLogin = null;
                    while(searchedUserLogin==null){
                        try {
                            searchedUserLogin = bufferedReader.readLine();
                            System.out.println("Szukana nazwa uzytkownika " + searchedUserLogin);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    printWriter.println(connection.searchUserByLogin(searchedUserLogin));
                    printWriter.flush();
                    break;
                case "addFriendship":
                    friendLogin = null;
                    while(friendLogin==null){
                        try {
                            friendLogin = bufferedReader.readLine();
                            System.out.println("Login przyjaciela : ClientHandler" +  friendLogin);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    connection.addFriendship(login,friendLogin);
                    connection.addGroup(login+"-"+friendLogin);
                    Integer idOfFriend = connection.getIdOfUser(friendLogin);
                    Integer groupId = connection.getIdGroup(login);
                    userId = connection.getIdOfUser(login);
                    connection.addRelationshipUserWidthGroup(groupId,userId);
                    connection.addRelationshipUserWidthGroup(groupId,idOfFriend);
                    friendLogin = null;
                    break;
                case "addGroup":

                    /*while(friendLogin==null) {
                        try {
                            friendLogin = bufferedReader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }*/

                    break;

                case "sendMessage":
                    friendLogin = null;
                    message = null;
                    while(friendLogin==null || message==null) {
                        try {
                            friendLogin = bufferedReader.readLine();
                            message = bufferedReader.readLine();
                            /*System.out.println("wiadomosc:" + message);
                            System.out.println("Drugi użytkwnik:" + friendLogin);*/

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    userId = connection.getIdOfUser(login);
                    groupId = connection.getIdGroup(login + "-" + friendLogin);
                    if(groupId == null) groupId = connection.getIdGroup(friendLogin + "-" + login);
                    System.out.println("grupa id : " + friendLogin + "-" + login);
                    connection.addMessage(message,userId,groupId);
                    break;
                case "loadChatMessages":
                    friendLogin = null;
                    List<String> chatMessages;
                    while(friendLogin==null){
                        try {
                            friendLogin = bufferedReader.readLine();
                            System.out.println("Login przyjaciela : ClientHandler" +  friendLogin);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    userId = connection.getIdOfUser(login);
                    groupId = connection.getIdGroup(login + "-" + friendLogin);
                    if(groupId == null) groupId = connection.getIdGroup(friendLogin + "-" + login);
                    userId = connection.getIdOfUser(login);
                    chatMessages = connection.getChatMessages(groupId);
                    System.out.println(chatMessages);
                    printWriter.println(chatMessages);
                    printWriter.flush();
                    break;
                case "logout":
                    connection.updateStatus("user", login,false);
                    break;
                default:
                    break;

            }
        }

    }
    private void checkSignInData(DataBase connection){
        String password;
        password = "";
        try {
            this.login = bufferedReader.readLine();
            password = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(connection.checkSignInData(login,password)){
            printWriter.println(true);
            connection.updateStatus("user", login, true);
        }
        else {
            printWriter.println(false);
        }
        printWriter.flush();

    }
}
