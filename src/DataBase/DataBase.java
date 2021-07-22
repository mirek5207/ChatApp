package DataBase;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DataBase{

    private Connection connection;
    private ResultSet resultSet;

    public DataBase() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root","password");
            Statement statement = this.connection.createStatement();
            this.resultSet = statement.executeQuery("SELECT * FROM ChatAppDataBase.User");
            /*while (resultSet.next()){
                System.out.println(resultSet.getInt(1)+ resultSet.getString("login") + resultSet.getString("email") + resultSet.getString("password"));
            }*/
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }

    }
    public boolean addData(String table ,String login, String password,String email) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO ChatAppDataBase." + table +"( login, password, email )" + " VALUES (?,?,?);");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException var5) {
            System.err.println("Błąd przy wprowadzaniu danych studenta: " + login + " " + email + " " + password);
            return false;
        }

    }
    public int getIdOfUser(String login){
        int id=0 ;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("Select userId From ChatAppDataBase.User where login = ?");
            preparedStatement.setString(1, login);
            this.resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getInt("userId");
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(id);
        return id;
    }
    public void addFriendship(String userLogin,String friendLogin){
        int idUser= getIdOfUser(userLogin);
        int idFriend= getIdOfUser(friendLogin);
        System.out.println("user=" + idUser + "friend=" + idFriend);
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO ChatAppDataBase.Friendship"  +"( userId1, userId2)" + " VALUES (?,?);");
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idFriend);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public boolean deleteData(String table, String tableId) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("Delete FROM " + table + " Where id = " + Integer.parseInt(tableId) + ";");
            preparedStatement.execute();
            return true;
        } catch (SQLException var4) {
            System.err.println("Błąd przy usuwaniu danych studenta nr: " + tableId);
            return false;
        }
    }*/
    /*public boolean updateData(String tabelName, String id, String surName, String name) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("Update " + tabelName + " Set surName = ? , name = ? Where id = ?");
            preparedStatement.setString(1, surName);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, Integer.parseInt(id));
            preparedStatement.execute();
            return true;
        } catch (SQLException var6) {
            System.err.println("Błąd przy wprowadzaniu danych studenta: " + surName + " " + name);
            System.err.println("Błąd przy wprowadzaniu danych studenta: " + var6.getMessage());
            return false;
        }
    }*/
    public boolean checkSignInData(String login, String password) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT login,password FROM ChatAppDataBase.User" + " where login = ? AND password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            this.resultSet = preparedStatement.executeQuery();
            if(!(resultSet.next()))
            {
                System.out.print("Dane do logowania są nie poprawne");
                return false;
            }
        } catch (SQLException var9) {
            System.err.println("Problem z wczytaniem danych z BD");
            return false;
        }
        return true;
    }
    public List<String> searchUserByLogin(String login) {
        List<String> searchedUsers = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT login FROM ChatAppDataBase.User" + " where lower(login) like ?");
            preparedStatement.setString(1, "%" + login.toLowerCase() + "%");
            this.resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                searchedUsers.add(resultSet.getString("login"));
            }
        } catch (SQLException e) {
           e.printStackTrace();
            System.out.println("nie znaleziono");
        }
        return searchedUsers;
    }
    public String searchUserById(Integer id) {
        String searchedLogin = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT login FROM ChatAppDataBase.User" + " where userId like ?");
            preparedStatement.setInt(1, id);
            this.resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                searchedLogin = resultSet.getString("login");
            }
        } catch (SQLException e) {
           e.printStackTrace();
            System.out.println("nie znaleziono");
        }
        return searchedLogin;
    }
    public List<Integer> searchIdOfFriends(Integer id) {
        List<Integer> searchedIdOfFriends = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "(SELECT userId2 FROM ChatAppDataBase.Friendship" + " where userId1 like ?) " +
                    "UNION (SELECT userId1 FROM ChatAppDataBase.Friendship" + " where userId2 like ?) ");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            this.resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                searchedIdOfFriends.add(resultSet.getInt("userId2"));
            }
        } catch (SQLException e) {
           e.printStackTrace();
            System.out.println("nie znaleziono");
        }

        return searchedIdOfFriends;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException var2) {
            System.err.println("Problem z zamknięciem połączenia");
        }

    }
}
