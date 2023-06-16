package fr.iut.montreuil.projetFinal.vue.vueTour;

import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.modele.tour.Tour;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;

public class VueTour {
    // private Tour tour;

    public VueTour(Pane pane, Tour t, String Url){
        URL url1 = Lancement.class.getResource(Url);
        Image image = new Image(String.valueOf(url1));
        ImageView imageView = new ImageView(image);
        imageView.setId(t.getId());
        imageView.setTranslateX(t.getX()-24);
        imageView.setTranslateY(t.getY()-24);

        imageView.setFitHeight(52);
        imageView.setFitWidth(52);
        imageView.setOnMouseClicked(mouseEvent -> {
            Circle rayonPortee = new Circle(); // Création d'un cercle pour représenter le rayon de portée
            double centerX = t.getX() ; // Calcul du centre du cercle en fonction de la position de la tourelle
            double centerY = t.getY() ;
            rayonPortee.setCenterX(centerX); // Positionnement du centre du cercle
            rayonPortee.setCenterY(centerY);
            rayonPortee.setRadius(t.getPortee()); // Définition du rayon du cercle en fonction de la portée de la tourelle
            rayonPortee.setFill(null); // Aucun remplissage
            rayonPortee.setStroke(Color.rgb(255, 0, 0, 0.8)); // Couleur de contour du cercle
            rayonPortee.setStrokeWidth(2.0); // Épaisseur du contour
            pane.getChildren().add(rayonPortee);// Ajout du cercle au panneau de jeu


            Button buttonRetirer  = new Button();
            buttonRetirer.setText("Retirer");
            buttonRetirer.setTranslateX(t.getX()-15);
            buttonRetirer.setTranslateY(t.getY()+25);
            Button buttonAnnuler  = new Button();
            buttonAnnuler.setText("Annuler");
            buttonAnnuler.setTranslateX(t.getX()-15);
            buttonAnnuler.setTranslateY(t.getY()+48);
//pane.getChildren().add(buttonAmelioration);
            pane.getChildren().add(buttonRetirer);
            pane.getChildren().add(buttonAnnuler);



            buttonRetirer.setOnAction(event -> {
                //t.retirer();
                t.retiréTour();
                //pane.getChildren().remove(buttonAmelioration);
                pane.getChildren().remove(buttonRetirer);
                pane.getChildren().remove(buttonAnnuler);
                pane.getChildren().remove(rayonPortee);
            });

            buttonAnnuler.setOnAction(event -> {
                //pane.getChildren().remove(buttonAmelioration);
                pane.getChildren().remove(buttonRetirer);
                pane.getChildren().remove(buttonAnnuler);
                pane.getChildren().remove(rayonPortee);
            });

        });
        pane.getChildren().add(imageView);

    }
    //}
}

