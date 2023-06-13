package fr.iut.montreuil.projetFinal.modele;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ImageClickableExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Crée une instance de ImageView avec l'image souhaitée
        Image image = new Image("archerV.png");
        ImageView imageView = new ImageView(image);

        // Définit une taille pour l'ImageView (optionnel)
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        // Ajoute un gestionnaire d'événements pour détecter le clic sur l'image
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Code à exécuter lorsque l'image est cliquée
                System.out.println("Image cliquée !");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        Scene scene = new Scene(root, 400, 400);

        primaryStage.setTitle("Image Clickable Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}