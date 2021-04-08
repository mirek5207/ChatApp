package ServerClient;

import ServerClient.ClientHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static InputStreamReader inputStreamReader;
    private static BufferedReader bufferedReader;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5056);
        while(true) {
            Socket clientSocket = serverSocket.accept();
            inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            Thread thread = new ClientHandler(clientSocket,  bufferedReader);
            thread.start();
        }
    }
}
