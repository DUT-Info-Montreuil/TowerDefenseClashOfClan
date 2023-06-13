package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.Projectile;
import fr.iut.montreuil.projetFinal.vue.VueProjectile;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class ListObsProjectile implements ListChangeListener<Projectile> {

    private Pane pane;
    private ArrayList<VueProjectile> vuesProjectile;

    public ListObsProjectile(Pane panneauDeJeu) {
        this.pane = panneauDeJeu;
        this.vuesProjectile = new ArrayList<>();
    }

    @Override
    public void onChanged(Change<? extends Projectile> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (int i = 0; i < change.getAddedSubList().size(); i++) {
                    Projectile projectile = (Projectile) change.getAddedSubList().get(i);
                    new VueProjectile(pane, projectile);
                }
            }
            if (change.wasRemoved()) {
                for (int i = change.getRemoved().size() - 1; i >= 0; i--) {
                    Projectile projectile = (Projectile) change.getRemoved().get(i);
                    Node n = pane.lookup("#" + projectile.getId());
                    pane.getChildren().remove(n);
                }
            }
        }

    }

//    private void enleverProjectile (Projectile proj){
//        this.panneauDeJeu.getChildren().remove(this.panneauDeJeu.lookup("#"+ p.getId()));
//        VueProjectile vueProjectileToRemove = null;
//        for (VueProjectile vueProjectile : vuesProjectile) {
//            if (vueProjectile.getProjectile() == proj) { // Recherche la vue correspondant à l'acteur ennemi supprimé
//                vueProjectileToRemove = vueProjectile; // Stocke la vue à supprimer
//                break;
//            }
//        }
//
//        if (vueProjectileToRemove != null) {
//            vueProjectileToRemove.retirerCercle(proj); // Retire l'image de l'acteur ennemi dans la vue
//            vuesProjectile.remove(vueProjectileToRemove); // Supprime la vue des ennemis
//        }
//    }
}

