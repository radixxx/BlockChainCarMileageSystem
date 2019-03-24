package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.Repository;
import sample.model.Transaction;
import sample.model.TransactionOutput;

import java.security.Security;
import java.util.HashMap;

import static sample.model.Gloabal.*;


public class Main extends Application {



    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<>();
    public static int difficulty = 2;
    public static float minimumTransaction = 0.1f;
    public static double mileageInterval1 = 110110f;
    public static double mileageInterval2 = 5500f;


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("iCar");
        stage.setScene(scene);
        stage.show();

    }


    public void workerProc() {
        //connecting lib for calculate hash
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        //Create components: (110110 === 15730)
        ECU = new Repository();
        Oil = new Repository();
        Drive = new Repository();
        Intercoler = new Repository();
        Headlights = new Repository();
        Transmission = new Repository();
        SpeedOmetr = new Repository();
        Generator = new Repository();

        repository = new Repository();

        allTripValue = (float) (mileageInterval1 + (int) (Math.random() * mileageInterval2));

        float value = 110110f;//static add mileage

        genesisTransaction = new Transaction(repository.publicKey, ECU.publicKey, allTripValue, null);
        genesisTransaction.generateSignature(repository.privateKey); //manually sign the genesis transaction
        genesisTransaction.transactionId = "0"; //manually set the transaction id

        //manually add the Transactions Output
        genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient,
                genesisTransaction.value, genesisTransaction.transactionId));

        //its important to store our first transaction in the UTXOs list.
        UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));
        System.out.println("Creating and Mining Genesis block...+ ");
       // isChainValid();

    }

}
