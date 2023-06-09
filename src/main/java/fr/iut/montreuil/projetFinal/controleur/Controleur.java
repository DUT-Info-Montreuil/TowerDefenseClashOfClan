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

    @FXML
    private TilePane tilePane;
    @FXML
    private Pane pane;
    @FXML
    private RadioButton ajouterTour;
    private Environnement environnement;
    private Timeline gameLoop;
    private int temps;
    private Bfs bfs;
    private Ennemi ennemi ;
    private int dd = (int)(Math.random()*10 );
    private CaseDebut caseDebut;
    private ListChangeListener<Ennemi> listObsE;
    private ListChangeListener<Tour> listObsTour;
    private ListChangeListener<Projectile> listObsProjectile;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.environnement = new Environnement(75, 50);
        this.bfs = new Bfs(environnement,21,2);
        caseDebut = new CaseDebut(environnement);
//        Case d = new Case(bfs.getDebut().get(3).getY(),bfs.getDebut().get(3).getX());
//        bfs.setC(d);

        environnement.unTour();
        ennemi = new Ennemi(0,0,environnement);

        URL ImageTile = Lancement.class.getResource("tiles_12.png");
        Image imTile = new Image(String.valueOf(ImageTile));
        for (int i = 0; i <  environnement.getTerrain().length; i++){
            for (int j =0; j < environnement.getTerrain()[i].length; j++){
                trouverTile(environnement.getTerrain()[i][j], imTile);
            }
        }
        listObsE = new ListObsEnnemi(pane, environnement);
        environnement.getEnnemis().addListener(listObsE);

        listObsTour = new ListObsTour(pane, environnement);
        environnement.getListeTour().addListener(listObsTour);

        listObsProjectile = new ListObsProjectile(pane, environnement);
        environnement.getListeProjectile().addListener(listObsProjectile);

        initAnimation();
        gameLoop.play();
    }

    public int getDd() {
        return dd;
    }

    @FXML
    void ajouter(ActionEvent event) {
        Ennemi ennemi = new Ennemi(50,50,environnement) ;
        environnement.ajouterEnnemi(ennemi);
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

    @FXML
    void creerTour(Tour tour){
        URL url = Lancement.class.getResource("tda_Coc.png");
        Image image = new Image(String.valueOf(url));
        ImageView imageView = new ImageView(image);
        imageView.setTranslateX(tour.getX()-24);
        imageView.setTranslateY(tour.getY()-24);
        pane.getChildren().add(imageView);
    }

    public void placerTour(MouseEvent event) {
        if (ajouterTour.isSelected()) {
            Tour tour = new Tour("Tour d'archer", event.getX(), event.getY(), environnement);
            environnement.ajouterTour(tour);
            creerTour(tour);
        }
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.2),
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

                    //environnement.unTour();

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