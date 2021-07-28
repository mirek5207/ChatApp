package ServerClient;

import Other.SceneChanger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Client {
    private String login;
    private String userId;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private static Client single_instance = null;

    private Client(){
    }
    public static Client getInstance()
    {
        if (single_instance == null)
            single_instance = new Client();

        return single_instance;
    }
    public void initializeReadWriteBuffer() throws IOException {

        Socket socket = new Socket("192.168.8.101", 5056);
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

    public void sendMessage(String message) {
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
        sendMessage(login);
        sendMessage(password);
        message = readMessage();
        System.out.println(message);
        if(message.contains("true")) return true;
        else return false;
    }
    public String getLogin() { return this.login; }
    public Integer getId(){
        Integer id;
        sendMessage("getId");
        id = Integer.parseInt(readMessage());
        return id;
    }
    public List<String> getListOfFriends(){
        sendMessage("getListOfFriends");
        String list = readMessage();
        list = list.substring(1,list.length()-1);
        List<String> listOfFriends = new LinkedList<>(Arrays.asList(list.split(", ")));
        return listOfFriends;
    }
    public List<String> getListOfSearchedUsers(String searchedLoginUser){
        sendMessage("getListOfSearchedUsers");
        sendMessage(searchedLoginUser);
        String list = readMessage();
        list = list.substring(1,list.length()-1);
        List<String> listOfSearchedUsers= new LinkedList<>(Arrays.asList(list.split(", ")));
        return listOfSearchedUsers;
    }
    public void addFriendship(String friendLogin){
        sendMessage("addFriendship");
        sendMessage(friendLogin);

    }
    public void openPrivateConversation(String loginFriend){
        sendMessage("addGroup");
        sendMessage(loginFriend);
        SceneChanger sceneChanger = new SceneChanger("../gui/chat.fxml", "../gui/style.css");
        sceneChanger.changeScene();
    }
}

