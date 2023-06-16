package fr.iut.montreuil.projetFinal.vue.vueProjectile;

import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.projectile.Boulet;
import fr.iut.montreuil.projetFinal.modele.projectile.Fleche;
import fr.iut.montreuil.projetFinal.modele.projectile.Projectile;
import fr.iut.montreuil.projetFinal.modele.projectile.ProjectileAigleA;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;

public class VueProjectile {

    private Environnement environnement;
    private Pane pane;
    private Projectile projectile;

    public VueProjectile(Pane pane, Environnement env, Projectile p){
        this.pane = pane;
        this.environnement = env;
        this.projectile = p;
        Circle projectile = new Circle();

        if (p instanceof Boulet) {
                projectile.setRadius(5);
                projectile.setFill(Color.BLACK);
        } else if (p instanceof ProjectileAigleA) {
            projectile.setRadius(5);
            projectile.setFill(Color.ORANGERED);
        }
        else {
            projectile.setRadius(3);
            projectile.setFill(Color.GRAY);
        }
        projectile.setId(p.getId());
        projectile.translateXProperty().bind(p.getxProperty());
        projectile.translateYProperty().bind(p.getyProperty());

        pane.getChildren().add(projectile);

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
                        env.enleverProjectile(p);
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
