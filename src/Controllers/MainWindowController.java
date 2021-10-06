package Controllers;

import Modules.Logic;
import Modules.LoginData;
import Modules.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainWindowController {
    @FXML private ListView<LoginData> loginDataListView;
    private final Logic logic = new Logic();
    @FXML private Button deCodeBtn;
    @FXML private void initialize(){
        boolean answer = this.logic.getDataFromDatabase();
        if(answer==false){
            Stage stage = (Stage) this.loginDataListView.getScene().getWindow();
            stage.close();
        }
        this.loadDataToLists();
        this.initPhotos();
    }
    /*Sets default photos*/
    private void initPhotos(){
        ImageView imageView = new ImageView(getClass().getResource("/Data/photos/lock.png").toExternalForm());
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setY(10);
        this.deCodeBtn.setGraphic(imageView);
    }
    /*Inserts data from order list into listviews*/
    private void loadDataToLists(){
        this.loginDataListView.setCellFactory((lv) -> LoginDataListCell.newInstance());
        ObservableList<LoginData> loginData = FXCollections.observableArrayList();
        for(LoginData logindata : this.logic.loginDataList) {
            loginData.add(logindata);
            this.loginDataListView.setItems(loginData);
        }
    }
    @FXML protected void createKeyWindow(){
        Window newWindow = new Window("Key Window", "/Views/KeyWindowView.fxml", "/Styles/style.css", 281, 210);
        newWindow.initWindow();
        KeyWindowController scene4Controller = newWindow.getLoader().getController();
        scene4Controller.transferSelectedItemAndLogicObject(this.loginDataListView.getSelectionModel().getSelectedItem(), this.logic);
        newWindow.showWindow();
    }
    private void createPopUpWindow(String message){
        Window newWindow = new Window("PopUp Window", "/Views/PopUpWindow.fxml", "/Styles/style.css", 235, 92);
        newWindow.initWindow();
        PopUpWindowController scene4Controller = newWindow.getLoader().getController();
        scene4Controller.transferMessage(message, null);
        newWindow.showWindow();
    }

}
