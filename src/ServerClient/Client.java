package ServerClient;

import Other.SceneChanger;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Client {
    private String login;
    private String userId;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private static Client single_instance = null;
    private String loginFriend;

    private Client(){
    }
    public static Client getInstance()
    {
        if (single_instance == null)
            single_instance = new Client();

        return single_instance;
    }
    public void initializeReadWriteBuffer() throws IOException {

        Socket socket = new Socket("localhost", 5056);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        this.printWriter = printWriter;
        this.bufferedReader = bufferedReader;
    }
    public void setLogin(String login)
    {
        this.login = login;
    }

    public void sendMessageToServer(String message) {
        printWriter.println(message);
        printWriter.flush();

    }
    private String readMessage(){
        String message = null;
        while(message==null){
            try {
                message = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    //client sending login and password to server to check if it is correct
    public boolean checkSignInData(String login, String password) throws IOException {
        String message;
        sendMessageToServer(login);
        sendMessageToServer(password);
        message = readMessage();
        System.out.println(message);
        if(message.contains("true")) return true;
        else return false;
    }
    public String getLogin() {
        return this.login;
    }
    public Integer getId(){
        Integer id;
        sendMessageToServer("getId");
        id = Integer.parseInt(readMessage());
        return id;
    }
    public List<String> getListOfFriends(){
        sendMessageToServer("getListOfFriends");
        String list = readMessage();
        list = list.substring(1,list.length()-1);
        List<String> listOfFriends = new LinkedList<>(Arrays.asList(list.split(", ")));
        return listOfFriends;
    }
    public List<String> getListOfOnlineFriends(){
        sendMessageToServer("getOnlineFriends");
        String list = readMessage();
        list = list.substring(1,list.length()-1);
        List<String> onlineListOfFriends = new LinkedList<>(Arrays.asList(list.split(", ")));
        return onlineListOfFriends;
    }
    public List<String> getListOfOfflineFriends(){
        sendMessageToServer("getOfflineUsers");
        String list = readMessage();
        list = list.substring(1,list.length()-1);
        List<String> offlineListOfFriends = new LinkedList<>(Arrays.asList(list.split(", ")));
        return offlineListOfFriends;
    }
    public List<String> getListOfSearchedUsers(String searchedLoginUser){
        sendMessageToServer("getListOfSearchedUsers");
        sendMessageToServer(searchedLoginUser);
        String list = readMessage();
        list = list.substring(1,list.length()-1);
        List<String> listOfSearchedUsers= new LinkedList<>(Arrays.asList(list.split(", ")));
        return listOfSearchedUsers;
    }
    public void addFriendship(String friendLogin){
        System.out.println("Client friendlogin:" + friendLogin);
        sendMessageToServer("addFriendship");
        sendMessageToServer(friendLogin);
    }
    public List<String> getChatMessages(){
        sendMessageToServer("loadChatMessages");
        sendMessageToServer(this.loginFriend);
        System.out.println("Konwersacja:" + this.loginFriend);
        String list = readMessage();
        list = list.substring(1,list.length()-1);
        List<String> messages= new LinkedList<>(Arrays.asList(list.split(", ")));
        return messages;
    }
    public void openPrivateConversation(String loginFriend){
        this.loginFriend = loginFriend;
        SceneChanger sceneChanger = new SceneChanger("../gui/fxml/chat.fxml", "../gui/css/style.css");
        sceneChanger.changeScene();
    }
    public void sendMessageToChat(String message){
        sendMessageToServer("sendMessage");
        sendMessageToServer(loginFriend);
        sendMessageToServer(message);
    }
    public void logout(){
        sendMessageToServer("logout");
    }
}

