package com.example.tegneprogram;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class TegnEllipse extends Ellipse implements Figur {
    final private static String NAVN = "Ellipse";
    //konstruktørmetode
    public TegnEllipse(double x, double y, double radiusX, double radiusY) {
        super(x, y, radiusX, radiusY);
    }


    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public String getNavn() {
        return NAVN;
    }

    @Override
    public void setX(double x) {
        setCenterX(x);
    }

    @Override
    public void setY(double y) {
        setCenterY(y);
    }

    @Override
    public double areal() {
        return Math.PI * getRadiusX() * getRadiusY();
    }

    @Override
    public double linjeLengde() {
        return 0;
    }

    @Override
    public void fyllFarge(Color farge) {
        setFill(farge);
    }

    @Override
    public Color getFyll() {
        return (Color) getFill();
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
        double nyRadiusX = Math.abs(getCenterX() - x);
        double nyRadiusY = Math.abs(getCenterY() - y);
        setRadiusX(nyRadiusX);
        setRadiusY(nyRadiusY);
    }

    @Override
    public void flytt(double x, double y) {
        setX(x);
        setY(y);
    }

    @Override
    public void fyllStrek(Color farge) {
        setStroke(farge);
        setStrokeWidth(5);
    }
}
