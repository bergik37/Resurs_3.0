package sample.HelpClass;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.Table;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.TextRange;
import com.spire.doc.fields.omath.OfficeMath;
import com.spire.doc.formatting.ParagraphFormat;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



/**
 * Класс word для создания microsoft word документа
 */
public class Word {
    String name = "resurs1.docx";
    //Document document = new Document();
   // Section section = document.addSection();
    TextRange tr;


    public Word() {
    }

    public void createDocument() {

    }

    public void setTableOne(ArrayList a1, ArrayList a2, ArrayList a3, ArrayList a4, ArrayList a5,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        Table table = section.addTable(true);
        paragraph.appendText("Таблица №1 Значение напряжений");
        table.resetCells(a1.size()+1, 5);

        table.get(0, 0).addParagraph().appendText("Режим полета");
        table.get(0, 0).setScaling(50f);
        table.get(0, 1).addParagraph().appendText("ni,%");
        table.get(0, 2).addParagraph().appendText("σm");
        table.get(0, 3).addParagraph().appendText("σa");
        table.get(0, 4).addParagraph().appendText("σmax");
        for (int i = 0; i < a1.size(); i++) {
            table.get(i+1, 0).addParagraph().appendText( a1.get(i).toString());
            table.get(i+1, 1).addParagraph().appendText( a2.get(i).toString());
            table.get(i+1, 2).addParagraph().appendText( a3.get(i).toString());
            table.get(i+1, 3).addParagraph().appendText( a4.get(i).toString());
            table.get(i+1, 4).addParagraph().appendText( a5.get(i).toString());
        }
        paragraph.getStyle().getParagraphFormat().setAfterSpacing(0);
        paragraph.getStyle().getParagraphFormat().setBeforeSpacing(0f);

    }


    public void setParagraph(String title,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        ParagraphFormat format = paragraph.getFormat();
        format.setLeftIndent(35f);
        paragraph.appendText("");

        tr = paragraph.appendText(title);
        tr.getCharacterFormat().setFontName("Arial");
        tr.getCharacterFormat().setFontSize(14f);
        tr.getCharacterFormat().setPosition(8.64f);
    }


    public void saveFile(Document document) {
        document.saveToFile(name, FileFormat.Docx_2013);
    }

    public void firstFormula(Document document,Section section) {
        Paragraph paragraph = section.addParagraph();

        String[] latexMathCode = {
                "\\sigma_{а}( ",
                "\\sigma_{а}",
                "+",
                "\\sigma_{m})",
                " = const"
        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }

    }

    public void secondFormula(Document document,Section section) {
        Paragraph paragraph = section.addParagraph();

        String[] latexMathCode = {
                "\\sigma_{экв} = ",
                "\\sqrt[m]{\\sum_{i=1}^n(n_{i}\\cdot\\sigma^m_{aпрi}})"
        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }

    }

    public void thirdFormula(double i1,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String b = i1 + "";
        String[] latexMathCode = {
                "\\sigma^{пер}_{m} = ",
                b + "\\frac{кгс_}{мм^2}"
        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }

    }

    public void fourthFormula(String i1,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String b = i1 + "";
        String[] latexMathCode = {
                "\\sigma^{пер}_{экв} = ",
                b + "\\frac{кгс_}{мм^2}"
        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }
    }

    public void fifth(String a1, String i1, String a2,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String[] latexMathCode = {
                "\\sigma^{пер}_{расч} = ",
                "\\sigma^{пер}_{экв}",
                "\\cdot",
                "n_{\\sigma} = " + a1 +
                        "\\cdot" + i1 + " = " + a2
        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }
    }

    public void sixth(String i1,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String[] latexMathCode = {
                "n_{\\sigma} = " + i1 + "\\mbox{- коэффициент запаса учитывающий, как разброс характеристик }"

        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }
    }

    public void seventh(Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String[] latexMathCode = {
                "P = " + "\\mbox{- Квантиль нормального распределения соответсвующий вероятности }"

        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }
    }

    public void eight(Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String[] latexMathCode = {
                "\\sigma_{lgN/m} = " + "\\mbox{- Квантиль нормального распределения соответсвующий вероятности }"

        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }
    }

    public void nine(Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String[] latexMathCode = {
                "γ = " + "\\mbox{- Квантиль нормального распределения соответсвующий вероятности }"

        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }
    }

    public void ten(Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String[] latexMathCode = {
                "n_{\\sigma} = " + "\\mbox{- Квантиль нормального распределения соответсвующий вероятности }"

        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }
    }

    public void eleven(Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String[] latexMathCode = {
                "n = " + "\\mbox{- Квантиль нормального распределения соответсвующий вероятности }"

        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }
    }

    public void twelve(Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String[] latexMathCode = {
                "\\mbox{Количество циклов}" + "n_v" + "\\mbox{переменной расчетной нагрузки за один полет}:"

        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }
    }

