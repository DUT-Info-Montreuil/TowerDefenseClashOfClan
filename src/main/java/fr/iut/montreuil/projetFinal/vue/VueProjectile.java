package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.modele.Projectile;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class VueProjectile {

    private Pane pane;
    private Projectile p;
    private ArrayList<Circle> listeBoulet;

    public VueProjectile(Pane pane, Projectile p){
        this.pane = pane;
        this.p = p;
        this.listeBoulet = new ArrayList<>();
        Circle boulet = new Circle(3);
        boulet.setFill(Color.BLACK);
        boulet.setTranslateX(p.getX());
        boulet.setTranslateY(p.getY());

        boulet.translateXProperty().bind(p.getxProperty());
        boulet.translateYProperty().bind(p.getyProperty());
        pane.getChildren().add(boulet);
        listeBoulet.add(boulet);
    }

    public Projectile getProjectile() {
        return p;
    }

    public void retirerCercle(Projectile p) {
        Circle imageViewToRemove = null;
        for (Circle imageView : listeBoulet) {
            if (imageView.getTranslateX() == p.getX() && imageView.getTranslateY() == p.getY()) {
                imageViewToRemove = imageView;
                break;
            }
        }

        if (imageViewToRemove != null) {
            pane.getChildren().remove(imageViewToRemove); // Retirer l'ImageView du panneau de jeu
            listeBoulet.remove(imageViewToRemove); // Retirer l'ImageView de la liste des ImageView

        }
    }
}

