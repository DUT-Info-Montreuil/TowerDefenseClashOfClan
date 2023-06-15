package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.Projectile;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;

public class VueProjectile {

    private Environnement environnement;
    private Pane pane;

    public VueProjectile(Pane pane, Environnement e, Projectile p){
        this.pane = pane;
        this.environnement = e;
        Circle boulet = new Circle(3);
        boulet.setFill(Color.BLACK);
        boulet.setId(p.getId());
        boulet.translateXProperty().bind(p.getxProperty());
        boulet.translateYProperty().bind(p.getyProperty());

        pane.getChildren().add(boulet);

        AnimationTimer timer = new AnimationTimer() {

            private long lastUpdate = 0;

            @Override
            public void handle(long l) {
                if (lastUpdate > 0){
                    double elapsedTime = (l - lastUpdate) / 1000000000.0;

                    p.deplacementProjectile(elapsedTime);
                    Ennemi ennemi = p.ennemiPresent();
                    if (ennemi != null){
                        ennemi.recoitDegat(p.getDegat());
                        e.enleverProjectile(p);
                    }
                }
//                if (p.getX() > environnement.getHeight() && p.getY() > environnement.getWidth()){
//                    environnement.enleverProjectile(p);
//                }
                lastUpdate = l;
            }
        };
        timer.start();
    }
}
