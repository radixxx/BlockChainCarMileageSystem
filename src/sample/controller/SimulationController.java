package sample.controller;

import javafx.animation.Animation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Main;
import sample.model.Block;
import sample.simulation.Simulation;

import java.io.*;
import java.io.Console;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.model.Global.*;
import static sample.model.Validation.addBlock;


public class SimulationController implements Initializable {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBackSample;

    @FXML
    private Button btnSimulation;

    @FXML
    private TextField mileageField;

    @FXML
    private Button okBtn;

    @FXML
    private Button btnInitializing;

    @FXML
    private Label lblSpeed;

    @FXML
    private Label lblTrip;

    @FXML
    private Text textTrip;

    @FXML
    private Text textMileage;

    @FXML
    private ImageView imgX;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img5;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img6;

    @FXML
    private ImageView img7;

    @FXML
    private HBox progressLbl;

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;

    @FXML
    private Label lbl3;

    @FXML
    private Label lbl4;

    @FXML
    private Label lbl5;

    @FXML
    private Label lbl6;

    @FXML
    private Label lbl7;

    @FXML
    private Label lbl8;

    @FXML
    private TextField ecuInput;

    @FXML
    private TextField oilInput;

    @FXML
    private TextField driveInput;

    @FXML
    private TextField generatorInput;

    @FXML
    private TextField intercolerInput;

    @FXML
    private TextField headlightsInput;

    @FXML
    private TextField transmissionInput;

    @FXML
    private TextField speedInput;

    @FXML
    private Button getEcu;

    @FXML
    private Button setecu;

    @FXML
    private Button getOil;

    @FXML
    private Button setOil;

    @FXML
    private Button getDrive;

    @FXML
    private Button setDrive;

    @FXML
    private Button getGenerator;

    @FXML
    private Button setGenerator;

    @FXML
    private Button getIntercoler;

    @FXML
    private Button setIntercoler;

    @FXML
    private Button getHeadlight;

    @FXML
    private Button setHeadlight;

    @FXML
    private Button getTransmission;

    @FXML
    private Button setTransmission;

    @FXML
    private Button getSpeed;

    @FXML
    private Button setSpeed;

    @FXML
    private ProgressBar simulateProgress;

    @FXML
    private ImageView green1Img;

    @FXML
    private ImageView red1Img;

    @FXML
    private ImageView yellow1Img;

    @FXML
    private ImageView green2Img;

    @FXML
    private ImageView red2Img;

    @FXML
    private ImageView yellow2Img;

    @FXML
    private ImageView green3Img;

    @FXML
    private ImageView red3Img;

    @FXML
    private ImageView yellow3Img;

    @FXML
    private ImageView green4Img;

    @FXML
    private ImageView red4Img;

    @FXML
    private ImageView yellow4Img;

    @FXML
    private ImageView green5Img;

    @FXML
    private ImageView red5Img;

    @FXML
    private ImageView yellow5Img;

    @FXML
    private ImageView green6Img;

    @FXML
    private ImageView red6Img;

    @FXML
    private ImageView yellow6Img;

    @FXML
    private ImageView green7Img;

    @FXML
    private ImageView red7Img;

    @FXML
    private ImageView yellow7Img;

    @FXML
    private ImageView green8Img;

    @FXML
    private ImageView red8Img;

    @FXML
    private ImageView yellow8Img;

    @FXML
    void initialize() {

    }

    private static final String RED_BAR = "red-bar";
    private static final String YELLOW_BAR = "yellow-bar";
    private static final String ORANGE_BAR = "orange-bar";
    private static final String GREEN_BAR = "green-bar";
    private static final String[] barColorStyleClasses = {RED_BAR, ORANGE_BAR, YELLOW_BAR, GREEN_BAR};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSimulation.setTooltip(new Tooltip("Press to Simulate"));

        green1Img.setOpacity(0);
        red1Img.setOpacity(0);
        yellow1Img.setOpacity(0);

        green2Img.setOpacity(0);
        red2Img.setOpacity(0);
        yellow2Img.setOpacity(0);

        green3Img.setOpacity(0);
        red3Img.setOpacity(0);
        yellow3Img.setOpacity(0);

        green4Img.setOpacity(0);
        red4Img.setOpacity(0);
        yellow4Img.setOpacity(0);

        green5Img.setOpacity(0);
        red5Img.setOpacity(0);
        yellow5Img.setOpacity(0);

        green6Img.setOpacity(0);
        red6Img.setOpacity(0);
        yellow6Img.setOpacity(0);

        green7Img.setOpacity(0);
        red7Img.setOpacity(0);
        yellow7Img.setOpacity(0);

        green8Img.setOpacity(0);
        red8Img.setOpacity(0);
        yellow8Img.setOpacity(0);




        btnInitializing.setOnAction((event) ->{
            if (Drive.getBalance() == 15730 && Transmission.getBalance() == 15730){
                System.out.println("Initialization status: OK");
            }else
                System.out.println("Initialization status: Error");
        });


