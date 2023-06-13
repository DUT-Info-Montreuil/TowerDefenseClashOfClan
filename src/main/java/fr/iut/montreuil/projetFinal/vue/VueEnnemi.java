package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.modele.Ennemi;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class VueEnnemi {

    private Pane pane;
    private Ennemi ennemi;
    private String urlImage;

    public VueEnnemi(Pane pane, Ennemi e, String urlImage){
        this.pane = pane;
        this.ennemi = e;
        this.urlImage = urlImage;
        URL url1 = Lancement.class.getResource(urlImage);
        Image image = new Image(String.valueOf(url1));
        ImageView imageView = new ImageView(image);
        imageView.setId(e.getId());
        imageView.translateXProperty().bind(e.xProperty());
        imageView.translateYProperty().bind(e.yProperty());
        pane.getChildren().add(imageView);
        e.getHitbox().xProperty().bind(e.xProperty());
        e.getHitbox().yProperty().bind(e.yProperty());
        pane.getChildren().add(e.getHitbox());
    }

}
