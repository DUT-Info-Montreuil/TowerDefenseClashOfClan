package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.Projectile;
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
                    new VueProjectile(pane, p);
                }
            }
            if (change.wasRemoved()) {
                for (Projectile p : change.getRemoved()) {
                    pane.lookup("#" + p.getId());
                    pane.getChildren().remove(p.getId());
                }
            }
        }

    }

}