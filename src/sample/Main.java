package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.TransactionOutput;

import java.util.HashMap;


public class Main extends Application {



    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<>();
    public static int difficulty = 2;
    public static float minimumTransaction = 0.1f;
    public static double mileageInterval1 = 110110f;
    public static double mileageInterval2 = 5500f;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
