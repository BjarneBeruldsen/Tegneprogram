package com.example.tegneprogram;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tekst extends Text implements Figur {
    final private static String NAVN = "Tekst";

    //konstruktør metode
    public Tekst(double x, double y) {
        super(x, y, NAVN);
    }

    //metode for fylling av farge
    @Override
    public void fyllFarge(Color farge) {
        setFill(farge);
    }

    @Override
    public String getNavn() {
        return NAVN;
    }

    //Metode som returnerer farge
    @Override
    public Color getFyll() {
        Color farge = (Color) getFill();
        return farge;
    }

    @Override
    public String getTekst() {
        return getText();
    }

    @Override
    public void setTekst(String tekst) {
        setText(tekst);
    }

    @Override
    public void endreStørrelse(double x1, double y1) {
        //finner distanse mellom tekst og musepeker
        double x = getX();
        double y = getY();

        // Beregner katetene som avstanden mellom senteret og museposisjonen
        double k1 = Math.abs(x1 - x);
        double k2 = Math.abs(y1 - y);


        //beregner distanse (hypotenusen)
        double distanse = Math.sqrt((k1*k1) + (k2*k2));

        //Legger til font for å endre størrelse
        Font fonten = new Font(distanse);

        setFont(fonten);
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

    //areal metode
    @Override
    public double areal() {
        return 0;
    }

    @Override
    public double linjeLengde() {
        return 0.0;
    }

}