    public void thirteen(String t, String t1, String t2,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String a = "\\frac{3600}{" + t1 + "}";
        String[] latexMathCode = {
                "n_{v} = ",
                "f",
                "\\cdot",
                "\\frac{3600}{k}",
                " = " + t +
                        "\\cdot" + a, " = " + t2

        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }
    }

    public void fourteen(Document document,Section section) {
        Paragraph paragraph = section.addParagraph();

        String[] latexMathCode = {
                "f" + "\\mbox{– частота колебание амплитуды напряжения цикла,                  }"

        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }
    }

    public void fifteen(Document document,Section section) {
        Paragraph paragraph = section.addParagraph();

        String[] latexMathCode = {
                "k" + "\\mbox{– количество полетов за 1 час.                                     }"

        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }
    }

    public void sixteen(Document document,Section section) {
        Paragraph paragraph = section.addParagraph();

        String[] latexMathCode = {
                "\\mbox{разрушения}" + "N_v" + "\\mbox{для напряжений }" + "\\sigma^{пер}_{расч}                             "

        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
            paragraph.getStyle().getCharacterFormat().setFontName("Arial");

        }
    }

    public void eighteen(String i1, String i2, String i3,String i4,String i5,Document document,Section section) {

        Paragraph paragraph = section.addParagraph();
        if(i2.equals("0.00")){
            String[] latexMathCode = {
                    "N_{v} = ",
                    "(\\frac{\\sigma_{a}}{\\sigma^{пер}_{экв}})^m",
                    "\\cdot",
                    "N_{1}",
                    " = ", "(\\frac{" + i1 + "}" + "{" + i2 + "})" + "^" + i3 + "\\cdot" + i4.charAt(0) + i4.charAt(1) + i4.charAt(2) + i4.charAt(3) + i4.charAt(4),"\\cdot", "10^" + i4.charAt(6) + i4.charAt(7),
                    " = " + "бесконечность"
            };
            OfficeMath officeMath;
            for (int i = 0; i < latexMathCode.length; i++) {
                officeMath = new OfficeMath(document);
                officeMath.fromLatexMathCode(latexMathCode[i]);
                paragraph.getItems().add(officeMath);
            }
        }
        else{
            String[] latexMathCode = {
                    "N_{v} = ",
                    "(\\frac{\\sigma_{a}}{\\sigma^{пер}_{экв}})^m",
                    "\\cdot",
                    "N_{1}",
                    " = ", "(\\frac{" + i1 + "}" + "{" + i2 + "})" + "^" + i3 + "\\cdot" + i4.charAt(0) + i4.charAt(1) + i4.charAt(2) + i4.charAt(3) + i4.charAt(4), "\\cdot", "10^" + i4.charAt(6) + i4.charAt(7),
                    " = " + i5.charAt(0) + i5.charAt(1) + i5.charAt(2) + i5.charAt(3) + i5.charAt(4), "\\cdot", "10^" + i5.charAt(6) + i5.charAt(7)
            };
            OfficeMath officeMath;
            for (int i = 0; i < latexMathCode.length; i++) {
                officeMath = new OfficeMath(document);
                officeMath.fromLatexMathCode(latexMathCode[i]);
                paragraph.getItems().add(officeMath);
            }
        }
    }

    public void twenty(String i1, char v0, char v1, char v2, char v3, char v4, char v6, char v7,char c0, char c1, char c2, char c3, char c4, char c6, char c7,char c8,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String b = "\\frac{" + i1 + "}";
        String[] latexMathCode = {
                "\\Pi_{v} = ",
                "\\frac{n_{v}}{{N_{V}}",
                " = ", b + "{" + v0 + v1 + v2+v3+v4 + "\\cdot" + "10^" + v6+v7 + "}",
                " = " + c0 + c1 + c2 + c3+c4 + "\\cdot" + "10^" + "{" + c6 + c7 + c8+"}"
        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            paragraph.getItems().add(officeMath);
        }
    }

    public void twentyOne(String i1,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String b = i1 + "";
        String[] latexMathCode = {
                "\\sigma^{ЗВЗ}_{m} = ",
                b + "\\frac{кгс_}{мм^2}"
        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }

    }

    public void twentySecond(String i1,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String b = i1 + "";
        String[] latexMathCode = {
                "\\sigma^{ЗВЗ}_{экв} = ",
                b + "\\frac{кгс_}{мм^2}"
        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            officeMath.getCharacterFormat().setFontSize(14f);
            paragraph.getItems().add(officeMath);
            paragraph.getStyle().getParagraphFormat().setBeforeSpacing(10f);
            paragraph.getStyle().getParagraphFormat().setAfterSpacing(10f);
            paragraph.getStyle().getCharacterFormat().setFontSize(14f);
        }
    }

