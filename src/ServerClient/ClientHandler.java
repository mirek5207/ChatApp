package ServerClient;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread{

    final BufferedReader bufferedReader;
    final Socket socket;

    public ClientHandler(Socket socket, BufferedReader bufferedReader) {
        this.socket = socket;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run() {
        //Thread thread = Thread.currentThread();
        String message;
        while(true) {
            message = null;
            try {
                message = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (message == null) break;
            System.out.println(message);
        }
    }
}
