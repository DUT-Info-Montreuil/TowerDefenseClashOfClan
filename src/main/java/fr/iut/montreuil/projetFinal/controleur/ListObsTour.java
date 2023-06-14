package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.modele.*;
import fr.iut.montreuil.projetFinal.vue.VueTourArcher;
import fr.iut.montreuil.projetFinal.vue.VueTourCanon;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
                if ((env.getorProperty() - tour.getPrix()) >= 0) {
                    env.setorProperty((env.getorProperty() - tour.getPrix()));
                    if (tour instanceof TourArchers) {
                        new VueTourArcher(pane, tour);
                    }
                    else if (tour instanceof Canon) {
                        new VueTourCanon(pane, tour);
                    }
                    env.setmessageProperty("La défense: " + tour.getNom() + " a été placée");
                }
                else {
                    env.setmessageProperty("Vous n'avez pas assez d'argent pour placer votre " + tour.getNom());
                    env.retirerTour(tour);
                }
            }

            if (change.wasRemoved()) {
                for (Tour tour : change.getRemoved()) {
                    Node n = pane.lookup("#" + tour.getId());
                    pane.getChildren().remove(n);
                }
            }
        }
    }
}
