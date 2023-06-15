package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.modele.*;
import fr.iut.montreuil.projetFinal.vue.VueBoulet;
import fr.iut.montreuil.projetFinal.vue.VueFleche;
import fr.iut.montreuil.projetFinal.vue.VueFlecheArcX;
import fr.iut.montreuil.projetFinal.vue.VueProjectile;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ListObsProjectile implements ListChangeListener<Projectile> {

    private Environnement env;
    @FXML
    private Pane pane;

    public ListObsProjectile(Pane pane, Environnement env){
        this.pane = pane;
        this.env = env;
    }

    @Override
    public void onChanged(Change<? extends Projectile> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Projectile p : change.getAddedSubList()) {
                    if (p instanceof Fleche) {
                        new VueFleche(pane, env, p);
                    }
                    else if (p instanceof Boulet){
                        new VueBoulet(pane, env, p);
                    }
                    else if (p instanceof FlecheArcX){
                        new VueFlecheArcX(pane, env, p);
                    }
                }
            }
            if (change.wasRemoved()) {
                for (Projectile p : change.getRemoved()) {
                    Node pro = (Node) pane.lookup("#" + p.getId());
                    pane.getChildren().remove(pro);
                }
            }
        }
    }
}
