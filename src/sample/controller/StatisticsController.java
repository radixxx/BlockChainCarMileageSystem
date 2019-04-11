package sample.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Block;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.model.Global.*;
import static sample.model.Validation.addBlock;

public class StatisticsController implements Initializable {

    private Main mainApp;

    @FXML
    private AnchorPane statisticsRoot;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img5;

    @FXML
    private ImageView img6;

    @FXML
    private ImageView img7;

    @FXML
    private ImageView img8;

    @FXML
    private Button btnEcu;

    @FXML
    private Button btnOil;

    @FXML
    private Button btnDrive;

    @FXML
    private Button btnGenerator;

    @FXML
    private Button btnIntercoler;

    @FXML
    private Button btnHedlights;

    @FXML
    private Button btnTransmision;

    @FXML
    private Button btnSpeedometer;

    @FXML
    private Label lbl4;

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;

    @FXML
    private Label lbl3;

    @FXML
    private Label lbl5;

    @FXML
    private Text text1;

    @FXML
    private Text text2;

    @FXML
    private Text text3;

    @FXML
    private Text text4;

    @FXML
    private ImageView imgBlock;

    @FXML
    private ImageView imgTrans;

    @FXML
    private ImageView imgBalance;

    @FXML
    private ImageView imgMileage;

    @FXML
    void initialize() {


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnEcu.setTooltip(new Tooltip("ECU Balance"));
        btnOil.setTooltip(new Tooltip("Oil Balance"));
        btnDrive.setTooltip(new Tooltip("Drive Balance"));
        btnGenerator.setTooltip(new Tooltip("Generator Balance"));
        btnIntercoler.setTooltip(new Tooltip("Intercoler Balance"));
        btnHedlights.setTooltip(new Tooltip("HeadLights Balance"));
        btnTransmision.setTooltip(new Tooltip("Transmission Balance"));
        btnSpeedometer.setTooltip(new Tooltip("SpeedOmetr Balance"));


        btnEcu.setOnAction((event) -> {
            lbl1.setText(String.valueOf(block1 = new Block(genesis.hash)));
            lbl2.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl3.setText(String.valueOf("\nECU is attempting to send  mileage to ECU...+" +
                    block1.addTransaction(ECU.sendMileage(ECU.publicKey, 15730))));
            addBlock(block1);
            lbl4.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl5.setText(String.valueOf("Oil's balance is: " + ECU.getBalance()));

        });


        btnOil.setOnAction((event) -> {
            lbl1.setText(String.valueOf(block2 = new Block(block1.hash)));
            lbl2.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl3.setText(String.valueOf("\nECU is attempting to send  mileage to Oil..."
                    + block1.addTransaction(ECU.sendMileage(Oil.publicKey, 15730))));
            addBlock(block2);
            lbl4.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl5.setText(String.valueOf(Oil.getBalance()));
        });

        btnDrive.setOnAction((event) -> {
            lbl1.setText(String.valueOf(block3 = new Block(block2.hash)));
            lbl2.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl3.setText(String.valueOf("\nECU is attempting to send  mileage to Drive..."
                    + block1.addTransaction(ECU.sendMileage(Drive.publicKey, 15730))));
            addBlock(block3);
            lbl4.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl5.setText(String.valueOf(Drive.getBalance()));

        });

        btnGenerator.setOnAction((event) -> {
            lbl1.setText(String.valueOf(block4 = new Block(block3.hash)));
            lbl2.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl3.setText(String.valueOf("\nECU is attempting to send  mileage to Generator..."
                    + block1.addTransaction(ECU.sendMileage(Generator.publicKey, 15730))));
            addBlock(block4);
            lbl4.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl5.setText(String.valueOf(Generator.getBalance()));

        });
        btnIntercoler.setOnAction((event) -> {
            lbl1.setText(String.valueOf(block5 = new Block(block4.hash)));
            lbl2.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl3.setText(String.valueOf("\nECU is attempting to send  mileage to Intercoler..."
                    + block1.addTransaction(ECU.sendMileage(Intercoler.publicKey, 15730))));
            addBlock(block5);
            lbl4.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl5.setText(String.valueOf(Intercoler.getBalance()));

        });

        btnHedlights.setOnAction((event) -> {
            lbl1.setText(String.valueOf(block6 = new Block(block5.hash)));
            lbl2.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl3.setText(String.valueOf("\nECU is attempting to send  mileage to Headlights..."
                    + block1.addTransaction(ECU.sendMileage(Headlights.publicKey, 15730))));
            addBlock(block6);
            lbl4.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl5.setText(String.valueOf(Headlights.getBalance()));
        });

        btnTransmision.setOnAction((event) -> {
            lbl1.setText(String.valueOf(block7 = new Block(block6.hash)));
            lbl2.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl3.setText(String.valueOf("\nECU is attempting to send  mileage to Transmission..."
                    + block1.addTransaction(ECU.sendMileage(Transmission.publicKey, 15730))));
            addBlock(block7);
            lbl4.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl5.setText(String.valueOf(Transmission.getBalance()));
        });

        btnSpeedometer.setOnAction((event) -> {
            lbl1.setText(String.valueOf(block8 = new Block(block7.hash)));
            lbl2.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl3.setText(String.valueOf("\nECU is attempting to send  mileage to SpeedOmetr..."
                    + block1.addTransaction(ECU.sendMileage(SpeedOmetr.publicKey, 15730))));
            addBlock(block8);
            lbl4.setText(String.valueOf("\nECU's balance is: " + ECU.getBalance()));
            lbl5.setText(String.valueOf(SpeedOmetr.getBalance()));
        });


    }

    public void changeScreen2ButtonPushed(javafx.event.ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/sample/view/sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

    }

}
