package Controllers;

import Modules.Logic;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AddNewPasswordController {
    @FXML private TextField nameField;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField keyField;

    Logic logic;
    public void transferLogicObject(Logic logic){
        this.logic = logic;
    }
    @FXML
    protected void addNewDataToList() {
        String pass = this.logic.codePassword(this.keyField.getText(), this.passwordField.getText());
        this.logic.addItemToList(this.nameField.getText(), this.loginField.getText(), pass);

    }
}
