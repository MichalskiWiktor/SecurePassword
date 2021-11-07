package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeCodedPasswordController {
    @FXML private Label newPassLabel;
    public void transferNewPassword(String newPassword){
        this.newPassLabel.setText(newPassword);
    }
    public void confirmBtnClicked(){
        Stage stage = (Stage) this.newPassLabel.getScene().getWindow();
        stage.close();
    }
}
