package sample.Model;

import javafx.scene.control.ComboBox;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ThirdStage {

    ComboBox qunt;
    ArrayList arrOne = new ArrayList();
    ArrayList arrTwo = new ArrayList();
    private double ζ;
    private int N;
    private double S;
    private double m;
    private double n_σ;
    private double lg10;
    private double Up;
    private double Km;
    private double Kb;
    private double n;

    public void coefficientForce(Double s) {
        ζ = s;
    }

    public void quadLog(Double s) {
        S = s;
    }

    public void degree(Double s) {
        m = s;
    }

    public void numberEkzemplyrov(Double s) {
        n_σ = s;
    }

    public void combotext(ComboBox s) {
        qunt = s;
    }

    public void numberObrazcov(int s) {
        N = s;
    }

    public void getArr_Km(ArrayList s) {
        arrOne = s;
    }

    public void getArr_Kb(ArrayList s) {
        arrTwo = s;
    }


    public void solution() {
        Kb = (double) arrOne.get(N - 1);
        Km = (double) arrTwo.get(N - 1);
        if (qunt.getSelectionModel().isSelected(0)) {
            Up = 2.33;
            lg10 = (Up - Km + 1.28 * Kb * Math.sqrt(1 + Math.pow(((ζ * 0.4343) / (Kb * S / m)), 2) * 1 / n_σ)) * S / m;
        } else {
            Up = 3.09;
            lg10 = (Up - Km + 1.28 * Kb * Math.sqrt(1 + Math.pow(((ζ * 0.4343) / (Kb * S / m)), 2) * 1 / n_σ)) * S / m;
        }
        n = Math.pow(10, lg10);
    }

    public String getResult_coefficientSafetyMargin() {
        String formattedDouble = (new DecimalFormat("#0.0000").format(n));
        return formattedDouble.replace(",", ".");
    }
}
