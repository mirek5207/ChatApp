package Other;

import ServerClient.Client;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class DynamicElementGuiBuilder {
    private static Integer indexOfClickedButton = -1;
    public DynamicElementGuiBuilder(){
    }
    public void createButtons(List<String> list, String buttonStyleClass, VBox parentOfButton, String methodOnClick){
        List<Button> buttonList = new LinkedList<>();
        Client client = Client.getInstance();
        for(int i=0;i<list.size();i++){
            if(!list.get(i).equals("")){
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
                                });
                        break;
                    case "openPrivateConversation":
                        button.setOnAction((ActionEvent e) -> {
                            if(buttonStyleClass == "friendButton") indexOfClickedButton = finalI;
                            client.openPrivateConversation(list.get(finalI));
                        });
                    default:
                        break;
                }
                parentOfButton.getChildren().add(buttonList.get(i));
            }
        }

        if(indexOfClickedButton != -1 ){
            parentOfButton.getChildren().get(indexOfClickedButton).getStyleClass().removeAll("button","friendButton");
            parentOfButton.getChildren().get(indexOfClickedButton).getStyleClass().add("friendButtonClicked");
        }
        indexOfClickedButton = -1;

    }
    public void creatTextBox(List<String> list, VBox containerForTextArea, String styleClassOfTextArea){
        List<TextArea> textAreaList = new LinkedList<>();
        List<TextField> textFieldList = new LinkedList<>();
        Client client = Client.getInstance();
        String userName = client.getLogin();

        int k = 0;
        for(int i = 0; i < list.size()-1 ;i += 2){
            TextArea textArea = new TextArea();
            TextField textField = new TextField();
            textField.setText(list.get(i));
            textArea.appendText(list.get(i+1));
            textArea.getStyleClass().add(styleClassOfTextArea);
            textArea.setMaxHeight(60);
            if(list.get(i).equals(userName)){
                textField.getStyleClass().add("userNameTextBox");
            }
            else{

                textField.getStyleClass().add("friendNameTextBox");
            }
            textAreaList.add(textArea);
            textFieldList.add(textField);
            containerForTextArea.getChildren().add(textFieldList.get(k));
            containerForTextArea.getChildren().add(textAreaList.get(k));
            k++;
        }

    }
}