        //simulation button
        btnSimulation.setOnAction((event) -> {
            simulation = new Simulation();
            lblTrip.setText(String.valueOf(simulation.getAllTripValue()));
            lblSpeed.setText(String.valueOf("Speed on the this trip is: " + simulation.calculateRandomSpeedPivot()) + " km/h");

            Task<Void> simulator = new Task<Void>() {
                @Override
                protected Void call() throws Exception {


                    for (int i = 0; i <= 8; i++) {
                        Thread.sleep(100);
                        updateProgress(i, 8);
                        Thread.sleep(200);
                    }
                    return null;
                }
            };


            simulateProgress.progressProperty().unbind();
            simulateProgress.progressProperty().bind(simulator.progressProperty());

            simulateProgress.progressProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    double progress = newValue == null ? 0 : newValue.doubleValue();
                    if (progress == 1 && Drive.getBalance() == 15730) { //||
                        setBarStyleClass(simulateProgress, GREEN_BAR);
                        green1Img.setOpacity(1);
                        green2Img.setOpacity(1);
                        green3Img.setOpacity(1);
                        green4Img.setOpacity(1);
                        green5Img.setOpacity(1);
                        green6Img.setOpacity(1);
                        green7Img.setOpacity(1);
                        green8Img.setOpacity(1);

                    } else if (progress < 0.6) {
                        setBarStyleClass(simulateProgress, YELLOW_BAR);

                        green1Img.setOpacity(0);
                        green2Img.setOpacity(0);
                        green3Img.setOpacity(0);
                        green4Img.setOpacity(0);
                        green5Img.setOpacity(0);
                        green6Img.setOpacity(0);
                        green7Img.setOpacity(0);
                        green8Img.setOpacity(0);

                    } else if (progress == 1 || Drive.getBalance() < 15730) { // !!
                        setBarStyleClass(simulateProgress, RED_BAR);
                        red3Img.setOpacity(1);
                        red7Img.setOpacity(1);
                        red1Img.setOpacity(1);

                        yellow3Img.setOpacity(1);
                        yellow7Img.setOpacity(1);

                    } else {
                        setBarStyleClass(simulateProgress, GREEN_BAR);
                    }
                }

                private void setBarStyleClass(ProgressBar simulateProgress, String barStyleClass) {
                    simulateProgress.getStyleClass().removeAll(barColorStyleClasses);
                    simulateProgress.getStyleClass().add(barStyleClass);
                }
            });

            Thread simulationThread = new Thread(simulator);
            simulationThread.setDaemon(true);
            simulationThread.start();


        });

        setDrive.setOnAction((event1) -> {

            block1.addTransaction(ECU.sendMileage(Drive.publicKey, -15730));
            if (Drive.getBalance() <= 15730 )
                red3Img.setOpacity(1);

            System.out.println("Transaction failed to process. Discarded.");
            System.out.println("Error #2. Drive is blocked");
            //System.out.println(Drive.getBalance());
            System.out.println(driveInput.getText());

        });


        setTransmission.setOnAction((event1) -> {

            block1.addTransaction(ECU.sendMileage(Transmission.publicKey, -15730));
//            driveInput.setText(String.valueOf();
            System.out.println("Transaction failed to process. Discarded.");
            System.out.println("Error #7. Transmission is blocked");
            //System.out.println(Drive.getBalance());
            System.out.println(transmissionInput.getText());

        });

        okBtn.setOnAction((event2) -> {

            System.out.println(lblSpeed.getText());
            System.out.println(mileageField.getText() + "  mileage");
        });
//
        getEcu.setOnAction(event -> {
            ecuInput.setText(String.valueOf(ECU.getBalance()));

        });

        getOil.setOnAction(event -> {
            oilInput.setText(String.valueOf(Oil.getBalance()));
            System.out.println("Oil: " + oilInput.getText());
        });

        getDrive.setOnAction(event -> {
            driveInput.setText(String.valueOf(Drive.getBalance()));
            System.out.println("Drive: " + Drive.getBalance());
        });

        getGenerator.setOnAction(event -> {
            generatorInput.setText(String.valueOf(Generator.getBalance()));
            System.out.println("Generator: " + generatorInput.getText());
        });

        getIntercoler.setOnAction(event -> {
            intercolerInput.setText(String.valueOf(Intercoler.getBalance()));
            System.out.println("Intercoler: " + intercolerInput.getText());
        });

        getHeadlight.setOnAction(event -> {
            headlightsInput.setText(String.valueOf(Intercoler.getBalance()));
            System.out.println("Headlight: " + headlightsInput.getText());
        });

        getTransmission.setOnAction(event -> {
            transmissionInput.setText(String.valueOf(Transmission.getBalance()));
            System.out.println("Transmission: " + transmissionInput.getText());
        });

        getSpeed.setOnAction(event -> {
            speedInput.setText(String.valueOf(SpeedOmetr.getBalance()));
            System.out.println("Speedometer: " + speedInput.getText());
        });

    }


    public void changeScreen4ButtonPushed(javafx.event.ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/sample/view/sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

    }

}
