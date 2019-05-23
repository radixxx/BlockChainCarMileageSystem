package sample.controller;


import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Block;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.model.Global.*;
import static sample.model.Validation.addBlock;
import static sample.model.Validation.isChainValid;


public class Controller implements Initializable {

    private Main mainApp;

    private StatisticsController statisticsController;

    @FXML
    private AnchorPane sampleRoot;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startBtn;

    @FXML
    private Label menuLbl;

    @FXML
    private Button viewBtn;

    @FXML
    private ImageView logo;

    @FXML
    private ImageView carImgId;

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
    private ProgressBar pB;

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
    private ProgressIndicator progressPercent;

    @FXML
    private Label lbl9;


    @FXML
    void initialize() {


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startBtn.setTooltip(new Tooltip("Press to Start"));

        //start Button
        startBtn.setOnAction(event -> {
            Task<Void> worker = new Task<Void>() {
                @Override
                protected Void call() throws Exception {

                    mm = new Main();
                    mm.workerProc();
                    genesis = new Block("0");
                    genesis.addTransaction(genesisTransaction);
                    addBlock(genesis);


                    for (int i = 0; i <= 3; i++) {
                        Thread.sleep(300);
                        updateProgress(i, 3);
                        Thread.sleep(1000);
                    }
                    return null;
                } //if pB is worked once stop, make condition
            };

            progressPercent.progressProperty().unbind();
            progressPercent.progressProperty().bind(worker.progressProperty());
            pB.progressProperty().unbind();
            pB.progressProperty().bind(worker.progressProperty());

            Thread t = new Thread(worker);
            t.setDaemon(true);
            t.start();


            Block genesis = new Block("0");
            genesis.addTransaction(genesisTransaction);
            addBlock(genesis);
            isChainValid();


        });

    }


    public void changeScreenButtonPushed(javafx.event.ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/sample/view/statistics.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();



    }


    public void changeScreen3ButtonPushed(javafx.event.ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/sample/view/simulation.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();


    }

}