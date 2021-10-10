package Controllers;

import Modules.Logic;
import Modules.LoginData;
import Modules.Window;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class KeyWindowController{
    Logic logic;
    @FXML private PasswordField keyField;
    private LoginData selectedItem;
    public void transferSelectedItemAndLogicObject(LoginData selectedItem, Logic logic){
        this.selectedItem = selectedItem;
        this.logic = logic;
    }
    public void confirmBtnClicked() {
        String newPassword = this.logic.deCodePassword(this.keyField.getText(), this.selectedItem);
        Stage stage = (Stage) this.keyField.getScene().getWindow();
        stage.close();
        ///new window//////////
        Window newWindow = new Window("Password Window", "/Views/DeCodedPasswordWindow.fxml", "/Styles/style.css", 281, 154);
        newWindow.initWindow();
        DeCodedPasswordController scene4Controller = newWindow.getLoader().getController();
        scene4Controller.transferNewPassword(newPassword);
        newWindow.showWindow();
    }
}
