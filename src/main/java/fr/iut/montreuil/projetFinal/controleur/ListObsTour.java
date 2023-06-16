package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.modele.*;
import fr.iut.montreuil.projetFinal.modele.tour.*;
import fr.iut.montreuil.projetFinal.vue.vueTour.VueTourAigleArtilleur;
import fr.iut.montreuil.projetFinal.vue.vueTour.VueTourArcX;
import fr.iut.montreuil.projetFinal.vue.vueTour.VueTourArcher;
import fr.iut.montreuil.projetFinal.vue.vueTour.VueTourCanon;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

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