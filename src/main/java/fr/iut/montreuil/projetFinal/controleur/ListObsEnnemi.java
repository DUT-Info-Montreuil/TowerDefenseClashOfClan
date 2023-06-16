package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.modele.*;
import fr.iut.montreuil.projetFinal.modele.ennemi.*;
import fr.iut.montreuil.projetFinal.vue.vueEnnemi.*;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
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

            NbVivant.setText("Ennemi restant : " + env.getEnnemis().size());
            for (Ennemi e: change.getAddedSubList()) {
                if (e instanceof Barbare){
                    new VueBarbare(paneMap,e);
                } else if (e instanceof Archer) {
                    new VueArcher(paneMap,e);
                }
                else if (e instanceof Géant){
                    new VueGéant(paneMap, e);
                }
                else if (e instanceof Golem){
                    new VueGolem(paneMap,e);
                }
                else if (e instanceof Pekka){
                    new VuePekka(paneMap, e);
                }
            }
            for (Ennemi e : change.getRemoved()) {
                Node n = paneMap.lookup("#" + e.getId());
                paneMap.getChildren().remove(n);
                Node n1 = paneMap.lookup("#"+e.getId()+1);
                paneMap.getChildren().remove(n1);



                if (e.getSuivant() + 1 != e.getBfs() && e.getSuivant() != e.getBfs()) {
                    mort++;
                    pv--;
                    NbMort.setText("Mort : " + mort);
                    env.recupererOrTroupe(e);
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
}