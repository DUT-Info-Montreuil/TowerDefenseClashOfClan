package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.Tour;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class VueTour {

    public VueTour(Pane pane, Tour t, String Url){
        URL url1 = Lancement.class.getResource(Url);
        Image image = new Image(String.valueOf(url1));
        ImageView imageView = new ImageView(image);
        imageView.setTranslateX(t.getX()-24);
        imageView.setTranslateY(t.getY()-24);
        pane.getChildren().add(imageView);
    }

}
