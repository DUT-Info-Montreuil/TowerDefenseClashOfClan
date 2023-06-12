package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.Projectile;
import fr.iut.montreuil.projetFinal.modele.Tour;
import fr.iut.montreuil.projetFinal.vue.VueTourArcher;
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
            if (change.wasAdded()) {
                for (Tour tour : change.getAddedSubList()) {
                    new VueTourArcher(pane, tour);
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
