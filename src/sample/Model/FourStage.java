package sample.Model;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import sample.HelpClass.SaveArr;
import sample.HelpClass.FileData;

import java.util.Iterator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;


public class FourStage {

    FileData fileData=new FileData("D:\\Idea\\Resurs_2.0\\logFile.txt");
    SaveArr saveArr =new SaveArr();




    ArrayList<Double> niList = new ArrayList();
    ArrayList<Double> sigmaA = new ArrayList();
    ArrayList<Double> sigmaM = new ArrayList();
    ArrayList<Double> sigmaMax = new ArrayList();
    ArrayList<Double> sigmaM_copy = new ArrayList();
    ArrayList<Double> sigma_copy_Max = new ArrayList();
    ArrayList<String> arrName=new ArrayList<>();

    ArrayList<Double> c = new ArrayList();//массив переменной С для сигма(а) и сигмы (b)
    ArrayList<Double> cMax = new ArrayList();//массив переменной C для сигма(max)
    ArrayList<Double> table_c = new ArrayList();//Приведение табличных значений к максмальному значению из переменных для сигма(а) и сигмы (b)
    ArrayList<Double> sigmaMax_M = new ArrayList();
    ArrayList<Double> sigmaMax_A = new ArrayList();
    ArrayList<Double> damage_const = new ArrayList();
    ArrayList<Double> damage_variable = new ArrayList();

    ArrayList<Double> degreeM_const = new ArrayList<>();
    ArrayList<Double> degreeM_variable = new ArrayList<>();
    ArrayList<Double> cycleN_const = new ArrayList<>();
    ArrayList<Double> cycleN_variable = new ArrayList<>();

    /**
     * Массивы для сортировки степеней
     */
    ArrayList<Double> degreeM_const_copy_max = new ArrayList<>();
    ArrayList<Double> degreeM_const_copy_min = new ArrayList<>();
    ArrayList<Double> degreeM_variable_copy_max = new ArrayList<>();
    ArrayList<Double> degreeM_variable_copy_min = new ArrayList<>();

    ArrayList<Double> arrX = new ArrayList<>();
    ArrayList<Double> arrY = new ArrayList<>();
    ArrayList<Double> arrY2 = new ArrayList<>();






    double count;
    double max;
    double a;
    double b;
    double f;
    double σMax;//значение максимальное из таблицы сигма(М)
    double σPic_max;//значение максимальное из таблицы сигма(Мax)
    double aMax;
    double bMax;
    double table_1;
    double b_table_1;
    double b_table_2;
    double sumCons = 0;
    double sumVariable = 0;
    double NsumCons;
    double NsumVariable;
    double σaGaG;
    double σaVariable;

    double m1_min;
    double m1_max;

    double m2_min;
    double m2_max;

    double m_gag;
    double m_variable;
    int numberFly;

    File fileChange = new File("D:\\Idea\\Resurs_2.0\\Относительная повторяемость режимов полета.xlsx");
    XSSFWorkbook wbChange = null;

    /**
     * Приведение к максимальной sigma;
     */
    ArrayList<Double> sigmaA_1 = new ArrayList();// приведенные значения для сигма(м) и сигма(а)
    ArrayList<Double> sigmaA_2 = new ArrayList();//приведенные значения для сигма(max)

    /**
     * Приведение к максимальной sigma, при b=0;
     */


    /**
     * Приведение табличных значений
     */
    ArrayList<Double> sigmaTable_1 = new ArrayList();// приведенные значения для сигма(м) и сигма(а)
    ArrayList<Double> sigmaTable_2 = new ArrayList();//приведенные значения для сигма(max)


