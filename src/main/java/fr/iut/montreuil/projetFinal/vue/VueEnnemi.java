package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.modele.Ennemi;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class VueEnnemi {
    private ProgressBar progressBarEnnemi;
    private Ennemi ennemi;
    private Pane pane;

    public VueEnnemi(Pane pane, Ennemi e, String urlImage){
        this.pane = pane;
        this.ennemi = e;
        this.progressBarEnnemi = new ProgressBar();
        progressBarEnnemi.translateYProperty().bind(ennemi.yProperty());
        progressBarEnnemi.translateXProperty().bind(ennemi.xProperty());
        progressBarEnnemi.setPrefWidth(30);
        progressBarEnnemi.setPrefHeight(10);

        if (ennemi.getPvProperty() > 20) {
            progressBarEnnemi.progressProperty().bind(ennemi.pvProperty().divide(80.0));
            String barColor = "-fx-accent: red;";
            String trackColor = "-fx-control-inner-background: white;";
            progressBarEnnemi.setStyle(barColor + trackColor);
        } else {
            progressBarEnnemi.setStyle("-fx-accent: red;");
        }


        progressBarEnnemi.setId(ennemi.getId() + 1);


        URL url1 = Lancement.class.getResource(urlImage);
        Image image = new Image(String.valueOf(url1));
        ImageView imageView = new ImageView(image);
        imageView.setId(e.getId());
        imageView.translateXProperty().bind(e.xProperty());
        imageView.translateYProperty().bind(e.yProperty());
        pane.getChildren().add(imageView);
        this.pane.getChildren().add(progressBarEnnemi);
    }
}
