package ServerClient;

import java.io.*;
import java.net.Socket;


public class Client {
    public String login;
    private PrintWriter printWriter;
    private static Client single_instance = null;

    private Client(){
    }
    public static Client getInstance()
    {
        if (single_instance == null)
            single_instance = new Client();

        return single_instance;
    }
    public void setData(String login,PrintWriter printWriter)
    {
        this.login = login;
        this.printWriter = printWriter;
    }

    public void sendMessage(String message) {
        printWriter.println("Message from Client:"+ message);
        printWriter.flush();

    }
    public String getLogin()
    {
        return this.login;
    }

}

