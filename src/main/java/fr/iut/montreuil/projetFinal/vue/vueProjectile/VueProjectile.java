package fr.iut.montreuil.projetFinal.vue.vueProjectile;

import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.projectile.Projectile;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class VueProjectile {

    private Environnement environnement;
    private Pane pane;
    private Projectile projectile;
    private String urlImage;

    public VueProjectile(Pane pane, Environnement env, Projectile p, String urlImage){
        this.pane = pane;
        this.environnement = env;
        this.urlImage = urlImage;
        this.projectile = p;
        URL url1 = Lancement.class.getResource(urlImage);
        Image image = new Image(String.valueOf(url1));
        ImageView imageView = new ImageView(image);
        imageView.setId(p.getId());
        imageView.translateXProperty().bind(p.getxProperty());
        imageView.translateYProperty().bind(p.getyProperty());
        pane.getChildren().add(imageView);

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
