package sample.HelpClass;

import sample.Model.FourStage;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SaveArr {

    static ArrayList arrayList =new ArrayList();
    static ArrayList<Double> niList = new ArrayList();
    static ArrayList<Double> sigmaA = new ArrayList();
    static ArrayList<Double> sigmaM = new ArrayList();
    static ArrayList<Double> sigmaMax = new ArrayList();
    static ArrayList<Double> arrY=new ArrayList<>();
    static ArrayList<Double> arrX=new ArrayList<>();
    static ArrayList<Double> sigmaTable_1=new ArrayList<>();
    static ArrayList<Double> sigmaTable_2=new ArrayList<>();
    static String σmVibFlyNew;
    static String σaVibFlyNew;
    static String σaGaG_FlyNew;
    static String σmGaG_FlyNew;
    static double m_gag;
    static double σmVibFly;


    public static String getΣmVibFly() {
        return String.valueOf(σmVibFly);
    }

    public void setΣmVibFly(double σmVibFly) {
        SaveArr.σmVibFly = σmVibFly;
    }

    public String getΣaGaG_FlyNew() {
        return σaGaG_FlyNew;
    }

    public void setΣaGaG_FlyNew(String σaGaG_FlyNew) {
        this.σaGaG_FlyNew = σaGaG_FlyNew;
    }

    public String getΣmGaG_FlyNew() {
        return σmGaG_FlyNew;
    }

    public void setΣmGaG_FlyNew(String σmGaG_FlyNew) {
        this.σmGaG_FlyNew = σmGaG_FlyNew;
    }



    public double getM_gag() {
        return m_gag;
    }

    public void setM_gag(double m_gag) {
        SaveArr.m_gag = m_gag;
    }




    public String getΣaVibFlyNew() {
        return σaVibFlyNew;
    }

    public void setΣaVibFlyNew(String σaVibFlyNew) {
        SaveArr.σaVibFlyNew = σaVibFlyNew;
    }



    public  String getΣmVibFlyNew() {
        return σmVibFlyNew;
    }

    public  void setΣmVibFlyNew(String σmVibFlyNew) {
        SaveArr.σmVibFlyNew = σmVibFlyNew;
    }



    public ArrayList<Double> getSigmaTable_1() {
        return sigmaTable_1;
    }

    public void setSigmaTable_1(ArrayList<Double> sigmaTable_1) {
        SaveArr.sigmaTable_1 = sigmaTable_1;
    }

    public ArrayList<Double> getSigmaTable_2() {
        return sigmaTable_2;
    }

    public void setSigmaTable_2(ArrayList<Double> sigmaTable_2) {
        SaveArr.sigmaTable_2 = sigmaTable_2;
    }


    public ArrayList getNiList() {
        return niList;

    }

    public void setNiList(ArrayList<Double> niList) {
        SaveArr.niList = niList;
    }

    public ArrayList<Double> getSigmaA() {
        return sigmaA;
    }

    public void setSigmaA(ArrayList<Double> sigmaA) {
        SaveArr.sigmaA = sigmaA;
    }

    public ArrayList<Double> getSigmaM() {
        return sigmaM;
    }

    public void setSigmaM(ArrayList<Double> sigmaM) {
        SaveArr.sigmaM = sigmaM;
    }

    public ArrayList<Double> getSigmaMax() {
        return sigmaMax;
    }

    public void setSigmaMax(ArrayList<Double> sigmaMax) {
        SaveArr.sigmaMax = sigmaMax;
    }

    public ArrayList getArrName() {

        return arrayList;
    }

    public void setArrName(ArrayList arrName) {

        this.arrayList = arrName;
    }
    public ArrayList<Double> getArrY() {
        return arrY;
    }

    public void setArrY(ArrayList<Double> arrY) {
        SaveArr.arrY = arrY;
    }

    public ArrayList<Double> getArrX() {
        return arrX;
    }

    public void setArrX(ArrayList<Double> arrX) {
        SaveArr.arrX = arrX;
    }


}
