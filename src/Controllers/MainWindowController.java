
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MainWindowController {
    @FXML
    private ListView<LoginData> loginDataListView;
    private final ArrayList<LoginData> loginDataList = new ArrayList<>();
    private final Logic logic = new Logic();
    @FXML
    private Button deCodeBtn;

    @FXML
    private void initialize() {
        this.getDataFromDatabase();
        boolean answer = this.logic.getDataFromDatabase();
        if (answer == false) {
            Stage stage = (Stage) this.loginDataListView.getScene().getWindow();
            stage.close();
        }
        this.loadDataToLists();
        this.initPhotos();
    }

    private void initPhotos() {
        ImageView imageView = new ImageView(getClass().getResource("/Data/photos/lock.png").toExternalForm());
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setY(10);
        this.deCodeBtn.setGraphic(imageView);
    }

    /*Makes connection to database*/
    private ResultSet connectToDatabase(String query) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/securepassword", "root", "");
            Statement myStat = myConn.createStatement();
            if (query.startsWith("UPDATE") || query.startsWith("DELETE")) myStat.executeUpdate(query);
            else return myStat.executeQuery(query);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

     public void databaseConnectionError() {
        this.createPopUpWindow("Database error!!!");
    }

    /*Gets data from database and inserts it into order list*/
    private void getDataFromDatabase() {
        try {
            ResultSet myRes = this.connectToDatabase("SELECT * FROM logindata");
            while (myRes.next())
                this.loginDataList.add(new LoginData(myRes.getInt("id"), myRes.getString("name"), myRes.getString("login"), myRes.getString("password")));
        } catch (Exception exc) {
            this.databaseConnectionError();
            Stage stage = (Stage) this.loginDataListView.getScene().getWindow();
            stage.close();
            exc.printStackTrace();
        }
    }

    /*Inserts data from order list into listviews*/
    private void loadDataToLists() {
        this.loginDataListView.setCellFactory((lv) -> LoginDataListCell.newInstance());
        ObservableList<LoginData> loginData = FXCollections.observableArrayList();
        for (LoginData logindata : this.logic.loginDataList) {
            loginData.add(logindata);
            this.loginDataListView.setItems(loginData);
        }
    }

    @FXML protected void createKeyWindow () {
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