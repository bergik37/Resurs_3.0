package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.Main;
import sample.Model.FirstStage;
import sample.Model.FourStage;
import sample.View;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;


public class SceneFourController294 extends View implements Initializable {

    FourStage fourStage = new FourStage();
    FirstStage firstStage=new FirstStage();

    static ArrayList<Double> arrX = new ArrayList();
    static ArrayList<Double> arrY = new ArrayList();
    static ArrayList<Double> arrY2 = new ArrayList();
    static ArrayList<String> aryStrings = new ArrayList();
    static ArrayList<String> aryStringsFlyBox=new ArrayList<>();
    int count;
    ArrayList<Double> niList = new ArrayList();

    File file = new File("D:\\Idea\\Resurs_2.0\\Материаллы.xlsx");
    XSSFWorkbook wbOne = null;
    File fileSecond = new File("D:\\Idea\\Resurs_2.0\\Относительная повторяемость режимов полета.xlsx");
    XSSFWorkbook wbSecond = null;
    @FXML
    private ComboBox comboBoxText;
    @FXML
    private TextField σmGaG_FlyNew;
    @FXML
    private TextField σaGaG_FlyNew;
    @FXML
    private TextField σmVibFlyNew;
    @FXML
    private TextField σaVibFlyNew;
    @FXML
    private TextField σaGaG_ExpNew;
    @FXML
    private TextField σmGaG_ExpNew;
    @FXML
    private TextField σmVibExpNew;
    @FXML
    private TextField σaVibExpNew;
    @FXML
    private TextField cycleGaG;
    @FXML
    private TextField cycleVariable;
    @FXML
    private TextField degreeMGaG;
    @FXML
    private TextField degreeMvariable;
    @FXML
    private Button refresh;
    @FXML
    private ComboBox flyDate;

    public SceneFourController294() throws FileNotFoundException {
    }

    private void σmGaG_FlyNew(String s) {
        σmGaG_FlyNew.setText(s);
    }

    private void σaGaG_FlyNew(String s) {
        σaGaG_FlyNew.setText(s);
    }

    private void σaVibFlyNew(String s) {
        σaVibFlyNew.setText(s);
    }

    private void σmVibFlyNew(String s) {
        σmVibFlyNew.setText(s);
    }

    private void σaGaG_ExpNew(String s) {
        σaGaG_ExpNew.setText(s);
    }

    private void σmGaG_ExpNew(String s) {
        σmGaG_ExpNew.setText(s);
    }

    private void σmVibExpNew(String s) {
        σmVibExpNew.setText(s);
    }

    private void σaVibExpNew(String s) {
        σaVibExpNew.setText(s);
    }

    private void cycleGaG(String s) {
        cycleGaG.setText(s);
    }

    private void cycleVariable(String s) {
        cycleVariable.setText(s);
    }

    private void degreeMGaG(String s) {
        degreeMGaG.setText(s);
    }

    private void degreeMvariable(String s) {
        degreeMvariable.setText(s);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    public ArrayList<Double> getArrY() {
        return arrY;
    }

    public ArrayList<Double> getArrX() {
        return arrX;
    }

    public ArrayList<Double> getArrY2() {
        return arrY2;
    }



    @FXML
    public void changeFly() throws IOException {
        try {
        Desktop.getDesktop().open(new File("D:\\Idea\\Resurs_2.0\\Относительная повторяемость режимов полета.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void changeMat() {
        try {
            Desktop.getDesktop().open(new File("D:\\Idea\\Resurs_2.0\\Материаллы.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void restart() {
        comboBoxText.getItems().clear();
        flyDate.getItems().clear();
        aryStrings.clear();
        aryStringsFlyBox.clear();
        if (refresh.isFocused()) {
            try {
                wbOne = new XSSFWorkbook(file);
                Iterator<Sheet> sheetIterator = wbOne.iterator();
                while (sheetIterator.hasNext()) {
                    Sheet sheet = sheetIterator.next();
                    aryStrings.add(sheet.getSheetName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < aryStrings.size(); i++) {
            comboBoxText.getItems().addAll(aryStrings.get(i));
        }


        if (refresh.isFocused()) {
            try {
                wbSecond = new XSSFWorkbook(fileSecond);
                Iterator<Sheet> sheetIterator = wbSecond.iterator();
                while (sheetIterator.hasNext()) {
                    Sheet sheet = sheetIterator.next();
                    aryStringsFlyBox.add(sheet.getSheetName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < aryStringsFlyBox.size(); i++) {
            flyDate.getItems().addAll(aryStringsFlyBox.get(i));
        }
        System.out.println(flyDate);
    }

    @FXML
    public void solve() throws Exception {
        count = 0;
        arrX.clear();
        arrY.clear();
        arrY2.clear();
        Sheet sheet3 = wbOne.getSheetAt(comboBoxText.getSelectionModel().getSelectedIndex());

        Iterator<Row> rows = sheet3.rowIterator();
        while (rows.hasNext()) {
            XSSFRow row = (XSSFRow) rows.next();
            count++;
            if ((count > 1) && (count < 5)) {//нужно проверка на количество строк в столбце!!!
                arrY.add(row.getCell(1).getNumericCellValue());
                arrY2.add(row.getCell(2).getNumericCellValue());
                arrX.add(row.getCell(3).getNumericCellValue());

            }
        }

        System.out.println(niList);
        System.out.println(arrX);
        this.solution();

    }
    public int getNumberFly() {
        return flyDate.getSelectionModel().getSelectedIndex();
    }



    private void solution() throws IOException {

        fourStage.setArrX(getArrX());
        fourStage.setArrY(getArrY());
        fourStage.setArrY2(getArrY2());
        fourStage.setNumberFly(getNumberFly());





        fourStage.solution();


        σmGaG_FlyNew(fourStage.getResult_σmGaG_FlyNew());
        σaGaG_FlyNew(fourStage.getResult_σaGaG_FlyNew());
        σaVibFlyNew(fourStage.getResult_σaVibFlyNew());
        σmVibFlyNew(fourStage.getResult_σmVibFlyNew());


        σaGaG_ExpNew(fourStage.getResult_σaGaG_ExpNew());
        σmGaG_ExpNew(fourStage.getResult_σmGaG_ExpNew());
        σmVibExpNew(fourStage.getResult_σmVibExpNew());
        σaVibExpNew(fourStage.getResult_σaVibExpNew());

        cycleGaG(fourStage.getResult_cycleGaG());
        cycleVariable(fourStage.getResult_cycleVariable());
        degreeMGaG(fourStage.getResult_degreeMGaG());
        degreeMvariable(fourStage.getResult_degreeMvariable());

    }
}

