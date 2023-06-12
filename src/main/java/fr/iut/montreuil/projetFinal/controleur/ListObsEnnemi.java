package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.vue.VueBarbare;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class ListObsEnnemi implements ListChangeListener<Ennemi> {

    @FXML
    private Pane paneMap;
    private Environnement env;


    public ListObsEnnemi(Pane paneMap, Environnement env) {
        this.paneMap = paneMap;
        this.env = env;
    }

    @Override
    public void onChanged(Change<?extends Ennemi> change) {
        while (change.next()) {
            for (Ennemi e: change.getAddedSubList()) {
                //creerSprite(e);
                new VueBarbare(paneMap, e);
            }
            for (Ennemi e : change.getRemoved()) {
                Node n = paneMap.lookup("#" + e.getId());
                paneMap.getChildren().remove(n);
                if (e.getSuivant() != e.getBfs()){
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