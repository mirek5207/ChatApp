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

    public ClientHandler(Socket socket, BufferedReader bufferedReader,PrintWriter printWriter) {
        this.socket = socket;
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
    }

    @Override
    public void run() {
        DataBase connection = new DataBase();
        String message = null;
        checkSignInData(connection);
        System.out.println("Login" + login);
        while (true){
            try {
                message = bufferedReader.readLine();
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (message){
                case "getId":
                    printWriter.println(connection.getIdOfUser(login));
                    printWriter.flush();
                    break;
                case "getListOfFriends":
                    List<Integer> idOfFriends;
                    List<String> listOfFriends = new LinkedList<>();
                    Integer userId = connection.getIdOfUser(login);
                    idOfFriends = connection.searchIdOfFriends(userId);
                    for(int i = 0 ; i<idOfFriends.size();i++ )
                    {
                        listOfFriends.add(connection.searchUserById(idOfFriends.get(i)));
                    }
                    printWriter.println(listOfFriends);
                    printWriter.flush();
                    break;
                case "getListOfSearchedUsers":
                    List<String> listOfSearchedUsers;
                    
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
        }
        else {
            printWriter.println(false);
        }
        printWriter.flush();

    }
}
