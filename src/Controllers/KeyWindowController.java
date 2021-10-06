package Controllers;

import Modules.Logic;
import Modules.LoginData;
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
    public String confirmBtnClicked(){
        this.logic.deCodePassword(this.keyField.getText(), this.selectedItem);
        return "";

    }
}
