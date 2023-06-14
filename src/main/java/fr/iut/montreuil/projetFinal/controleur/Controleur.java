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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private Environnement environnement;
    private Timeline gameLoop;
    private int temps;
    private Bfs bfs;
    private Ennemi ennemi ;
    private Hdv hdv;
    private ListChangeListener<Ennemi> listObsEnnemi;
    private ListChangeListener<Tour> listObsTour;
    private ListChangeListener<Projectile> listObsProjectile;
    private boolean isPaused = false;
    @FXML
    private TilePane tilePane;
    @FXML
    private Pane pane;
    @FXML
    private RadioButton ajouterTourArchers;
    @FXML
    private Label compteurOr;
    @FXML
    private Label messageJoueur;
    @FXML
    private RadioButton ajouterCanon;
    @FXML
    private Label NbMort;
    @FXML
    private Label NbVivant;
    @FXML
    private Label PvHdv;
    @FXML
    private Vague vague;
    @FXML
    private ProgressBar VieEnnemi;
    @FXML
    private boolean pause = true;
    @FXML
    private ToggleGroup selectionDefense;
    @FXML
    private ImageView imagePausereprendre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.environnement = new Environnement(75, 50);
        this.bfs = new Bfs(environnement, 21, 2);
        this.hdv = new Hdv(environnement, VieEnnemi);
        System.out.println("hdv : " + hdv.getPv());
        //VieEnnemi.setProgress(56);
        this.vague = new Vague(environnement);




        URL ImageTile = Lancement.class.getResource("tiles_12.png");
        Image imTile = new Image(String.valueOf(ImageTile));
        for (int i = 0; i < environnement.getTerrain().length; i++) {
            for (int j = 0; j < environnement.getTerrain()[i].length; j++) {
                trouverTile(environnement.getTerrain()[i][j], imTile);
            }
        }

        listObsEnnemi = new ListObsEnnemi(pane, environnement, NbVivant, NbMort);
        environnement.getEnnemis().addListener(listObsEnnemi);

        listObsTour = new ListObsTour(pane, environnement);
        environnement.getListeTour().addListener(listObsTour);

        listObsProjectile = new ListObsProjectile(pane, environnement);
        environnement.getListeProjectile().addListener(listObsProjectile);

        compteurOr.textProperty().bind(environnement.orProperty().asString());
        messageJoueur.textProperty().bind(environnement.messageProperty());
        PvHdv.textProperty().bind(hdv.pv().asString());

        hdv.pvProperty().addListener((observableValue, number, t1) -> {
            double nb = Double.valueOf(t1.toString()) / 100;
            VieEnnemi.setProgress(nb);
        });
        initAnimation();
        gameLoop.play();
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
        else {
            environnement.setmessageProperty("Vous ne pouvez pas placer votre tour ici");
        }
    }

    public void afficherGameOverScene(){
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL resource = getClass().getResource("/fr/iut/montreuil/projetFinal/f.fxml");
            Parent root = null;
            try {
                    root = fxmlLoader.load(resource);
            } catch (IOException e) {
                e.printStackTrace();
                return; // Arrêter la méthode si une exception se produit Lors du chargement, du fichier. FxML
            }
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) ((Node) pane).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
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
                    if (hdv.hdvGameOver()){
                        gameLoop.stop();
                        afficherGameOverScene();
                    }

                    if (environnement.getNbToursProperty()%2 == 0 && pause == true ) {
                        Ennemi archer = new Archer(45,45,environnement,hdv,vague);
                        environnement.ajouterEnnemi(archer);
                    }
                    else if (environnement.getNbToursProperty() % 3 == 0 && pause == true && vague.getVagueProperty() >= 2){
                        Ennemi barbare = new Barbare(50, 50, environnement, hdv, vague);
                        environnement.ajouterEnnemi(barbare);
                    }
                    else if (environnement.getNbToursProperty()%5 == 0 && pause == true && vague.getVagueProperty() >= 3) {
                        Ennemi géant = new Géant(50,50,environnement,hdv,vague);
                        environnement.ajouterEnnemi(géant);
                    }
                    else {
                        if (pause == true && vague.getVagueProperty() >= 4){
                            Ennemi pekka = new Pekka(50, 50, environnement, hdv, vague);
                            environnement.ajouterEnnemi(pekka);
                        }
                    }

                    if (environnement.getNbToursProperty()%100 == 0 && environnement.getNbToursProperty()!=0){
                        System.out.println("getNbTour : " + environnement.getNbToursProperty());
                        System.out.println("dans tours vague");
                        vague.augmenterVague();
                        pause = false;
                        System.out.println("pause dans vagueeeeeeeeeeeeee  " + pause);
                    }
                    environnement.unTour();

                    if (environnement.getNbToursProperty()%50 == 0 && pause == false){
                        pause = true;
                        System.out.println("dans la methode  pout changer pause " + pause);
                    }

                    System.out.println("tour");
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    @FXML
    void mettrePause(){
        if (isPaused) {
            this.gameLoop.play();
            isPaused = false;
        } else {
            this.gameLoop.pause();
            isPaused = true;
        }
    }
}