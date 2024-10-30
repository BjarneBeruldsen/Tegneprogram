/*
 * Tegneprogram
 *
 * Dette programmet er et enkelt tegneprogram for å lage og manipulere figurer som rektangler, sirkler, linjer,
 * ellipser og tekst. Programmet gir brukeren mulighet til å velge figurtype, justere størrelse, flytte figurer,
 * og velge farge for både fyll og strek. Figurene kan også flyttes frem eller tilbake i lagrekkefølgen på tegneflaten.
 * Dette er en oppgave med fokus på objektorientering og polymorfi
 *
 * Utvikler: Bjarne Hovd Beruldsen.
 * Dato: Oktober 2024
 */

package com.example.tegneprogram;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.util.ArrayList;

public class Tegneprogram extends Application {
    //deklarerer variabler her slik at de eksisterer i hele applikasjonen
    private ArrayList<Figur> figurTab = new ArrayList<>();
    private Pane tegnePanel;
    private BorderPane hovedPanel;


    //Knapper for å tegne gitt figur
    private RadioButton rbSirkel;
    private RadioButton rbRektangel;
    private RadioButton rbRettLinje;
    private RadioButton rbTekst;
    private RadioButton rbFlytt;
    private RadioButton rbEndreStørrelse;
    private RadioButton rbStroke;
    private RadioButton rbFill;
    private RadioButton rbEllipse;

    //legger til knapper for flytting fram og tilbake
    private Button btHeltFrem;
    private Button btEnFrem;
    private Button btHeltBak;
    private Button btEnBak;


    //TekstFelt
    private TextField tfFigurType;
    private ColorPicker cpFargeValg;
    private TextField tfLinjeLengde;
    private TextField tfAreal;
    private TextField tfTekst;

    //global variabel som lagrer valgt figur
    private Shape valgtFigur;


    @Override
    public void start(Stage stage) throws IOException {
        //Oppretter panelet
        hovedPanel = new BorderPane();

        //setter tegnepanel i senter
        hovedPanel.setCenter(tegnePanel());

        //setter knapper øverst
        hovedPanel.setTop(knappePanel());

        //legger lyttere til knapper med musetrykk
        tegnePanel.setOnMouseClicked(e -> leggTilFigur(e));

        //legger til lyttere til knapper for flytting ved overlapp
        btHeltBak.setOnAction(e -> flyttBak());
        btEnBak.setOnAction(e -> flyttEnBak());
        btHeltFrem.setOnAction(e -> flyttFrem());
        btEnFrem.setOnAction(e -> flyttEnFrem());

        Scene scene = new Scene(hovedPanel, 1000, 800);
        stage.setScene(scene);
        stage.setTitle("Tegneprogram");
        stage.show();
    }

    //metode for tegnepanel
    public Pane tegnePanel() {
        //Oppretter panelet
        tegnePanel = new Pane();

        return tegnePanel;
    }

    //metode for knapp panel horisontalt
    public HBox knappePanel() {
        //Oppretter panelet
        HBox panel = new HBox();
        panel.setStyle("-fx-background-color: #d3d3d3; -fx-border-width: 2px; -fx-border-color: black");
        //Setter mellomrom
        panel.setSpacing(10);
        //sentrerer knappene
        panel.setAlignment(Pos.CENTER);

        //oppretter knapper og legger til panelet
        rbRektangel = new RadioButton("Rektangel");
        rbSirkel = new RadioButton("Sirkel");
        rbRettLinje = new RadioButton("Rett Linje");
        rbTekst = new RadioButton("Tekst");
        rbFlytt = new RadioButton("Velg");
        rbEndreStørrelse = new RadioButton("Størrelse");
        rbEllipse = new RadioButton("Ellipse");

        //legger radioknapper til togglegroup
        ToggleGroup gruppe = new ToggleGroup();

        rbRektangel.setToggleGroup(gruppe);
        rbSirkel.setToggleGroup(gruppe);
        rbRettLinje.setToggleGroup(gruppe);
        rbTekst.setToggleGroup(gruppe);
        rbEllipse.setToggleGroup(gruppe);
        rbFlytt.setToggleGroup(gruppe);
        rbEndreStørrelse.setToggleGroup(gruppe);

        //legger til knapper for flytting ved overlapp
        btEnBak = new Button(">");
        btHeltBak= new Button(">>");
        btEnFrem= new Button("<");
        btHeltFrem = new Button("<<");

        panel.getChildren().addAll(rbEndreStørrelse, rbFlytt, rbRektangel,
                                    rbSirkel, rbRettLinje, rbTekst, rbEllipse,
                                    btHeltFrem, btEnFrem, btEnBak, btHeltBak);

        return panel;
    }

