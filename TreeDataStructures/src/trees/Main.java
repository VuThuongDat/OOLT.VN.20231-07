package trees;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/View.fxml"));
            primaryStage.setTitle("Trees");
            primaryStage.setScene(new Scene(root, 500, 340));
        }
        catch (NullPointerException e){
            e.getMessage();
        }
        primaryStage.show();
    }
}
