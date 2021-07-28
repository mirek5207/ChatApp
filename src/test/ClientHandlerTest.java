package test;

import DataBase.DataBase;
import org.junit.Assert;
import org.junit.Test;

public class ClientHandlerTest {

    @Test
    public void checkSignInData_ReturnTrue(){
        //given
        String login = "mirek";
        String password = "";
        DataBase connection = new DataBase();
        //when
        boolean result = connection.checkSignInData(login,password);
        //then
        Assert.assertTrue(result);

    }



}








