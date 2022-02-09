package Other;

import ServerClient.Client;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class DynamicElementGuiBuilder {

    private List<String> messages ;
    public DynamicElementGuiBuilder(){
    }

    public List<String> getMessage(){
        return messages;
    }
    public void createButtons(List<String> list, String buttonStyleClass, VBox parentOfButton, String methodOnClick){
        List<Button> buttonList = new LinkedList<>();
        Client client = Client.getInstance();

        for(int i=0;i<list.size();i++){
            Button button = new Button();
            button.setText(list.get(i));
            button.getStyleClass().add(buttonStyleClass);
            buttonList.add(button);
            int finalI = i;
            switch (methodOnClick) {
                case "addFriendship":
                    button.setOnMouseClicked(
                            (EventHandler) event -> {
                                client.addFriendship(list.get(finalI));
                                System.out.println("Dynamic Element gui builder:"+list.get(finalI));
                            });
                    break;
                case "openPrivateConversation":
                    messages = null;
                    button.setOnAction((ActionEvent e) -> {
                        client.openPrivateConversation(list.get(finalI));
                    });
                    break;
                default:
                    break;
            }
            parentOfButton.getChildren().add(buttonList.get(i));
        }
    }
}
