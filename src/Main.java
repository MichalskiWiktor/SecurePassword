import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/MainWindowView.fxml")));
            primaryStage.setTitle("Password Decoder");
            primaryStage.setScene(new Scene(root, 1063, 752));
            primaryStage.setResizable(false);
            primaryStage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/style.css")).toExternalForm());
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
