/* Grensesnitt som ligger øverst i hierarkiet.
 * Inneholder metoder som er ønsket for klasser
 * som implementerer figur.
 */
package com.example.tegneprogram;

import javafx.scene.paint.Color;

public interface Figur  {
    //metoder som er ønsket i klasser som implementerer grensesnittet.

    //setter og getter metoder
    double getX();
    double getY();

    String getNavn(); //metode for å hente navn

    void setX(double x);
    void setY(double y);

    //metode som returnerer areal
    double areal();

    //metode som returnerer linjelengde
    double linjeLengde();

    //metode for å fylle figur med farge
    void fyllFarge(Color farge);

    //metode som returnerer farge
    Color getFyll();

    //metode som returnerer tekst
    String getTekst();

    //Metode for å sette tekst
    void setTekst(String tekst);

    //metode for endring av størrelse
    void endreStørrelse(double x, double y);

    //metode for å flytte pos
    void flytt(double x, double y);

    void fyllStrek(Color farge);
}
