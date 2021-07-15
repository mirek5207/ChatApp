package Other;

import javafx.application.Platform;
import javafx.stage.Screen;
import sample.Main;

public class SupportForIconButtons {


    public void exitWindow(){
        Platform.exit();
        System.exit(0);
    }
    public void minimizeWindow()
    {
        Main.stage.setIconified(true);
    }
    public static int getHeightOfScreen(){
        return (int) Screen.getPrimary().getBounds().getHeight();
    }
    public static int getWidthOfScreen(){
        return (int) Screen.getPrimary().getBounds().getWidth();
    }
    public void resizeWindow()
    {
        if(getHeightOfScreen()==Main.stage.getHeight()&&getWidthOfScreen()==Main.stage.getWidth())
        {
            Main.stage.setHeight(600);
            Main.stage.setWidth(1000);
        }
        else{
            Main.stage.setHeight(getHeightOfScreen());
            Main.stage.setWidth(getWidthOfScreen());
        }
        Main.stage.centerOnScreen();
    }

}

