package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.modele.*;
import fr.iut.montreuil.projetFinal.vue.VueTourAigleArtilleur;
import fr.iut.montreuil.projetFinal.vue.VueTourArcX;
import fr.iut.montreuil.projetFinal.vue.VueTourArcher;
import fr.iut.montreuil.projetFinal.vue.VueTourCanon;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;

public class ListObsTour implements ListChangeListener<Tour> {

    private Environnement env;
    @FXML
    private Pane pane;

    public ListObsTour(Pane pane, Environnement env){
        this.pane = pane;
        this.env = env;
    }

    @Override
    public void onChanged(Change<? extends Tour> change) {
        while (change.next()) {
            for (Tour tour : change.getAddedSubList()) {
                    if (tour instanceof TourArchers) {
                        new VueTourArcher(pane, tour);
                    }
                    else if (tour instanceof Canon) {
                        new VueTourCanon(pane, tour);
                    }
                    else if (tour instanceof ArcX) {
                        new VueTourArcX(pane, tour);
                    }
                    else if (tour instanceof AigleArtilleur) {
                        new VueTourAigleArtilleur(pane, tour);
                    }
                    env.setmessageProperty("La défense: " + tour.getNom() + " a été placée");
                }
            }
            for (Tour tour : change.getRemoved()) {
                System.out.println("les suppressions : " + change.getRemoved());
                env.vendreLaTour(tour);
                Node tourSprite = pane.lookup("#" + tour.getId());
                System.out.println("Id : " + tour.getId());
                pane.getChildren().remove(tourSprite);
            }
        }
    }