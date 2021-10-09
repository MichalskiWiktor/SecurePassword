package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DeCodedPasswordController {
    @FXML private Label newPassLabel;
    public void transferNewPassword(String newPassword){
        this.newPassLabel.setText(newPassword);
    }
    public void confirmBtnClicked(){
        ///close window

    }
}
