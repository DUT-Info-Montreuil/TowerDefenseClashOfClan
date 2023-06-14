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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private TilePane tilePane;
    @FXML
    private Pane pane;
    @FXML
    private RadioButton ajouterTour;

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
    private RadioButton vendreTour;

    @FXML
    private boolean pause = true;

    private Tour tour;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.environnement = new Environnement(75, 50);
        this.bfs = new Bfs(environnement,21,2);
        this.hdv = new Hdv(environnement,VieEnnemi);
        System.out.println("hdv : " + hdv.getPv());
        //VieEnnemi.setProgress(56);
        this.vague = new Vague(environnement);




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
        hdv.pvProperty().addListener((observableValue, number, t1) -> {
            double nb=Double.valueOf(t1.toString())/100 ;
            VieEnnemi.setProgress(nb);
        });
        //VieEnnemi.setProgress(hdv.getPv());
        initAnimation();
        gameLoop.play();
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
        if (environnement.getTerrain()[(int) event.getY()/16][(int) event.getX()/16] == 63) {
            if (ajouterTourArchers.isSelected()) {
                TourArchers tour = new TourArchers(event.getX(), event.getY(), environnement);
                environnement.ajouterTour(tour);
            } else if (ajouterCanon.isSelected()) {
                Canon tour = new Canon(event.getX(), event.getY(), environnement);
                environnement.ajouterTour(tour);
            }
            else if (vendreTour.isSelected()) {
                chercherTour(event.getX(), event.getY());
                /*environnement.setorProperty(environnement.getorProperty() + indiceTour.getVente());
                environnement.setmessageProperty("+ " + indiceTour.getVente() + "Or pour vente de " + indiceTour.getNom());*/
            }
        }
        else {
            environnement.setmessageProperty("Vous ne pouvez pas placer votre tour ici");
        }
    }

    public void chercherTour(double x, double y) {
        for (Tour t : environnement.getListeTour()) {
            if (t.getX() <= x && t.getY() <= y && x < t.getX()+48 && y < t.getY()+48){
                //if ((this.getX()-48 <= t.getX() && t.getX()<=this.getX()+48) && (this.getY()-48 <= t.getY() && t.getY()<=this.getY()+48)){
                System.out.println("rentré dans tour");
                environnement.retirerTour(t);
                System.out.println("Tour supprimée");
                System.out.println("Id : " + t.getId());

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

                    if (hdv.hdvGameOver()){
                        gameLoop.stop();
                        afficherGameOverScene();
                    }

                    if (environnement.getNbToursProperty() % 2 == 0 && pause == true){
                        System.out.println("pause dans créer archer = " + pause);
                        Ennemi archer = new Archer(45,45,environnement,hdv,vague);
                        environnement.ajouterEnnemi(archer);
                    }
                    else {
                        if (pause== true){
                            Ennemi barbare = new Barbare(50,50,environnement,hdv,vague);
                            environnement.ajouterEnnemi(barbare);
                        }
                    }
                    if (environnement.getNbToursProperty()%10 == 0 && environnement.getNbToursProperty()!=0){
                        System.out.println("getNbTour : " + environnement.getNbToursProperty());
                        System.out.println("dans tours vague");
                        vague.augmenterVague();
                        pause = false;
                        System.out.println("pause dans vagueeeeeeeeeeeeee" + pause);
                    }
                    environnement.unTour();
                    if (environnement.getNbToursProperty()%25 == 0 && pause == false){
                        System.out.println("dans la methode  pout changer pause");
                        pause = true;
                    }
                    System.out.println("tour");



                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}