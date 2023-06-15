package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.Projectile;
import javafx.scene.layout.Pane;

public class VueBoulet extends VueProjectile{
    public VueBoulet(Pane pane, Environnement env, Projectile p) {
        super(pane, env, p, "boulet.png");
    }
}
