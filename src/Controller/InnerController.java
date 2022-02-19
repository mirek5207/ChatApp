package Controller;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;


public interface InnerController {
    @FXML
    void addGroup(ActionEvent event);

    @FXML
    void logout(ActionEvent event);
}
