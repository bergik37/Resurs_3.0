package sample.Controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.Model.FirstStage;
import sample.Model.ThirdStage;
import sample.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;

public class SceneThirdController extends View implements Initializable {

    static ArrayList<Double> arr_Km = new ArrayList();
    static ArrayList<Double> arr_Kb = new ArrayList();
    ThirdStage thirdStage = new ThirdStage();
    int count;
    ArrayList<String> title = new ArrayList<>();
    private double ηSafe;
    @FXML
    private ComboBox comboBoxText;
    @FXML
    private TextField numberObrazcov;
    @FXML
    private TextField coefficientForce;
    @FXML
    private TextField quadLog;
    @FXML
    private TextField degree;
    @FXML
    private TextField numberEkzemplyrov;
    @FXML
    private TextField coefficientSafetyMargin;

    public SceneThirdController() throws FileNotFoundException {
    }

    public static ArrayList<Double> getArr_Km() {
        return arr_Km;
    }

    public static ArrayList<Double> getArr_Kb() {
        return arr_Kb;
    }

    private void coefficientSafetyMargin(String s) {
        coefficientSafetyMargin.setText(s);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.add("10E-2");
        title.add("10E-3");
        comboBoxText.getItems().addAll(title);
        File file = new File("D:\\Idea\\Resurs_2.0\\Параметры зависяющие от количества образцов.xlsx");
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheet("Лист1");
            Iterator<Row> rows = sheet.rowIterator();
            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();
                count++;
                if (count > 1) {
                    arr_Km.add(row.getCell(1).getNumericCellValue());
                    arr_Kb.add(row.getCell(2).getNumericCellValue());
                }
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void solve() throws Exception {
        this.solution();
    }

    private void solution() {

        thirdStage.coefficientForce(parseDouble(coefficientForce.getText()));
        thirdStage.quadLog(parseDouble(quadLog.getText()));
        thirdStage.degree(parseDouble(degree.getText()));
        thirdStage.numberEkzemplyrov(parseDouble(numberEkzemplyrov.getText()));
        thirdStage.combotext(comboBoxText);
        thirdStage.numberObrazcov((int) parseDouble(numberObrazcov.getText()));
        thirdStage.getArr_Km(getArr_Km());
        thirdStage.getArr_Kb(getArr_Kb());

        thirdStage.solution();

        coefficientSafetyMargin(thirdStage.getResult_coefficientSafetyMargin());


    }


}
