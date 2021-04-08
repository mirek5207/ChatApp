package ServerClient;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class Client {
    private String login;
    private Socket socket ;
    private PrintWriter printWriter;

    public Client(String login, Socket socket, PrintWriter printWriter){
        this.login = login;
        this.socket = socket;
        this.printWriter = printWriter;

    }

    public void sendMessage(String message) {
        printWriter.println("Message from Client:"+ message + " Socket:"+socket );
        printWriter.flush();

    }
    public String getLogin()
    {
        return login;
    }

}

