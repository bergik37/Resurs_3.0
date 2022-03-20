package sample.HelpClass;

import java.io.*;
import java.util.ArrayList;

/**
 * Класс FileData для работы с блокнотом
 * "Cp1251" Для работы с кириллицой
 */
public class FileData {
    private String path_to_file;






    public FileData(String path) {
        path_to_file = path;
    }

    //"Cp1251"-Для работы с кирилицой
    public String[] OpenAndRead() throws Exception {
        Reader x = new InputStreamReader(new FileInputStream(path_to_file), "Cp1251");
        BufferedReader varRead = new BufferedReader(x);
        int num = numStrings();
        String[] lines = new String[num];
        int i;
        for (i = 0; i < num; i++) {
            lines[i] = varRead.readLine();
        }
        varRead.close();
        return lines;
    }

    int numStrings() throws IOException {
        Reader text = new InputStreamReader(new FileInputStream(path_to_file), "Cp1251");
        BufferedReader y = new BufferedReader(text);
        String one;
        int num = 0;
        while ((one = y.readLine()) != null) {
            num++;
        }
        y.close();
        return num;
    }

    public void OpenAndWrite(ArrayList<Double> sigmaTable_1, ArrayList<Double> sigmaA_1, double cons, double nsumCons, ArrayList<Double> degreeM_const, ArrayList<Double> sigmaTable_2, ArrayList<Double> sigmaA_2, double sumCons, double nsumVariable, ArrayList degreeM_variable) throws IOException {

        BufferedWriter outWriter=null;
        outWriter=new BufferedWriter(new FileWriter((path_to_file)));
        outWriter.write("Табличные сигмы приведеные к первым сигмам"+" ");
        outWriter.append('\n');
        for (int i = 0; i < sigmaTable_1.size(); i++) {
            outWriter.write(sigmaTable_1.get(i)+" ");
        }
        outWriter.append('\r');
        outWriter.append('\n');
        outWriter.write("первые сигмы"+" ");
        for (int i = 0; i < sigmaA_1.size(); i++) {
            outWriter.write(sigmaA_1.get(i)+" ");
        }
        outWriter.append('\r');
        outWriter.append('\n');
        outWriter.write("Суммарная повреждаймость от первых сигм"+" ");
        outWriter.write(String.valueOf(cons));
        outWriter.append('\r');
        outWriter.append('\n');
        outWriter.write("Средняя повреждаемость от первых сил"+" ");
        outWriter.write(String.valueOf(nsumCons));
        outWriter.append('\r');
        outWriter.append('\n');
        outWriter.write("Степень m для первых сигм"+" ");
        for (int i = 0; i < degreeM_const.size(); i++) {
            outWriter.write(degreeM_const.get(i)+" ");
        }

        /**
         * Вторые сигмы
         */
        outWriter.append('\r');
        outWriter.append('\n');
        outWriter.write("   ");
        outWriter.append('\r');
        outWriter.append('\n');
        outWriter.write("Вторые сигмы");
        outWriter.append('\r');
        outWriter.append('\n');
        outWriter.write("   ");
        outWriter.append('\r');
        outWriter.append('\n');
        outWriter.write("Табличные сигмы приведенные ко вторым сигма"+" ");
        outWriter.append('\n');
        for (int i = 0; i < sigmaTable_2.size(); i++) {
            outWriter.write(sigmaTable_2.get(i)+" ");
        }
        outWriter.append('\r');
        outWriter.append('\n');
        outWriter.write("вторые сигма сигмы"+" ");
        for (int i = 0; i < sigmaA_2.size(); i++) {
            outWriter.write(sigmaA_2.get(i)+" ");
        }
        outWriter.append('\r');
        outWriter.append('\n');
        outWriter.write("Суммарная повреждаймость от вторых сигм"+" ");
        outWriter.write(String.valueOf(sumCons));
        outWriter.append('\r');
        outWriter.append('\n');
        outWriter.write("Средняя повреждаемость от вторых сил"+" ");
        outWriter.write(String.valueOf(nsumVariable));
        outWriter.append('\r');
        outWriter.append('\n');
        outWriter.write("Степень m для вторых сигм"+" ");
        for (int i = 0; i < degreeM_variable.size(); i++) {
            outWriter.write(degreeM_variable.get(i)+" ");
        }

        outWriter.flush();
        outWriter.close();
    }
}

