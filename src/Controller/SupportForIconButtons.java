package Controller;

import javafx.application.Platform;
import javafx.stage.Screen;
import sample.Main;

public class SupportForIconButtons {

    private int width;
    private int height;

    public void exitWindow(){
        Platform.exit();
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

}

