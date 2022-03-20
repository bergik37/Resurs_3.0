package sample.HelpClass;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Excel {

    private static final String[] titles = {
            "Кривая усталости материала", "σmax", "N", "","Кривая усталости привденная σmax от вибрационного составляющей", "σmax", "N","", "Кривая усталости привденная σmax от постоянной составляющей цикла", "σmax", "N",};
    Workbook wb=new XSSFWorkbook();
    Sheet sheet = wb.createSheet("Кривая усталости");
   /* String file = "Кривая усталости.xlsx";
    FileOutputStream out = new FileOutputStream(file);*/
  // FileOutputStream out = new FileOutputStream("D:\\Idea\\Resurs_2.0\\Кривая усталости.xlsx");
   // File fileChange = new File("D:\\Idea\\Resurs_2.0\\Кривая усталости.xlsx");
    //XSSFWorkbook wbChange = null;


    public Excel() throws FileNotFoundException {
    }

    public void grafik(ArrayList arrY,ArrayList arrX,ArrayList sigmaTable_1,ArrayList sigmaTable_2) throws IOException, InvalidFormatException {
       // wb = new XSSFWorkbook();
        // XSSFSheet sheet = wbChange.getSheetAt(numberFly);
       // if(wb.getSheetName(0)=="Кривая усталости") {
           // wbChange = new XSSFWorkbook(fileChange);
           // XSSFSheet sheet1 = wbChange.getSheetAt(0);
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < titles.length; i++) {
                Cell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(titles[i]);
            }
            for (int i = 1; i < arrY.size() + 1; i++) {
                Row rowSigma = sheet.createRow(i);
                Cell cellSigma1 = rowSigma.createCell(1);
                cellSigma1.setCellValue((Double) arrY.get(i - 1));
                Cell cellN1 = rowSigma.createCell(2);
                cellN1.setCellValue((Double) arrX.get(i - 1));

                Cell cellSigma2 = rowSigma.createCell(5);
                cellSigma2.setCellValue((Double) sigmaTable_1.get(i - 1));
                Cell cellN2 = rowSigma.createCell(6);
                cellN2.setCellValue((Double) arrX.get(i - 1));

                Cell cellSigma3 = rowSigma.createCell(9);
                cellSigma3.setCellValue((Double) sigmaTable_2.get(i - 1));
                Cell cellN3 = rowSigma.createCell(10);
                cellN3.setCellValue((Double) arrX.get(i - 1));
            }
      //  }
        FileOutputStream out = new FileOutputStream("D:\\Idea\\Resurs_2.0\\Кривая усталости.xlsx");
        wb.write(out);
        out.close();
        //wb.close();

    }
    public void close() throws IOException {

    }

}
