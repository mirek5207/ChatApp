package ServerClient;

import DataBase.DataBase;

import java.io.*;
import java.net.Socket;


public class ClientHandler extends Thread{

    final BufferedReader bufferedReader;
    final PrintWriter printWriter;
    final Socket socket;

    public ClientHandler(Socket socket, BufferedReader bufferedReader,PrintWriter printWriter) {
        this.socket = socket;
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
    }

    @Override
    public void run() {
        String message;
        checkSignInData();
        /*while(true) {
            message = null;
            try {
                message = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (message == null) break;
            System.out.println(message);
        }*/
    }
    private void checkSignInData(){
        String login,password;
        login = password = "";
        DataBase connection = new DataBase();
        try {
            login = bufferedReader.readLine();
            password = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(connection.searchData(login,password)){
            printWriter.println(true);
        }
        else {
            printWriter.println(false);
        }
        printWriter.flush();

    }
}
