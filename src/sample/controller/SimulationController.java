package sample.controller;

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
import sample.Main;
import sample.model.Block;
import sample.simulation.Simulation;

import java.io.IOException;
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


        //simulation button
        btnSimulation.setOnAction((event) -> {
            simulation = new Simulation();
            lblTrip.setText(String.valueOf(simulation.getAllTripValue()));
            lblSpeed.setText(String.valueOf("Speed on the this trip is: " + simulation.calculateRandomSpeedPivot()) + " km/h");

            Task<Void> simulator = new Task<Void>() {
                @Override
                protected Void call() throws Exception {


                    for (int i = 0; i <= 5; i++) {
                        Thread.sleep(150);
                        updateProgress(i, 5);
                        Thread.sleep(500);
                    }
                    return null;
                }
            };

            simulateProgress.progressProperty().unbind();
            simulateProgress.progressProperty().bind(simulator.progressProperty());

            simulateProgress.progressProperty().addListener(new ChangeListener<Number>() {
                @Override public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    double progress = newValue == null ? 0 : newValue.doubleValue();
                    if (progress < 0.2) {
                        setBarStyleClass(simulateProgress, RED_BAR);
                    } else if (progress < 0.4) {
                        setBarStyleClass(simulateProgress, ORANGE_BAR);
                    } else if (progress < 0.6) {
                        setBarStyleClass(simulateProgress, YELLOW_BAR);
                    } else {
                        setBarStyleClass(simulateProgress, GREEN_BAR);
                    }
                }

                private void setBarStyleClass(ProgressBar simulateProgress, String barStyleClass) {
                    simulateProgress.getStyleClass().removeAll(barColorStyleClasses);
                    simulateProgress.getStyleClass().add(barStyleClass);
                }
            });
//
//            simulation.getChildren().setAll(simulateProgress, btnSimulation);
//            simulation.getStylesheets().add(getClass().getResource("progressBar.css").toExternalForm());
//            stage.setScene(new Scene(simulation));
//            stage.show();
//
//            timeline.play();

            Thread simulationThread = new Thread(simulator);
            simulationThread.setDaemon(true);
            simulationThread.start();

        });


        getEcu.setOnAction(event -> {
            ecuInput.setText(String.valueOf(ECU.getBalance()));

        });

        getOil.setOnAction(event -> {
            oilInput.setText(String.valueOf(Oil.getBalance()));

        });

        getDrive.setOnAction(event -> {
            driveInput.setText(String.valueOf(Drive.getBalance()));

        });

        getGenerator.setOnAction(event -> {
            generatorInput.setText(String.valueOf(Generator.getBalance()));

        });

        getIntercoler.setOnAction(event -> {
            intercolerInput.setText(String.valueOf(Intercoler.getBalance()));

        });

        getHeadlight.setOnAction(event -> {
            headlightsInput.setText(String.valueOf(Intercoler.getBalance()));

        });

        getTransmission.setOnAction(event -> {
            transmissionInput.setText(String.valueOf(Transmission.getBalance()));

        });

        getSpeed.setOnAction(event -> {
            speedInput.setText(String.valueOf(SpeedOmetr.getBalance()));

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