    public void solution() throws IOException {


        clearTable();

        try {
            count = 0;
            wbChange = new XSSFWorkbook(fileChange);
            //Sheet sheet = wbChange.getSheetAt("Лист 1");
            XSSFSheet sheet = wbChange.getSheetAt(numberFly);
            Iterator<Row> rows = sheet.rowIterator();
            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();
                count++;
                if (count > 1) {
                    arrName.add(row.getCell(0).getStringCellValue());
                    niList.add(row.getCell(1).getNumericCellValue());
                    sigmaM.add(row.getCell(2).getNumericCellValue());
                    sigmaA.add(row.getCell(3).getNumericCellValue());
                    sigmaMax.add(row.getCell(4).getNumericCellValue());
                }

            }
            // wbChange.close();

        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        σMax = setMax();
        σPic_max = setMax_M();


        for (int i = 0; i < niList.size(); i++) {
            f = sigmaMax.get(i) / 2;
            sigmaMax_M.add(f);
            sigmaMax_A.add(f);

            /**
             * Расчет приведенного значения для сигма(м) и сигма(а)
             */

            a = (sigmaM.get(i) + (sigmaA.get(i))) * (sigmaA.get(i));
            c.add(a);
            b = (-σMax + Math.sqrt(Math.pow(σMax, 2) + (4 * c.get(i)))) / 2;
            sigmaA_1.add(b);

            /**
             * Расчет приведенного значения для сигма(max)
             */
            aMax = (sigmaMax_M.get(i) + (sigmaMax_A.get(i))) * (sigmaMax_A.get(i));
            cMax.add(aMax);
            bMax = (-σPic_max + Math.sqrt(Math.pow(σPic_max, 2) + (4 * cMax.get(i)))) / 2;
            sigmaA_2.add(bMax);
        }
        for (int i = 0; i < arrY.size(); i++) {
            /**
             * Приведение табличных значение к максимальному из сигма(м) и сигма(а)
             */
            table_1 = (arrY.get(i) + arrY2.get(i)) * arrY2.get(i);
            table_c.add(table_1);
            b_table_1 = (-σMax + Math.sqrt(Math.pow(σMax, 2) + (4 * table_c.get(i)))) / 2;
            sigmaTable_1.add(b_table_1);

            /**
             * Приведение табличных значение к максимальному из сигма(max)
             */
            b_table_2 = (-σPic_max + Math.sqrt(Math.pow(σPic_max, 2) + (4 * table_c.get(i)))) / 2;
            sigmaTable_2.add(b_table_2);
        }


        for (int i = 0; i < niList.size(); i++) {
            if (sigmaA_1.get(i) > sigmaTable_1.get(1)) {
                //N=((sigmaA_1.get(i)-sigmaTable_1.get(0)/(sigmaTable_1.get(0))-sigmaTable_1.get(1))*(arrX.get(1)-arrX.get(0))+arrX.get(0));
                degreeM_const.add(Math.log(arrX.get(1) / arrX.get(0)) / Math.log((sigmaTable_1.get(0) / sigmaTable_1.get(1))));
                cycleN_const.add((Math.pow((sigmaTable_1.get(1) / sigmaA_1.get(i)), degreeM_const.get(i))) * arrX.get(1));

            } else if (sigmaA_1.get(i) < sigmaTable_1.get(1)) {
                degreeM_const.add(Math.log(arrX.get(2) / arrX.get(1)) / Math.log((sigmaTable_1.get(1) / sigmaTable_1.get(2))));
                cycleN_const.add((Math.pow((sigmaTable_1.get(1) / sigmaA_1.get(i)), degreeM_const.get(i))) * arrX.get(1));

            }

        }
        for (int i = 0; i < niList.size(); i++) {
            if (sigmaA_2.get(i) >= sigmaTable_2.get(1)) {
                degreeM_variable.add(Math.log(arrX.get(1) / arrX.get(0)) / Math.log((sigmaTable_2.get(0) / sigmaTable_2.get(1))));
                cycleN_variable.add((Math.pow((sigmaTable_2.get(1) / sigmaA_2.get(i)), degreeM_variable.get(i))) * arrX.get(1));

            } else if (sigmaA_2.get(i) < sigmaTable_2.get(1)) {
                degreeM_variable.add(Math.log(arrX.get(2) / arrX.get(1)) / Math.log((sigmaTable_2.get(1) / sigmaTable_2.get(2))));
                cycleN_variable.add((Math.pow((sigmaTable_2.get(1) / sigmaA_2.get(i)), degreeM_variable.get(i))) * arrX.get(1));
            }
        }
        for (int i = 0; i < niList.size(); i++) {
            damage_const.add((niList.get(i) / 100) / cycleN_const.get(i));
            damage_variable.add((niList.get(i) / 100) / cycleN_variable.get(i));
        }

        for (int i = 0; i < niList.size(); i++) {
            sumCons += damage_const.get(i);
            sumVariable += damage_variable.get(i);
        }

        NsumVariable = 1 / sumVariable;

        NsumCons= 1 / sumCons;

        m1_min = setMinConst();
        m1_max = setMaxConst();

        m2_min = setMinMVariable();
        m2_max = setMazMVariable();


        if (NsumVariable > arrX.get(1)) {
            σaVariable = Math.pow(((Math.pow(sigmaTable_2.get(1), degreeM_variable.get(0))) * arrX.get(1) / NsumVariable), 1 / m1_max);
            m_variable = m2_max;
        } else if (NsumVariable < arrX.get(1)) {
            σaVariable = Math.pow(((Math.pow(sigmaTable_2.get(1), degreeM_variable.get(1))) * arrX.get(1) / NsumVariable), 1 / m1_min);
            m_variable = m2_min;
        }

        if (NsumCons > arrX.get(1)) {
            σaGaG = Math.pow(((Math.pow(sigmaTable_1.get(1), degreeM_const.get(0))) * arrX.get(1) / NsumCons), 1 / m2_max);
            m_gag = m1_min;
        } else if (NsumCons < arrX.get(1)) {
            σaGaG = Math.pow(((Math.pow(sigmaTable_1.get(1), degreeM_const.get(1))) * arrX.get(1) / NsumCons), 1 / m2_min);
            m_gag = m1_max;
        }


        saveArr.setArrName(arrName);
        saveArr.setNiList(niList);
        saveArr.setSigmaMax(sigmaMax);
        saveArr.setSigmaM(sigmaM);
        saveArr.setSigmaA(sigmaA);
        saveArr.setArrY(arrY);
        saveArr.setArrX(arrX);
        saveArr.setSigmaTable_1(sigmaTable_1);
        saveArr.setSigmaTable_2(sigmaTable_2);
        saveArr.setSigmaTable_2(sigmaTable_2);
        saveArr.setΣmVibFlyNew((new DecimalFormat("#0.00").format(σMax)).replace(",", "."));
        saveArr.setΣaVibFlyNew((new DecimalFormat("#0.00").format(σaGaG)).replace(",", "."));
        saveArr.setM_gag(m_gag);
        saveArr.setΣaGaG_FlyNew(new DecimalFormat("#0.00").format(σaVariable));
        saveArr.setΣmGaG_FlyNew(new DecimalFormat("#0.00").format(σPic_max));
        fileData.OpenAndWrite(sigmaTable_1,sigmaA_1,sumCons,NsumCons,degreeM_const,sigmaTable_2,sigmaA_2,sumVariable,NsumVariable,degreeM_variable);

        System.out.println("Табличные первые сигмы" + sigmaTable_1);
        System.out.println("первые сигмы" + sigmaA_1);
        System.out.println();
        System.out.println(sumCons);
        System.out.println("NsumCons" + " " + NsumCons);
        System.out.println(degreeM_const);
        System.out.println(m2_max);
        System.out.println(m2_min);
        System.out.println();
        System.out.println("Табличные вторые сигмы" + sigmaTable_2);
        System.out.println("вторые сигмы" + sigmaA_2);
        System.out.println();
        System.out.println(sumVariable);
        System.out.println("NsumVariable" + " " + NsumVariable);
        System.out.println(degreeM_variable);
        System.out.println(m1_max);
        System.out.println(m1_min);

        //σaGaG_Exp(fourStage.getResult_σmGaG_FlyNew());

      restart();


    }

