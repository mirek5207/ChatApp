package Other;

import ServerClient.Client;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.List;

public class DynamicElementGuiBuilder {
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
                    button.setOnAction((ActionEvent e) -> client.addFriendship(list.get(finalI)));
                    break;
                case "openPrivateConversation":
                    button.setOnAction((ActionEvent e) ->client.openPrivateConversation(list.get(finalI)));
                default:
                    break;
            }
            parentOfButton.getChildren().add(buttonList.get(i));
        }
    }
}
