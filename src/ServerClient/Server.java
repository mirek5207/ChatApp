package ServerClient;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *Server in loop accept client socket and create thread for handling every client separately
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5056);
        while(true) {
            Socket clientSocket = serverSocket.accept();
            InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
            Thread thread = new ClientHandler(clientSocket, bufferedReader, printWriter);
            thread.start();
        }
    }
}
