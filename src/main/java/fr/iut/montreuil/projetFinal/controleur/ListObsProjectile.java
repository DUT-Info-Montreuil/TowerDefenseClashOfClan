package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.Projectile;
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
                for (int i = 0; i < change.getAddedSubList().size(); i++) {
                    Projectile projectile = (Projectile) change.getAddedSubList().get(i);
                    creerProjectile(projectile);
                }
            }
            if (change.wasRemoved()) {
                for (int i = change.getRemoved().size() - 1; i >= 0; i--) {
                    Projectile projectile = (Projectile) change.getRemoved().get(i);
                    Node n = pane.lookup("#" + projectile.getId());
                    pane.getChildren().remove(n);
                }
            }
        }

    }

    public void creerProjectile(Projectile p){
        Circle boulet = new Circle(3);
        boulet.setFill(Color.BLACK);
        boulet.setTranslateX(p.getX());
        boulet.setTranslateY(p.getY());
        pane.getChildren().add(boulet);
    }
}
