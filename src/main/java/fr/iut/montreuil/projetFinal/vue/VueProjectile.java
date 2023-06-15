package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.modele.Projectile;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;

public class VueProjectile {

    public VueProjectile(Pane pane, Projectile p){
        Circle boulet = new Circle(3);
        boulet.setFill(Color.BLACK);
        boulet.translateXProperty().bind(p.getxProperty());
        boulet.translateYProperty().bind(p.getyProperty());

        pane.getChildren().add(boulet);
    }
}
