/* Sirkel klasse som arver fra circle klassen
 */
package com.example.tegneprogram;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Sirkel extends Circle implements Figur {
    final private static String NAVN = "Sirkel";

    //konstruktørmetode
    public Sirkel (double x, double y, double radius){
        super(x, y, radius);
        setRadius(radius);
    }
    //konstruktørmetode uten radius
    public Sirkel (double x, double y) {
        super(x, y, 50);
    }


    @Override
    public double getX() {
        return getCenterX();
    }

    @Override
    public double getY() {
        return getCenterY();
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
    public void endreStørrelse(double x1, double y1) {

            //finner linje lengde mellom nåværende musposisjon og senter av sirkel
            //tenker på punktene som en rettvinklet trekant
            //finner katetene

            double x = getCenterX();
            double y = getCenterY();

            // Beregner katetene som avstanden mellom senteret og museposisjonen
            double k1 = Math.abs(x1 - x);
            double k2 = Math.abs(y1 - y);


            //beregner distanse (hypotenusen)
            double distanse = Math.sqrt((k1*k1) + (k2*k2));

            //legger distanse til radius
            setRadius(distanse);
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

    //legger til areal metode
    @Override
    public double areal() {
        return Math.PI*getRadius()*getRadius();
    }

    @Override
    public double linjeLengde() {
        return 0.0;
    }


}
