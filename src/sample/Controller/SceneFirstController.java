package sample.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import sample.Model.FirstStage;
import sample.Model.FourStage;
import sample.View;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;


public class SceneFirstController extends View implements Initializable {

    @FXML
    private TextField amountFlightPerHour;
    @FXML
    private TextField frequencyVibration;
    @FXML
    private TextField σmGaG_Fly;
    @FXML
    private TextField σaGaG_Fly;
    @FXML
    private TextField σmVibFly;
    @FXML
    private TextField σaVibFly;
    @FXML
    private TextField σmGaG_Exp;
    @FXML
    private TextField σaGaG_Exp;
    @FXML
    private TextField σmVibExp;
    @FXML
    private TextField σaVibExp;
    @FXML
    private TextField cycleGaG;
    @FXML
    private TextField degreeMGaG;
    @FXML
    private TextField cycleVariable;
    @FXML
    private TextField degreeMvariable;
    @FXML
    private TextField coefficientSafeCycle;
    @FXML
    private TextField coefSumm;
    @FXML
    private TextField fatigueFly;
    @FXML
    private TextField fatigueHour;
    @FXML
    private TextField amountManueverPerHour;
    @FXML
    private Pane paneGraphicsFirst;
    @FXML
    private LineChart linechart1;
    @FXML
    private TextField coefficientSafe;






    private FirstStage firstStage = new FirstStage();
    private  FourStage fourStage=new FourStage();

    public SceneFirstController() throws FileNotFoundException {
    }


    private void fatigueFly(String s) {
        fatigueFly.setText(s);
    }
    private void σaGaG_Exp(String s){
        σaGaG_Exp.setText(s);
    }

    private void fatigueHour(String s) {
        fatigueHour.setText(s);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void solve() throws Exception {
        this.solution();
    }

    private void solution() throws IOException, InvalidFormatException {
        firstStage.σaGaG_Exp(parseDouble(σaGaG_Exp.getText()));
        firstStage.σaGaG_Fly(parseDouble(σaGaG_Fly.getText()));
        firstStage.σaVarExp(parseDouble(σaVibExp.getText()));
        firstStage.σaVarFly(parseDouble(σaVibFly.getText()));
        firstStage.σmGaG_Exp(parseDouble(σmGaG_Exp.getText()));
        firstStage.σmGaG_Fly(parseDouble(σmGaG_Fly.getText()));
        firstStage.σmVarExp(parseDouble(σmVibExp.getText()));
        firstStage.σmVarFly(parseDouble(σmVibFly.getText()));
        firstStage.σmGaG_Fly(parseDouble(σmGaG_Fly.getText()));
        firstStage.ηSafe(parseDouble(coefficientSafe.getText()));
        firstStage.coefficientSafeCycle(parseDouble(coefficientSafeCycle.getText()));
        firstStage.coefSumm(parseDouble(coefSumm.getText()));
        firstStage.cycleGaG(parseDouble(cycleGaG.getText()));
        firstStage.degreeMGaG(parseDouble(degreeMGaG.getText()));
        firstStage.cycleVariable(parseDouble(cycleVariable.getText()));
        firstStage.degreeMvariable(parseDouble(degreeMvariable.getText()));
        firstStage.frequencyVibration(parseDouble(frequencyVibration.getText()));
        firstStage.amountFlightPerHour(parseDouble(amountFlightPerHour.getText()));
        firstStage.amountManueverPerHour(parseDouble(amountManueverPerHour.getText()));

        firstStage.solution();


        fatigueFly(firstStage.getResult_fatigueFly());
        fatigueHour(firstStage.getResult_fatigueHour());

    }


    public void print() throws Exception {
        this.printGraphic();
    }


    private void printGraphic() {
        linechart1.setVisible(false);
        firstStage.printGraphicFirstStage(paneGraphicsFirst);


    }


   @FXML
    public void otchet() throws Exception {
        this.startOchet();
    }

    public void startOchet() throws IOException, InvalidFormatException {

        firstStage.startOchet();
    }

    public void appearThreeScene() throws IOException {
        Stage stageThird = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane secondScene = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Resources/thirdScene.fxml"));
        stageThird.setTitle("Расчет коэффициента безопасности");
        stageThird.setScene(new Scene(secondScene, 700, 700));
        stageThird.showAndWait();
    }

    public void appearFourScene_294() throws IOException, InterruptedException {
        Stage stageThird = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane secondScene = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Resources/fourScene_294.fxml"));
        stageThird.setTitle("Относительная повторяемость режимов полета");
        stageThird.setScene(new Scene(secondScene, 700, 800));
        //stageThird.showAndWait();
        stageThird.show();
    }
        public void logFile(){
            try {
                Desktop.getDesktop().open(new File("D:\\Idea\\Resurs_2.0\\logFile.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }



    public void close() {
        System.exit(0);
    }

}