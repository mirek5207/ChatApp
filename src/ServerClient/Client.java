package ServerClient;

import java.io.*;
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
    public void setData(PrintWriter printWriter,BufferedReader bufferedReader)
    {
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
        for(int i =0; i < listOfFriends.size(); i++){
            System.out.println(listOfFriends.get(i));
        }
        return listOfFriends;
    }
}

