package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.modele.*;
import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.controleur.ListObsEnnemi;
import fr.iut.montreuil.projetFinal.modele.*;

import fr.iut.montreuil.projetFinal.modele.projectile.Projectile;
import fr.iut.montreuil.projetFinal.modele.tour.*;
import fr.iut.montreuil.projetFinal.vue.VueTerrain;
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
    private ListChangeListener<Ennemi> listObsEnnemi;
    private ListChangeListener<Tour> listObsTour;
    private ListChangeListener<Projectile> listObsProjectile;
    private boolean isPaused = false;
    private Vague vague;
    private int compteurClick = 0;
    @FXML
    private TilePane tilePane;
    @FXML
    private Pane pane;
    @FXML
    private Label compteurOr;
    @FXML
    private Label messageJoueur;
    @FXML
    private Label NbMort;
    @FXML
    private Label NbVivant;
    @FXML
    private Label PvHdv;
    @FXML
    private ProgressBar VieEnnemi;
    @FXML
    private RadioButton vendreTour;
    @FXML
    private Label labelTourArcher;
    @FXML
    private Label labelCanon;
    @FXML
    private Label labelArcX;
    @FXML
    private Label labelArtilleur;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.environnement = new Environnement(75, 50);
        this.bfs = new Bfs(environnement, 21, 2); // Il commence à la case [21;2] par default
        this.vague = new Vague(environnement);

        new VueTerrain(tilePane,environnement);

        listObsEnnemi = new ListObsEnnemi(pane, environnement, NbVivant, NbMort);
        environnement.getEnnemis().addListener(listObsEnnemi);

        listObsTour = new ListObsTour(pane, environnement);
        environnement.getListeTour().addListener(listObsTour);

        listObsProjectile = new ListObsProjectile(pane, environnement);
        environnement.getListeProjectile().addListener(listObsProjectile);

        compteurOr.textProperty().bind(environnement.orProperty().asString());
        messageJoueur.textProperty().bind(environnement.messageProperty());
        PvHdv.textProperty().bind(environnement.getHdv().pv().asString());

        mettreAJourPrix();

        this.environnement.getHdv().pvProperty().addListener((observableValue, number, t1) -> {
            double nb = Double.valueOf(t1.toString()) / 100;
            VieEnnemi.setProgress(nb);
            System.out.println("progresbar: " + VieEnnemi);
        });
        initAnimation();
        gameLoop.play();
    }

    @FXML
    public void ajouterTourArcher(ActionEvent event) {
        pane.setOnMouseClicked(mouseEvent -> {
            if (environnement.getTerrain()[(int)mouseEvent.getY()/16][(int) mouseEvent.getX()/16] == 63) {
                Tour tour = new TourArchers(mouseEvent.getX(), mouseEvent.getY(), environnement);
                environnement.essaiDébiterOr(tour);

            }
            else {
                environnement.setmessageProperty("Vous ne pouvez pas placer votre tour ici");
            }
            this.pane.setOnMouseClicked(null);
            });
    }

    @FXML
    public void ajouterCanon(ActionEvent event) {
        pane.setOnMouseClicked(mouseEvent -> {
            if (environnement.getTerrain()[(int)mouseEvent.getY()/16][(int) mouseEvent.getX()/16] == 63) {
                Tour tour = new Canon(mouseEvent.getX(), mouseEvent.getY(), environnement);
                environnement.essaiDébiterOr(tour);
            }
            else {
                environnement.setmessageProperty("Vous ne pouvez pas placer votre tour ici");
            }
            this.pane.setOnMouseClicked(null);
        });
    }

    @FXML
    public void ajouterArcX(ActionEvent event) {
        pane.setOnMouseClicked(mouseEvent -> {
            if (environnement.getTerrain()[(int)mouseEvent.getY()/16][(int) mouseEvent.getX()/16] == 63) {
                Tour tour = new ArcX(mouseEvent.getX(), mouseEvent.getY(), environnement);
                environnement.essaiDébiterOr(tour);
            }
            else {
                environnement.setmessageProperty("Vous ne pouvez pas placer votre tour ici");
            }
            this.pane.setOnMouseClicked(null);
        });
    }

    @FXML
    public void ajouterAigleArtilleur(ActionEvent event) {
        pane.setOnMouseClicked(mouseEvent -> {
            if (environnement.getTerrain()[(int)mouseEvent.getY()/16][(int) mouseEvent.getX()/16] == 63) {
                Tour tour = new AigleArtilleur(mouseEvent.getX(), mouseEvent.getY(), environnement);
                environnement.essaiDébiterOr(tour);
            }
            else {
                environnement.setmessageProperty("Vous ne pouvez pas placer votre tour ici");
            }
            this.pane.setOnMouseClicked(null);
        });
    }


    public void selectionTour(MouseEvent event){
        System.out.println("est séléctionnéééééééééééééééééééééééé");
        if (vendreTour.isSelected()) {
            System.out.println("vendre tourrrrrrrrrrrrrrrrrrrrrrrr");
            environnement.chercherTour(event.getX(), event.getY());
        }
    }

    public void afficherGameOverScene(){
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL resource = Lancement.class.getResource("vueGameOver.fxml");
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

    public void afficherVictoire(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = Lancement.class.getResource("vueVictoire.fxml");
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
                    if (vague.getVagueProperty() == 6 && environnement.getEnnemis().size() == 0){
                        gameLoop.stop();
                        afficherVictoire();
                    }
                    if (environnement.getHdv().hdvGameOver()){
                        gameLoop.stop();
                        afficherGameOverScene();
                    }
                    environnement.unTour();
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

    @FXML
    void accelererJeu() {
        compteurClick++;
        if (compteurClick == 1) {
            double currentRate = gameLoop.getRate();
            gameLoop.setRate(currentRate * 1.5);
        }
        else if (compteurClick == 2) {
            gameLoop.setRate(2); // double la vitesse
        }
        else {
            gameLoop.setRate(1.0); // Remettre la vitesse initiale
            compteurClick = 0; // Réinitialiser le compteur
        }
    }

    @FXML
    void ralentirJeu(){
        compteurClick++;
        if (compteurClick == 1) {
            double currentRate = gameLoop.getRate();
            gameLoop.setRate(currentRate * 0.5);
        }
        else if (compteurClick == 2) {
            gameLoop.setRate(0.25);
        }
        else {
            gameLoop.setRate(1.0);
            compteurClick = 0;
        }
    }

    public void mettreAJourPrix(){
        Tour tda = new TourArchers(0,0,environnement);
        labelTourArcher.textProperty().bind(tda.getPrixProperty().asString());

        Tour canon = new Canon(0,0,environnement);
        labelCanon.textProperty().bind(canon.getPrixProperty().asString());

        Tour arcX = new ArcX(0,0,environnement);
        labelArcX.textProperty().bind(arcX.getPrixProperty().asString());

        Tour aigleArtilleur = new AigleArtilleur(0,0,environnement);
        labelArtilleur.textProperty().bind(aigleArtilleur.getPrixProperty().asString());
    }
}