    public void twentyThird(String i1, String i2, String i3, String i4,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String[] latexMathCode = {
                "n_{ЗВЗ} = ",
                "k",
                "\\cdot",
                "n",
                "\\cdot",
                "\\eta_{N}",
                " = " + i1 +
                        "\\cdot" + i2, "\\cdot" + i3, " = " + i4
        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            paragraph.getItems().add(officeMath);
        }
    }

    public void twentyFourth(String i1, String i2, String i3, char v0, char v1, char v2, char v3, char v4, char v6, char v7, char c0, char c1, char c2, char c3, char c4, char c6, char c7,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String b = "(\\frac{" + i1 + "}" + "{" + i2 + "})" + "^" + i3;
        String[] latexMathCode = {
                "N_{ЗВЗ} = ",
                "(\\frac{\\sigma_{а}}{\\sigma^{ЗВЗ}_{пер}})^m",
                "\\cdot",
                "N_{1}",
                " = ", b + "\\cdot" + v0 + v1 + v2+v3+v4, "\\cdot", "10^" + v6+v7,
                " = " + c0 + c1 + c2+c3+c4, "\\cdot", "10^" + c6+c7
        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            paragraph.getItems().add(officeMath);
        }
    }

    public void twentyFive(String i1, char v0, char v1, char v2, char v3, char v4, char v6, char v7, char c0, char c1, char c2, char c3, char c4, char c6, char c7,char c8,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String b = "\\frac{" + i1 + "}";
        String[] latexMathCode = {
                "\\Pi_{ЗВЗ} = ",
                "\\frac{n_{ЗВЗ}}{{N_{ЗВЗ}}",
                " = ", b + "{" + v0 + v1 + v2 + v3+v4 + "\\cdot" + "10^" + v6 +v7+ "}",
                " = " + c0+c1+c2+c3+c4 + "\\cdot" + "10^" + "{" + c6 + c7 + c8+"}"
        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            paragraph.getItems().add(officeMath);
        }

    }

    public void twentySix(char v0, char v1, char v2, char v3, char v4, char v6, char v7,char v8,char c0, char c1, char c2, char c3, char c4, char c6, char c7,char c8,char m0, char m1, char m2, char m3, char m4, char m6, char m7,char m8,char d0, char d1, char d2, char d3, char d4, char d6, char d7,char d8,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String b = "\\frac{" + v0 + v1 + v2 + v3+v4+ "\\cdot" + "10^" + "{" + v6 + v7 +v8+ "}" + "}{" + c0 + c1 + c2 + c3+c4 + "\\cdot" + "10^" + "{" + c6 + c7+c8 +"}" + "+" + m0 + m1 + m2 + m3+m4 + "\\cdot" + "10^" + "{" + m6 + m7 +m8+ "}" + "}";

        String[] latexMathCode = {
                "\\ovelrine{\\Pi_{ЗВЗ}} = ",
                "\\frac{\\Pi_{ЗВЗ}}{\\Pi_{\\Sigma}} = ",
                "\\frac{\\Pi_{ЗВЗ}}{\\Pi_{ЗВЗ}+\\Pi_{v}}",
                " = " + b +
                        "= " +d0 + d1 + d2 + d3+d4+ "\\cdot" + "10^" + "{" + d6 + d7 +d8
        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            paragraph.getItems().add(officeMath);
        }
    }

    public void twentySeven(char c0, char c1, char c2, char c3, char c4, char c6, char c7,char c8,char m0, char m1, char m2, char m3, char m4, char m6, char m7,char m8, double i1, String i2,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String b = "\\frac{1}{("  + c0 + c1 + c2 + c3+c4 + "\\cdot" + "10^" + "{" + c6 + c7+c8 +"}" + "+" + m0 + m1 + m2 + m3+m4 + "\\cdot" + "10^" + "{" + m6 + m7 +m8+ "})" + "\\cdot" + i1 + "}";
        String[] latexMathCode = {
                "R_{fly} = ",
                "\\frac{1}{\\Pi_{\\Sigma}} = ",
                "\\frac{1}{(\\Pi_{ZvZ}+\\Pi_{v})\\cdot \\eta_{\\Sigma} }",
                " = " + b +
                        "= " + i2
        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            paragraph.getItems().add(officeMath);
        }

    }
    public void twentyEight(String i1, String i2, String i3,Document document,Section section) {
        Paragraph paragraph = section.addParagraph();
        String b = "\\frac{" + i1 + "}" + "{" + i2 + "}";
                String[] latexMathCode = {
                "R_{час} = ",
                "\\frac{R_{fly}}{k}",
                " = " + b +
                        "= " + i3
        };
        OfficeMath officeMath;
        for (int i = 0; i < latexMathCode.length; i++) {
            officeMath = new OfficeMath(document);
            officeMath.fromLatexMathCode(latexMathCode[i]);
            paragraph.getItems().add(officeMath);
        }

    }
}



