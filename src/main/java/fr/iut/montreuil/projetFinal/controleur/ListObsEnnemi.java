package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.modele.*;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class ListObsEnnemi implements ListChangeListener<Ennemi> {
    @FXML
    private Pane paneMap;
    private Environnement env;
    private Label NbVivant;
    private Label NbMort;
    private static int pv = 100;
    private static int mort = 0;


    public ListObsEnnemi(Pane paneMap, Environnement env,Label nbVivant,Label nbMort) {
        this.paneMap = paneMap;
        this.env = env;
        NbVivant = nbVivant;
        NbMort = nbMort;
    }
    @Override
    public void onChanged(Change<?extends Ennemi> change) {
        while (change.next()) {

            NbVivant.setText("nbEnnemie : "+env.getEnnemis().size());
            for (Ennemi e: change.getAddedSubList()) {
                creerSprite(e);
            }
            for (Ennemi e : change.getRemoved()) {
                Node n = paneMap.lookup("#" + e.getId());
                paneMap.getChildren().remove(n);
                if (e.getSuivant() + 1 != e.getBfs() && e.getSuivant() != e.getBfs()) {
                    mort++;
                    pv--;
                    NbMort.setText("NbMort : "+mort);
                    env.setorProperty(env.getorProperty() + e.getOrTroupe());
                    URL url = Lancement.class.getResource("Clashofclans-tombe-1.png");
                    Image image = new Image(String.valueOf(url));
                    ImageView imageView = new ImageView(image);
                    imageView.setX(e.getX());
                    imageView.setY(e.getY());
                    paneMap.getChildren().add(imageView);
                }
            }
        }
    }

    public void creerSprite(Ennemi ennemie) {
        if (ennemie instanceof Barbare){
            URL url = Lancement.class.getResource("barbareV.jpeg");
            Image image = new Image(String.valueOf(url));
            ImageView imageView = new ImageView(image);
            imageView.setId(ennemie.getId());
            imageView.translateXProperty().bind(ennemie.xProperty());
            imageView.translateYProperty().bind(ennemie.yProperty());
            paneMap.getChildren().add(imageView);
        }
        else if (ennemie instanceof Archer){
            URL url = Lancement.class.getResource("archerV.png");
            Image image = new Image(String.valueOf(url));
            ImageView imageView = new ImageView(image);
            imageView.setId(ennemie.getId());
            imageView.translateXProperty().bind(ennemie.xProperty());
            imageView.translateYProperty().bind(ennemie.yProperty());
            paneMap.getChildren().add(imageView);
        }
        else if (ennemie instanceof Géant){
            URL url = Lancement.class.getResource("géant.png");
            Image image = new Image(String.valueOf(url));
            ImageView imageView = new ImageView(image);
            imageView.setId(ennemie.getId());
            imageView.translateXProperty().bind(ennemie.xProperty());
            imageView.translateYProperty().bind(ennemie.yProperty());
            paneMap.getChildren().add(imageView);
        }

    }
}