    public double setMax() {
        sigmaM_copy.addAll(sigmaM);
        Collections.sort(sigmaM_copy);
        max = sigmaM_copy.get(niList.size() - 1);
        return max;
    }

    public double setMax_M() {
        sigma_copy_Max.addAll(sigmaMax);
        Collections.sort(sigma_copy_Max);
        max = sigma_copy_Max.get(niList.size() - 1);
        return max / 2;
    }
    public double setMazMVariable() {
        degreeM_const_copy_max.addAll(degreeM_const);
        Collections.sort(degreeM_const_copy_max);
        max = degreeM_const_copy_max.get(niList.size() - 1);
        return max;
    }

    public double setMinMVariable() {
        degreeM_const_copy_min.addAll(degreeM_const);
        Collections.sort(degreeM_const_copy_min);
        max = degreeM_const_copy_min.get(0);
        return max;
    }

    public double setMaxConst() {
        degreeM_variable_copy_max.addAll(degreeM_variable);
        Collections.sort(degreeM_variable_copy_max);
        max = degreeM_variable_copy_max.get(niList.size() - 1);
        return max;
    }

    public double setMinConst() {
        degreeM_variable_copy_min.addAll(degreeM_variable);
        Collections.sort(degreeM_variable_copy_min);
        max = degreeM_variable_copy_min.get(0);
        return max;
    }