    //metode for panel på høyre siden
    public GridPane høyrePanel() {
        GridPane panel = new GridPane();
        //sentrerer elementer
        panel.setPadding(new Insets(10, 10, 10, 10));
        panel.setHgap(5);
        panel.setVgap(5);

        //legger til padding
        panel.setStyle("-fx-background-color: #d3d3d3; -fx-border-width: 2px; -fx-border-color: black");

        //legger til labels og textfelt/fargepanel
        panel.add(new Label("Figurtype:"), 0, 0);
        panel.add(tfFigurType = new TextField(), 1, 0);
        panel.add(new Label("Linjelengde:"), 0, 2);
        panel.add(tfLinjeLengde = new TextField(), 1, 2);
        panel.add(new Label("Areal:"), 0, 3);
        panel.add(tfAreal = new TextField(), 1, 3);
        panel.add(new Label("Tekst:"), 0, 4);
        panel.add(tfTekst = new TextField(), 1, 4);
        panel.add(new Label("Farge:"), 0, 5);
        panel.add(cpFargeValg = new ColorPicker(), 1, 5);

        //legger til togglegroup for fyll eller strek farge
        ToggleGroup gruppe = new ToggleGroup();
        panel.add(rbFill = new RadioButton("Fyll farge"), 0, 6);
        rbFill.setToggleGroup(gruppe);
        panel.add(rbStroke = new RadioButton("Strek farge"), 1, 6);
        rbStroke.setToggleGroup(gruppe);
        gruppe.selectToggle(rbFill);

        return panel;
    }
    //metode som fyller inn info i høyre panel
    public void fyllInfo(MouseEvent e) {
        //Høyre panel dukker opp ved trykk på figur
        hovedPanel.setRight(høyrePanel());
        //henter figur
        Figur f = (Figur)(valgtFigur);

        tfFigurType.setText(f.getNavn());
        tfAreal.setText(String.format("%.2f", f.areal()));
        cpFargeValg.setValue(f.getFyll());
        tfLinjeLengde.setText(String.format("%.2f", f.linjeLengde()));
        tfTekst.setText(f.getTekst());
        //setter tekst til tfTekst
        tfTekst.setOnAction(er -> {
            f.setTekst(tfTekst.getText());
        });



        //Setter farge til valgt farge for gitt figur
        //legger til valgt farge
        cpFargeValg.setOnAction(er -> {
            Color farge;
            if (rbFill.isSelected()) {
                farge = cpFargeValg.getValue();
                f.fyllFarge(farge);
            } else if (rbStroke.isSelected()) {
                farge = cpFargeValg.getValue();
                f.fyllStrek(farge);
            }
        });


    }

    //metode som legger til figur basert på knappetrykk
    public void leggTilFigur(MouseEvent e) {
        Figur f = null;
        if(rbRektangel.isSelected() ) {
            f = new Rektangel(e.getX(), e.getY(),
                    200, 50);
        } else if (rbSirkel.isSelected()) {
            f = new Sirkel(e.getX(), e.getY());
        } else if (rbRettLinje.isSelected()) {
            f = new RettLinje(e.getX(), e.getY(), e.getX()+100, e.getY());
        } else if (rbTekst.isSelected()) {
            f = new Tekst(e.getX(), e.getY());
        } else if(rbEllipse.isSelected()) {
            f = new TegnEllipse(e.getX(), e.getY(), 50, 30);
        }

        //legger til panel og tabell visst ikke den allerede er det
        if(f != null) {
            figurTab.add(f);
            tegnePanel.getChildren().add((Shape) f);
            //legger siste figur tegnet som valgt figur
            valgtFigur = (Shape) (f);

            //legger til lyttere ved oppretting slik at alle har dem
            valgtFigur.setOnMouseClicked(er -> visInfo(er));
            valgtFigur.setOnMouseDragged(er -> behandleFigur(er));
        }
    }

    //Metode for å vise info
    public void visInfo(MouseEvent e) {
        //viser info for valgt figur
        valgtFigur = (Shape) e.getSource();
        fyllInfo(e);
    }


    //Metode for flytting av figur og endring av størrelse 
    public void behandleFigur(MouseEvent e) {
        valgtFigur = (Shape) e.getSource();
        Figur f = (Figur)(valgtFigur);
        if(rbFlytt.isSelected()) {
            f.flytt(e.getX(), e.getY());
        } else if(rbEndreStørrelse.isSelected()) {
            f.endreStørrelse(e.getX(), e.getY());
        }
        //viser info ved flytting og endring av størrelse
        visInfo(e);
    }

    // Metode som flytter valgtFigur til front i Pane (tegnePanel)
    private void flyttFrem() {
        if (valgtFigur != null) { // En figur må være valgt
            valgtFigur.toFront();
        }
    }


    // Metode som flytter valgtFigur til helt bak i Pane (tegnePanel)
    private void flyttBak() {
        if (valgtFigur != null) { // En figur må være valgt
            valgtFigur.toBack();
        }
    }


    // Metode som flytter valgtFigur ett hakk lenger frem i Pane (tegnePanel)
    private void flyttEnFrem() {
        if (valgtFigur != null) { // En figur må være valgt
            int index = tegnePanel.getChildren().indexOf(valgtFigur); // Hent valgtFigur index
            if (index < tegnePanel.getChildren().size() - 1) {        // Sjekk at figur ikke allerede er fremst
                tegnePanel.getChildren().remove(index);                 // Fjern valgtFigur fra panelet
                tegnePanel.getChildren().add(index + 1, valgtFigur);    // legg til figuren igjen, ett hakk lenger frem
            }
        }
    }


    // Metode som flytter valgtFigur ett hakk lenger bak i Pane (tegnePanel)
    private void flyttEnBak() {
        if (valgtFigur != null) {    // En figur må være valgt
            int index = tegnePanel.getChildren().indexOf(valgtFigur); // Hent valgtFigur index
            if (index > 0) {                                          // Sjekk at figur ikke allerde er bakerst
                tegnePanel.getChildren().remove(index);                 // Fjern valgtFigur fra panelet
                tegnePanel.getChildren().add(index - 1, valgtFigur);    // Legg til figuren igjen, ett hakk lenger bak
            }
        }
    }


    public static void main(String[] args) {
        launch();
    }
}
