package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.modele.*;
import fr.iut.montreuil.projetFinal.Lancement;
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
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private Environnement environnement;
    @FXML
    private TilePane tilePane;
    @FXML
    private Pane pane;
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
    private RadioButton ajouterCanon;
    private Hdv hdv;
    private ListChangeListener<Ennemi> listObsEnnemi;
    private ListChangeListener<Tour> listObsTour;
    private ListChangeListener<Projectile> listObsProjectile;
    @FXML
    private Label NbMort;
    @FXML
    private Label NbVivant;
    @FXML
    private Label PvHdv;

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
        listObsEnnemi = new ListObsEnnemi(pane, environnement,NbVivant,NbMort);
        environnement.getEnnemis().addListener(listObsEnnemi);

        listObsTour = new ListObsTour(pane, environnement);
        environnement.getListeTour().addListener(listObsTour);

        listObsProjectile = new ListObsProjectile(pane, environnement);
        environnement.getListeProjectile().addListener(listObsProjectile);

        compteurOr.textProperty().bind(environnement.orProperty().asString());
        messageJoueur.textProperty().bind(environnement.messageProperty());
        PvHdv.textProperty().bind(hdv.pv().asString());

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

    public void choixTour(MouseEvent event) {
        if (environnement.getTerrain()[(int) event.getY() / 16][(int) event.getX() / 16] == 63) {
            if (ajouterTourArchers.isSelected()) {
                TourArchers tour = new TourArchers(event.getX(), event.getY(), environnement);
                environnement.ajouterTour(tour);
            } else if (ajouterCanon.isSelected()) {
                Canon tour = new Canon(event.getX(), event.getY(), environnement);
                environnement.ajouterTour(tour);
            }
        }
        else{
            environnement.setmessageProperty("Vous ne pouvez pas placer votre tour ici");
        }
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.5),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
//                    if (ennemi.estArriver() || !ennemi.estVivant()){
//                        suprimerSprite();
//                    }
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