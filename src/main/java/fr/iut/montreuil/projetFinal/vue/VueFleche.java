package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.Projectile;
import javafx.scene.layout.Pane;

public class VueFleche extends VueProjectile{
    public VueFleche(Pane pane, Environnement e, Projectile p) {
        super(pane, e, p, "fleche.png");
    }
}
