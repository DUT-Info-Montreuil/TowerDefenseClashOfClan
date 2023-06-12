package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.modele.*;
import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.controleur.ListObsEnnemi;
import fr.iut.montreuil.projetFinal.modele.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private Environnement environnement;
    @FXML
    private TilePane tilePane;
    @FXML
    private Pane Pane;
    //private Barbare barbare;
    private Timeline gameLoop;
    private int temps;

    private Bfs bfs;
    private Ennemi ennemi ;
    @FXML
    private RadioButton ajouterTourArchers;
    @FXML
    private Label compteurOr;
    @FXML
    private Label messageJoueur;

    @FXML
    private Button ajouter;

    @FXML
    private RadioButton ajouterCanon;

    @FXML
    private Hdv hdv;

    @FXML
    ListChangeListener<Ennemi> listObs;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.environnement = new Environnement(75, 50);
        this.bfs = new Bfs(environnement,21,2);
        this.hdv = new Hdv(environnement);
        System.out.println("hdv : " + hdv.getPv());

        URL ImageTile = Lancement.class.getResource("tiles_12.png");
        Image imTile = new Image(String.valueOf(ImageTile));
        for (int i = 0; i <  environnement.getTerrain().length; i++){
            for (int j =0; j < environnement.getTerrain()[i].length; j++){
                trouverTile(environnement.getTerrain()[i][j], imTile);
            }
        }
        listObs = new ListObsEnnemi(Pane, environnement);
        environnement.getEnnemis().addListener(listObs);
        compteurOr.textProperty().bind(environnement.orProperty().asString());
        messageJoueur.textProperty().bind(environnement.messageProperty());
        initAnimation();
        gameLoop.play();
    }


    @FXML
    void ajouter(ActionEvent event) {

        if (environnement.getNbToursProperty() % 2 == 0){
            Ennemi archer = new Archer(45,45,environnement,hdv);
            environnement.ajouterEnnemi(archer);
        }
        else {
            Ennemi barbare = new Barbare(50,50,environnement,hdv);
            environnement.ajouterEnnemi(barbare);
        }

    }

    @FXML
    void fairTour(ActionEvent event) {
        environnement.unTour();
    }


    @FXML
    private void trouverTile(int id, Image im){
        ImageView imv = new ImageView(im);
        int x,y;
        int taille = 16;
        int largeurMapX = 384;
        int formuleX = taille + (taille * id);
        x = formuleX - (largeurMapX * (formuleX/largeurMapX));
        y = taille + taille * (formuleX/largeurMapX);
        Rectangle2D imviewport = new Rectangle2D(x,y,taille,taille);
        imv.setViewport(imviewport);
        this.tilePane.getChildren().add(imv);
    }

    // Faut nettoyer ici ya de la redondance pour les tours
    @FXML
    void creerTour(Tour tour, URL url){
        Image image = new Image(String.valueOf(url));
        ImageView imageView = new ImageView(image);
        imageView.setTranslateX(tour.getX()-24);
        imageView.setTranslateY(tour.getY()-24);
        Pane.getChildren().add(imageView);

//        Rectangle t = new Rectangle(50, 50);
//        t.setFill(Color.BURLYWOOD);
//        t.setTranslateX(tour.getX() - 25);
//        t.setTranslateY(tour.getY() -25);
//        Pane.getChildren().add(t);
    }


    public void choixTour(MouseEvent event) {
        if (ajouterTourArchers.isSelected()) {
            TourArchers tour = new TourArchers(event.getX(), event.getY(), environnement);
            placerTour(tour);
        } else if (ajouterCanon.isSelected()) {
            Canon tour = new Canon(event.getX(), event.getY(), environnement);
            placerTour(tour);
        }
    }

    public void placerTour (Tour tour){
        if ((environnement.getorProperty() - tour.getPrix()) >= 0) {
            environnement.setorProperty((environnement.getorProperty() - tour.getPrix()));
            environnement.ajouterTour(tour);
            if (tour instanceof TourArchers) {
                URL url = Lancement.class.getResource("tda_Coc.png");
                creerTour(tour,url);
            }
            else if (tour instanceof Canon) {
                URL url = Lancement.class.getResource("canon_Coc.png");
                creerTour(tour,url);
            }
            environnement.setmessageProperty("La défense: " + tour.getNom() + " a été placée");
            //tour.tir();
        }
        else {
            environnement.setmessageProperty("Vous n'avez pas assez d'argent pour placer votre " + tour.getNom());
        }
    }



    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(1),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(temps==10000){
                        System.out.println("fini");
                        gameLoop.stop();
                    }

                    if (environnement.getNbToursProperty() % 2 == 0){
                        Ennemi archer = new Archer(45,45,environnement,hdv);
                        environnement.ajouterEnnemi(archer);
                    }
                    else {
                        Ennemi barbare = new Barbare(50,50,environnement,hdv);
                        environnement.ajouterEnnemi(barbare);
                    }

                    environnement.unTour();

//                    else if (!ennemi.estArriver()){
//                        System.out.println("un tour");
//                        environnement.unTour();
//                        System.out.println("nbr de tour : " + environnement.getTour());
//                        System.out.println(ennemi.estArriver());
//                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}
