package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.modele.Projectile;
import javafx.scene.layout.Pane;

public class VueProjectile {

    private Pane pane;
    private Projectile projectile;

    public VueProjectile(Pane pane, Projectile projectile){
        this.pane =  pane;
        this.projectile = projectile;
    }

}
