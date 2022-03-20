package sample.Model;


import com.spire.doc.Document;
import com.spire.doc.Section;
import com.spire.doc.documents.Paragraph;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import sample.HelpClass.Excel;
import sample.HelpClass.SaveArr;
import sample.HelpClass.Word;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;



public class FirstStage {
    int[] X = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
    double[] YKoff = new double[19];
    double[] Y = new double[19];
    private double σmGaG_Fly;
    private double σaGaG_Fly;
    private double σmVibFly;
    private double σaVibFly;
    private double σmGaG_Exp;
    private double σaGaG_Exp;
    private double σmVibExp;
    private double σaVibExp;
    private double σaVibFlyFlyNewSecurity;
    private double ηSafe;
    private double Nx;
    private double k;
    private double f;
    private double nVariable;
    private double damageVariable;
    private double ηSafeCycle;
    private double nAmountCyclePerHour;
    private double amountManueverPerHour;
    private double NxGaG;
    private double cycleVariable;
    private double degreeMvariable;
    private double degreeMGaG;
    private double cycleGaG;
    private double coefficientSafeCycle;
    private double damageGaG;
    private double damage;
    private double η_coefSum;
    private double fatigueFly;
    private double fatigueHour;


    SaveArr saveArr =new SaveArr();
    Excel excel=new Excel();
    Word word = new Word();


    public FirstStage() throws FileNotFoundException {
    }


    public void σmGaG_Fly(Double s) {
        σmGaG_Fly = s;
    }

    public void amountFlightPerHour(Double s) {
        k = s;
    }

    public void frequencyVibration(Double s) {
        f = s;
    }

    public void σaGaG_Fly(Double s) {
        σaGaG_Fly = s;
    }

    public void σmVarFly(Double s) {
        σmVibFly = s;
    }
    public void ηSafe(Double s) {
        ηSafe = s;
    }

    public void σaVarFly(Double s) {
        σaVibFly = s;
    }

    public void σmGaG_Exp(Double s) {
        σmGaG_Exp = s;
    }

    public void σaGaG_Exp(Double s) {
        σaGaG_Exp = s;
    }

    public void σmVarExp(Double s) {
        σmVibExp = s;
    }

    public void σaVarExp(Double s) {
        σaVibExp = s;
    }

    public void cycleGaG(Double s) {
        cycleGaG = s;
    }

    public void degreeMGaG(Double s) {
        degreeMGaG = s;
    }

    public void cycleVariable(Double s) {
        cycleVariable = s;
    }

    public void degreeMvariable(Double s) {
        degreeMvariable = s;
    }

    public void coefficientSafeCycle(Double s) {
        coefficientSafeCycle = s;
    }

    public void coefSumm(Double s) {
        η_coefSum = s;
    }

    public void amountManueverPerHour(Double s) {
        amountManueverPerHour = s;
    }





    public void printGraphicFirstStage(Pane paneGraphics) {

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();

        final double pi = 3.14;
        double σпс = σmGaG_Fly + σaGaG_Fly;
        for (int i = 0; i < X.length; i++) {
            YKoff[i] = (pi / 4) * X[i];
        }
        Y[0] = 0;
        Y[1] = σпс / 2;
        Y[2] = σпс;
        Y[3] = σпс / 2;
        for (int i = 4; i < X.length; i++) {
            Y[i] = σaVibFly * Math.sin(YKoff[i]) + σmVibFly;
        }
        for (int i = 0; i < X.length; i++) {
            Y[0] = 0;
            Y[1] = σпс / 2;
            Y[2] = σпс;
            Y[3] = σпс / 2;
            series.getData().add(new XYChart.Data(X[i], Y[i]));
        }
        lineChart.getData().add(series);
        paneGraphics.getChildren().clear();
        paneGraphics.getChildren().add(lineChart);
        paneGraphics.setScaleX(0.85);
        paneGraphics.setScaleY(0.85);

    }

    public void solution() {
        /**
         * Vibration
         */
       // Document document = new Document();
       // Section section = document.addSection();
        σaVibFlyFlyNewSecurity = σaVibFly * ηSafe;
        Nx = cycleVariable * Math.pow((σaVibExp / σaVibFlyFlyNewSecurity), degreeMvariable);
        nVariable = f * 3600 / k;
        damageVariable = nVariable / Nx;

        /**
         * Ground-Air-Ground
         */
        nAmountCyclePerHour = (coefficientSafeCycle)* amountManueverPerHour;
        NxGaG = cycleGaG * Math.pow((σaGaG_Exp / σaGaG_Fly), degreeMGaG);
        damageGaG = nAmountCyclePerHour / NxGaG;

        damage = damageGaG / (damageGaG + damageVariable);
        fatigueFly = 1 / ((damageGaG + damageVariable) * η_coefSum);
        fatigueHour = fatigueFly /k;
    }


