package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.modele.*;
import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.controleur.ListObsEnnemi;
import fr.iut.montreuil.projetFinal.modele.*;

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
    private boolean pause = true;
    private int compteurClick = 0;
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
    private ProgressBar VieEnnemi;
    @FXML
    private RadioButton vendreTour;
    @FXML
    private ToggleGroup selectionDefense;
    @FXML
    private ImageView imageTda;
    @FXML
    private ImageView imageCanon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.environnement = new Environnement(75, 50);
        this.bfs = new Bfs(environnement, 21, 2);
        this.vague = new Vague(environnement);

        new VueTerrain(tilePane,environnement);

        URL urlArcher = Lancement.class.getResource("tda_Coc.png");
        Image imgA = new Image(String.valueOf(urlArcher));
        imageTda.setImage(imgA);

        URL urlCanon = Lancement.class.getResource("canon_Coc.png");
        Image imgC = new Image(String.valueOf(urlCanon));
        imageCanon.setImage(imgC);

        listObsEnnemi = new ListObsEnnemi(pane, environnement, NbVivant, NbMort);
        environnement.getEnnemis().addListener(listObsEnnemi);

        listObsTour = new ListObsTour(pane, environnement);
        environnement.getListeTour().addListener(listObsTour);

        listObsProjectile = new ListObsProjectile(pane, environnement);
        environnement.getListeProjectile().addListener(listObsProjectile);

        compteurOr.textProperty().bind(environnement.orProperty().asString());
        messageJoueur.textProperty().bind(environnement.messageProperty());
        PvHdv.textProperty().bind(environnement.getHdv().pv().asString());

        this.environnement.getHdv().pvProperty().addListener((observableValue, number, t1) -> {
            double nb = Double.valueOf(t1.toString()) / 100;
            VieEnnemi.setProgress(nb);
            System.out.println("progresbar: " + VieEnnemi);
        });
        initAnimation();
        gameLoop.play();
    }


    public void choixTour(MouseEvent event) {
        if (environnement.getTerrain()[(int) event.getY()/16][(int) event.getX()/16] == 63) {
            if (ajouterTourArchers.isSelected()) {
                TourArchers tour = new TourArchers(event.getX(), event.getY(), environnement);
                if (environnement.peutPayerTour(tour)) {
                    environnement.ajouterTour(tour);
                    environnement.payerTour(tour);
                }
                else {
                    environnement.setmessageProperty("Vous n'avez pas assez d'argent pour placer votre " + tour.getNom());
                }
            } else if (ajouterCanon.isSelected()) {
                Canon tour = new Canon(event.getX(), event.getY(), environnement);
                if (environnement.peutPayerTour(tour)) {
                    environnement.ajouterTour(tour);
                    environnement.payerTour(tour);
                }
                else {
                    environnement.setmessageProperty("Vous n'avez pas assez d'argent pour placer votre " + tour.getNom());
                }
            }
            else if (vendreTour.isSelected()) {
                chercherTour(event.getX(), event.getY());
            }
        }
        else {
            environnement.setmessageProperty("Vous ne pouvez pas placer votre tour ici");
        }
    }

    public void chercherTour(double x, double y) {
        for (int i = 0; i < environnement.getListeTour().size(); i++) {
            if (environnement.getListeTour().get(i).getX() <= x && environnement.getListeTour().get(i).getY() <= y && x < environnement.getListeTour().get(i).getX()+48 && y < environnement.getListeTour().get(i).getY()+48){
                System.out.println("Id : " + environnement.getListeTour().get(i).getId());
                System.out.println("rentré dans tour");
                environnement.retirerTour(environnement.getListeTour().get(i));
                System.out.println("Tour supprimée");
            }
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
                    if (environnement.getHdv().hdvGameOver()){
                        gameLoop.stop();
                        afficherGameOverScene();
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

    @FXML
    public void mouseEnteredTourArcher(MouseEvent event){
        imageTda.setFitHeight(40);
        imageTda.setFitWidth(40);
    }

    @FXML
    public void mouseExitTourArcher(MouseEvent event){
        imageTda.setFitHeight(30);
        imageTda.setFitWidth(30);
    }

    @FXML
    public void mouseEnteredCanon(MouseEvent event){
        imageCanon.setFitHeight(40);
        imageCanon.setFitWidth(40);
    }

    @FXML
    public void mouseExitCanon(MouseEvent event){
        imageCanon.setFitHeight(30);
        imageCanon.setFitWidth(30);
    }
}