    public void setArrY(ArrayList<Double> arrY) {
        this.arrY = arrY;
    }

    public void setArrY2(ArrayList<Double> arrY2) {
        this.arrY2 = arrY2;
    }

    public void setArrX(ArrayList<Double> arrX) {
        this.arrX = arrX;
    }

    public void setNumberFly(int numberFly) {
        this.numberFly = numberFly;
    }

    /**
     * Сеттеры для получения значений Excel из контролера
     */
    public String getResult_σmGaG_FlyNew() {
        String formattedDouble = (new DecimalFormat("#0.00").format(σPic_max));
        return formattedDouble.replace(",", ".");
    }
/**
 * Сотри сюда
 */


    public String getResult_σaVibFlyNew() {
        String formattedDouble = (new DecimalFormat("#0.00").format(σaGaG));
        return formattedDouble.replace(",", ".");
    }
    public String getResult_σaGaG_FlyNew() {
        String formattedDouble = (new DecimalFormat("#0.00").format(σaVariable));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_σmVibFlyNew() {
        String formattedDouble = (new DecimalFormat("#0.00").format(σMax));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_σaVibExpNew() {
        String formattedDouble = (new DecimalFormat("#0.00").format(sigmaTable_1.get(1)));
        return formattedDouble.replace(",", ".");

    }

    public String getResult_σmVibExpNew() {
        String formattedDouble = (new DecimalFormat("#0.00").format(σMax));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_σmGaG_ExpNew() {
        String formattedDouble = (new DecimalFormat("#0.00").format(σPic_max));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_σaGaG_ExpNew() {
        String formattedDouble = (new DecimalFormat("#0.00").format(sigmaTable_2.get(1)));
        return formattedDouble.replace(",", ".");
    }


    public String getResult_cycleGaG() {
        String formattedDouble = (new DecimalFormat("#0.00").format(arrX.get(1)));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_cycleVariable() {
        String formattedDouble = (new DecimalFormat("#0.00").format(arrX.get(1)));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_degreeMGaG() {
        String formattedDouble = (new DecimalFormat("#0.00").format(m_gag));
        return formattedDouble.replace(",", ".");
    }

    public String getResult_degreeMvariable() {
        String formattedDouble = (new DecimalFormat("#0.00").format(m_variable));
        return formattedDouble.replace(",", ".");
    }


    public void restart() throws IOException {
       /* arrName.clear();
        niList.clear();
        sigmaMax.clear();
        sigmaM.clear();
        sigmaA.clear();*/
        sigmaM_copy.clear();
        sigma_copy_Max.clear();
        c.clear();
        cMax.clear();
        table_c.clear();
        sigmaMax_M.clear();
        sigmaMax_A.clear();
        damage_const.clear();
        damage_variable.clear();
        degreeM_const.clear();
        degreeM_variable.clear();
        cycleN_const.clear();
        cycleN_variable.clear();
        degreeM_const_copy_max.clear();
        degreeM_const_copy_min.clear();
        degreeM_variable_copy_max.clear();
        degreeM_variable_copy_min.clear();
        sigmaA_1.clear();
        sigmaA_2.clear();
        wbChange.close();

    }

    public void clearTable() {
        if (table_1 != 0) {
            arrName.clear();
            niList.clear();
            sigmaM.clear();
            sigmaA.clear();
            sigmaMax.clear();
            sigmaTable_1.clear();
            sigmaTable_2.clear();
            max = 0;
            a = 0;
            b = 0;
            f = 0;
            σMax = 0;
            σPic_max = 0;
            aMax = 0;
            bMax = 0;
            table_1 = 0;
            b_table_1 = 0;
            b_table_2 = 0;
            sumCons = 0;
            sumVariable = 0;
            NsumCons = 0;
            NsumVariable = 0;
            σaGaG = 0;
            σaVariable = 0;
            m1_min = 0;
            m1_max = 0;
            m2_min = 0;
            m2_max = 0;
            m_gag = 0;
            m_variable = 0;
        }
    }

}

