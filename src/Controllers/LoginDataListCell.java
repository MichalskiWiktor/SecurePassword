package Controllers;

import Modules.LoginData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginDataListCell extends ListCell<LoginData> implements Initializable {
    @FXML private Label nameLabel;
    @FXML private Label loginLabel;
    @FXML private Label passwordLabel;
    @FXML private AnchorPane root;
    private LoginData model;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setGraphic(root);
    }
    public AnchorPane getRoot() {
        return root;
    }

    public static LoginDataListCell newInstance() {
        FXMLLoader loader = new FXMLLoader(LoginDataListCell.class.getResource("/Views/LoginDataListCell.fxml"));
        try {
            loader.load();
            return loader.getController();
        } catch (IOException ex) {
            return null;
        }
    }
    @Override
    protected void updateItem(LoginData item, boolean empty) {
        super.updateItem(item, empty);
        getRoot().getChildrenUnmodifiable().forEach(c -> c.setVisible(!empty));
        if (!empty && item != null && !item.equals(this.model)) {
            this.nameLabel.textProperty().set(String.valueOf(item.getName()));
            this.loginLabel.textProperty().set(String.valueOf(item.getLogin()));
            this.passwordLabel.textProperty().set(String.valueOf(item.getPassword()));
        }
        this.model = item;
    }
}
