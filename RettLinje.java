package com.example.tegneprogram;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class RettLinje extends Line implements Figur {
    final private static String NAVN = "Rett linje";
    //konstruktør
    public RettLinje(double startX, double startY, double sluttX, double sluttY) {
        super(startX, startY, sluttX, sluttY);
        setStrokeWidth(5);
    }

    //setter og getter metoder
    @Override
    public double getX() {
        return getStartX();
    }

    @Override
    public double getY() {
        return getStartY();
    }

    @Override
    public String getNavn() {
        return NAVN;
    }

    public double getSluttX() {
        return getEndX();
    }

    public double getSluttY() {
        return getEndY();
    }

    @Override
    public void setX(double x) {
        setStartX(x);
    }

    @Override
    public void setY(double y) {
        setStartY(y);
    }

    public void setSluttX(double x) {
        setEndX(x);
    }

    public void setSluttY(double y) {
        setEndY(y);
    }

    //metode for fylling av farge
    @Override
    public void fyllFarge(Color farge) {
        setStroke(farge);
    }

    //Metode som returnerer farge
    @Override
    public Color getFyll() {
        Color farge = (Color) getStroke();
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
        setEndX(x);
        setEndY(y);
    }

    @Override
    public void flytt(double x, double y) {
        // Hent nåværende start- og sluttkoordinater for linjen
        double startX = getStartX();
        double startY = getStartY();
        double sluttX = getSluttX();
        double sluttY = getSluttY();

        // Beregn linjens opprinnelige vinkel og lengde
        double deltaX = sluttX - startX;
        double deltaY = sluttY - startY;
        double vinkel = Math.atan2(deltaY, deltaX);  // Vinkel i radianer
        double lengde = linjeLengde();

        // Oppdater startposisjonen til museposisjonen
        setStartX(x);
        setStartY(y);

        // Beregn de nye sluttkoordinatene basert på vinkel og lengde
        double nySluttX = x + lengde * Math.cos(vinkel);
        double nySluttY = y + lengde * Math.sin(vinkel);

        // Oppdater sluttposisjonen for å opprettholde vinkelen og lengden
        setSluttX(nySluttX);
        setSluttY(nySluttY);
    }

    @Override
    public void fyllStrek(Color farge) {
        setStroke(farge);
    }

    //arealet av en linje er alltid null
    @Override
    public double areal() {
        return 0;
    }

    @Override
    public double linjeLengde() {
        double x = getStartX();
        double y = getStartY();
        double x1 = getSluttX();
        double y1 = getEndY();

        // Beregner katetene som avstanden mellom senteret og museposisjonen
        double k1 = Math.abs(x1 - x);
        double k2 = Math.abs(y1 - y);



        //beregner distanse (hypotenusen)
        double distanse = Math.sqrt((k1*k1) + (k2*k2));

        return distanse;
    }
}