    public void startOchet() throws IOException, InvalidFormatException {
         Document document = new Document();
         Section section = document.addSection();
         Paragraph paragraph=section.addParagraph();
         String name="resurs.docx";
        //excel.close();
        //word.setParagraph("Таблица №1 Значение напряжений");
        
        word.setTableOne(saveArr.getArrName(), saveArr.getNiList(),saveArr.getSigmaA(),saveArr.getSigmaM(),saveArr.getSigmaMax(),document,section);
        word.setParagraph("ni – повторяемость режимов,",document,section);
        word.setParagraph("ϭm – среднее (постоянное) значение напряжений цикла вибрационного нагружения,",document,section);
        word.setParagraph("ϭa – значения амплитуда напряжений цикла вибрационного нагружения,",document,section);
        word.setParagraph("ϭmax – максимальные значения напряжений цикла вибрационного нагружения.",document,section);
        word.setParagraph(" ",document,section);
        word.setParagraph("Определяем повреждаемость от переменного нагружения",document,section);
        word.setParagraph("Переменные значения вибрационного нагружения амплитуд напряжений ϭa i   на каждом режиме приводим к максимальному среднему значению ϭm max   из всех режимов по формуле Одинга:",document,section);
        word.firstFormula(document,section);
        word.setParagraph("Эквивалентное значение амплитуды напряжения ϭa i  определяется по формуле ",document,section);
        word.secondFormula(document,section);
        word.setParagraph("где σ_(а пр i)^m – приведенное значение амплитуд напряжений ϭa i  к максимальному среднему значению ϭm max    ",document,section);
        word.setParagraph("Таким образом:",document,section);
        word.setParagraph("Среднее значение напряжения эквивалентного цикла:",document,section);
        word.thirdFormula(σmVibFly,document,section);
        word.setParagraph("Значение амплитуды напряжения эквивалентного цикла:",document,section);
        word.fourthFormula(getResult_σaGaG_FlyNew(),document,section);
        word.setParagraph("Расчетное значение амплитуды напряжения цикла составляет:",document,section);
        word.fifth(getResult_σaGaG_FlyNew(),getResult_ηSafe(),getResult_σaVibFlyFlyNewSecurity(),document,section);
        word.sixth(getResult_ηSafe(),document,section);
        word.setParagraph("выносливости конструкции так и измеренных нагрузок определенный при следующих значениях входящих в него параметров: ",document,section);
        word.seventh(document,section);
        word.nine(document,section);
        word.ten(document,section);
        word.eleven(document,section);
        word.twelve(document,section);
        word.setParagraph("(продолжительность полета 30 минут):",document,section);
        word.thirteen(getResult_f(), getResult_k(), getResult_nVariable(),document,section);
        word.fourteen(document,section);
        word.fifteen(document,section);
        word.setParagraph("Находим по кривой усталости (см. рис. №  1) количество циклов до ",document,section);
        word.sixteen(document,section);
        word.setParagraph("Тут должна быть форму ее пока нет а ватомате ",document,section);

        word.eighteen(getResult_σaVibExp(),getResult_σaVibFlyFlyNewSecurity(),getResult_degreeMvariable(),getResult_cycleVariable(),getResult_Nx(),document,section);

        word.setParagraph("Рисунок 1 - кривая усталости, приведенная к максимальному вибрационному среднему значению ϭm max  .",document,section);
        word.setParagraph("Определяем повреждаемость от переменных нагрузок:",document,section);
        word.setParagraph("Тут должна быть форму ее пока нет а ватомате ",document,section);
//        word.twenty(getResult_nVariable(), getResult_Nx().charAt(0), getResult_Nx().charAt(1), getResult_Nx().charAt(2), getResult_Nx().charAt(3),getResult_Nx().charAt(4), getResult_Nx().charAt(6), getResult_Nx().charAt(7),getResult_damageVariable().charAt(0),getResult_damageVariable().charAt(1),getResult_damageVariable().charAt(2),getResult_damageVariable().charAt(3),getResult_damageVariable().charAt(4),getResult_damageVariable().charAt(6),getResult_damageVariable().charAt(7),getResult_damageVariable().charAt(8),document,section);
        word.setParagraph("Определяем повреждаемость от повторно-статического нагружения",document,section);
        word.setParagraph("Переменные значения повторно статических нагружений  (ЗВЗ) амплитуд напряжений ϭa i   на каждом режиме приводим к максимальному среднему значению ϭm max   из всех режимов по формуле Одинга:",document,section);
        word.firstFormula(document,section);
        word.setParagraph("Эквивалентное значение амплитуды напряжения (ЗВЗ) ϭa i  определяется по формуле ",document,section);
        word.secondFormula(document,section);
        word.setParagraph("приведенное значение амплитуд напряжений ϭa i  к максимальному среднему значениюцикла ЗВЗ ϭm max    ",document,section);
        word.setParagraph("Таким образом:",document,section);
        word.setParagraph("Среднее значение напряжения эквивалентного цикла ЗВЗ:",document,section);
        word.twentyOne(getResult_σaGaG_FlyNew(),document,section);
        word.setParagraph("Значение амплитуды напряжения эквивалентного цикла ЗВЗ",document,section);
        word.twentySecond(getResult_σaGaG_FlyNew(),document,section);
        word.setParagraph("Расчетное число циклов ЗВЗ за один полет:",document,section);
        word.twentyThird(getResult_coefficientSafeCycle(),getResult_k(),amountManueverPerHour() ,getResult_nAmountCyclePerHour(),document,section);
        word.setParagraph("где η_N=2 - коэффициент надежности по циклам.",document,section);
        word.setParagraph("f – частота повторений циклв ЗВЗ.",document,section);
        word.setParagraph("η_N--???",document,section);
        word.setParagraph("Находим по кривой усталости (см. рис. №  2) количество циклов до разрушения N_v для напряжений σ_ЗВЗ^пер:",document,section);
        word.twentyFourth(getResult_σaGaG_Exp(), getResult_σaGaG_FlyNew(), getResult_degreeMGaG(), getResult_cycleGaG().charAt(0), getResult_cycleGaG().charAt(1), getResult_cycleGaG().charAt(2), getResult_cycleGaG().charAt(3),getResult_cycleGaG().charAt(4),getResult_cycleGaG().charAt(6),getResult_cycleGaG().charAt(7), getResult_NxGaG().charAt(0), getResult_NxGaG().charAt(1), getResult_NxGaG().charAt(2), getResult_NxGaG().charAt(3),getResult_NxGaG().charAt(4),getResult_NxGaG().charAt(6),getResult_NxGaG().charAt(7),document,section);
        word.setParagraph("Рисунок 2 - кривая усталости, приведенная к максимальному среднему значению ЗВЗ ϭm max  .",document,section);
        word.setParagraph("Определяем повреждаемость от переменных нагрузок и цикла ЗВЗ:",document,section);
        word.twentyFive(getResult_nAmountCyclePerHour(),getResult_NxGaG().charAt(0), getResult_NxGaG().charAt(1), getResult_NxGaG().charAt(2), getResult_NxGaG().charAt(3),getResult_NxGaG().charAt(4),getResult_NxGaG().charAt(6),getResult_NxGaG().charAt(7),getResult_damageGaG().charAt(0), getResult_damageGaG().charAt(1), getResult_damageGaG().charAt(2), getResult_damageGaG().charAt(3),getResult_damageGaG().charAt(4),getResult_damageGaG().charAt(6),getResult_damageGaG().charAt(7),getResult_damageGaG().charAt(8),document,section);
        word.setParagraph("Доля повреждаемости от цикла ЗВЗ:",document,section);
        word.setParagraph("Тут должна быть форму ее пока нет а ватомате ",document,section);
       // word.twentySix(getResult_damageGaG().charAt(0), getResult_damageGaG().charAt(1), getResult_damageGaG().charAt(2), getResult_damageGaG().charAt(3),getResult_damageGaG().charAt(4),getResult_damageGaG().charAt(6),getResult_damageGaG().charAt(7),getResult_damageGaG().charAt(8),getResult_damageGaG().charAt(0), getResult_damageGaG().charAt(1), getResult_damageGaG().charAt(2), getResult_damageGaG().charAt(3),getResult_damageGaG().charAt(4),getResult_damageGaG().charAt(6),getResult_damageGaG().charAt(7),getResult_damageGaG().charAt(8),getResult_damageVariable().charAt(0),getResult_damageVariable().charAt(1),getResult_damageVariable().charAt(2),getResult_damageVariable().charAt(3),getResult_damageVariable().charAt(4),getResult_damageVariable().charAt(6),getResult_damageVariable().charAt(7),getResult_damageVariable().charAt(8),getResult_damage().charAt(0),getResult_damage().charAt(1),getResult_damage().charAt(2),getResult_damage().charAt(3),getResult_damage().charAt(4),getResult_damage().charAt(6),getResult_damage().charAt(7),getResult_damage().charAt(8),document,section);
        word.setParagraph("Ресурс составляет:",document,section);
        word.setParagraph("Тут должна быть форму ее пока нет а ватомате ",document,section);
//        word.twentySeven(getResult_damageGaG().charAt(0), getResult_damageGaG().charAt(1), getResult_damageGaG().charAt(2), getResult_damageGaG().charAt(3),getResult_damageGaG().charAt(4),getResult_damageGaG().charAt(6),getResult_damageGaG().charAt(7),getResult_damageGaG().charAt(8),getResult_damageVariable().charAt(0),getResult_damageVariable().charAt(1),getResult_damageVariable().charAt(2),getResult_damageVariable().charAt(3),getResult_damageVariable().charAt(4),getResult_damageVariable().charAt(6),getResult_damageVariable().charAt(7),getResult_damageVariable().charAt(8),η_coefSum, getResult_fatigueFly(),document,section);
        word.setParagraph("η_Σ – коэффициент учитывающий влияние порядка чередования и наложения нагрузок",document,section);
        word.setParagraph("Ресурс в часах составляет:",document,section);
        word.twentyEight(getResult_fatigueFly(), getResult_k(), getResult_fatigueHour(),document,section);
        excel.grafik(saveArr.getArrY(),saveArr.getArrX(),saveArr.getSigmaTable_1(),saveArr.getSigmaTable_2());
        word.saveFile(document);
        /*try {
            Desktop.getDesktop().open(new File("D:\\FaticPro\\resurs1.docx."));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Desktop.getDesktop().open(new File("D:\\FaticPro\\file\\График.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    public String getResult_cycleGaG(){
        String formattedDouble = (new DecimalFormat("0.000E00").format(cycleGaG));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_σaVibFlyFlyNewSecurity(){
        String formattedDouble = (new DecimalFormat("0.00").format(σaVibFlyFlyNewSecurity));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_cycleVariable(){
        String formattedDouble = (new DecimalFormat("0.000E00").format(cycleVariable));
        return formattedDouble.replace(",", ".");
    }


    public String getResult_Nx() {
        String formattedDouble = (new DecimalFormat("0.000E00").format(Nx));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_NxGaG() {
        String formattedDouble = (new DecimalFormat("0.000E00").format(NxGaG));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_σaGaG_FlyNew() {
        String formattedDouble = (new DecimalFormat("#0.00").format(σaGaG_Fly));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_k() {
        String formattedDouble = (new DecimalFormat("#0.00").format(k));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_fatigueFly() {
        String formattedDouble = (new DecimalFormat("#0").format(fatigueFly));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_fatigueHour() {
        String formattedDouble = (new DecimalFormat("#0").format(fatigueHour));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_damageVariable() {
        String formattedDouble = (new DecimalFormat("0.000E00").format(damageVariable));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_damageGaG() {
        String formattedDouble = (new DecimalFormat("0.000E00").format(damageGaG));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_damage() {
        String formattedDouble = (new DecimalFormat("0.000E00").format(damage));
        return formattedDouble.replace(",", ".");
    }
    public String getResult_f() {
        String formattedDouble = (new DecimalFormat("#0.00").format(f));
        return formattedDouble.replace(",", ".");
    }


    public String getResult_nVariable() {
        String formattedDouble = (new DecimalFormat("#0.00").format(nVariable));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_ηSafe() {
        String formattedDouble = (new DecimalFormat("#0.00").format(ηSafe));
        return formattedDouble.replace(",", ".");
    }


    public String getResult_σaVibExp() {
        String formattedDouble = (new DecimalFormat("#0.00").format(σaVibExp));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_degreeMvariable() {
        String formattedDouble = (new DecimalFormat("#0.00").format(degreeMvariable));
        return formattedDouble.replace(",", ".");
    }


    public String getResult_coefficientSafeCycle() {
        String formattedDouble = (new DecimalFormat("#0.00").format(coefficientSafeCycle));
        return formattedDouble.replace(",", ".");
    }


    public String amountManueverPerHour() {
        String formattedDouble = (new DecimalFormat("#0.00").format(amountManueverPerHour));
        return formattedDouble.replace(",", ".");
    }


    public String getResult_nAmountCyclePerHour() {
        String formattedDouble = (new DecimalFormat("#0.00").format(nAmountCyclePerHour));
        return formattedDouble.replace(",", ".");
    }


    public String getResult_σaGaG_Exp() {
        String formattedDouble = (new DecimalFormat("#0.00").format(σaGaG_Exp));
        return formattedDouble.replace(",", ".");
    }


    public String getResult_degreeMGaG() {
        String formattedDouble = (new DecimalFormat("#0.00").format(degreeMGaG));
        return formattedDouble.replace(",", ".");
    }
}



