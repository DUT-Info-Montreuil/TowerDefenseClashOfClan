package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.Lancement;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.net.URL;


public class VueTerrain {

    public VueTerrain (TilePane tilePane, Environnement environnement){
    URL ImageTile = Lancement.class.getResource("tiles_12.png");
    Image imTile = new Image(String.valueOf(ImageTile));
        for (int i = 0; i < environnement.getTerrain().length; i++) {
            for (int j = 0; j < environnement.getTerrain()[i].length; j++) {
            trouverTile(environnement.getTerrain()[i][j], imTile,tilePane);
            }
        }
    }

    private void trouverTile(int id, Image im , TilePane tilePane){
        ImageView imv = new ImageView(im);
        int x,y;
        int taille = 16;
        int largeurMapX = 384;
        int formuleX = taille + (taille * id);
        x = formuleX - (largeurMapX * (formuleX/largeurMapX));
        y = taille + taille * (formuleX/largeurMapX);
        Rectangle2D imviewport = new Rectangle2D(x,y,taille,taille);
        imv.setViewport(imviewport);
        tilePane.getChildren().add(imv);
    }
}
