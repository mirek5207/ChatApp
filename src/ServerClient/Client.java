package ServerClient;

import java.io.*;



public class Client {
    public String login;
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

    //client sending login and password to server to check if it is correct
    public boolean checkSignInData(String login, String password) throws IOException {
        String message = null;
        printWriter.println(login);
        printWriter.println(password);
        printWriter.flush();
        while(message==null){
            try {
                message = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(message);
        if(message.contains("true")) return true;
        else return false;
    }

    public String getLogin() { return this.login; }

}

