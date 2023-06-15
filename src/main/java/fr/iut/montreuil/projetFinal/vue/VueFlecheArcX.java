package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.Projectile;
import javafx.scene.layout.Pane;

public class VueFlecheArcX extends VueProjectile{
    public VueFlecheArcX(Pane pane, Environnement env, Projectile p) {
        super(pane, env, p, "fleche.png");
    }
}