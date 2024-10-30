package com.example.tegneprogram;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Rektangel extends Rectangle implements Figur {
    //instansvariabler
    final private static String NAVN = "Rektangel";
    //konstruktør metode
    public Rektangel(double x, double y, double bredde, double høyde) {
        super(x, y, bredde, høyde);
    }

    //get metode for navn
    public String getNavn() {
        return NAVN;
    }

    //metode for fylling av farge
    @Override
    public void fyllFarge(Color farge) {
        setFill(farge);
    }

    //Metode som returnerer farge
    @Override
    public Color getFyll() {
        Color farge = (Color) getFill();
        return farge;
    }

    @Override
    public String getTekst() {
        return "";
    }

    @Override
    public void setTekst(String tekst) {

    }

    @Override
    public void endreStørrelse(double x, double y) {
        setWidth(x);
        setHeight(y);
    }

    @Override
    public void flytt(double x, double y) {
        setX(x - getWidth() / 2);
        setY(y - getHeight() / 2);
    }

    @Override
    public void fyllStrek(Color farge) {
        setStroke(farge);
        setStrokeWidth(5);
    }


    //areal metode
    @Override
    public double areal() {
        return getWidth()*getHeight();
    }

    @Override
    public double linjeLengde() {
        return 0.0;
    }


}
