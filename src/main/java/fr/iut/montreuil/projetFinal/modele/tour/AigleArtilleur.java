package fr.iut.montreuil.projetFinal.modele.tour;

import fr.iut.montreuil.projetFinal.modele.*;
import fr.iut.montreuil.projetFinal.modele.projectile.Projectile;
import fr.iut.montreuil.projetFinal.modele.projectile.ProjectileAigleA;

public class AigleArtilleur extends Tour {
    public AigleArtilleur (double x,double y, Environnement env){
        super("Aigle Artilleur",x,y,env,500,300,100);
    }

    @Override
    public void tir() {
        Ennemi e = this.essaieTir();
        System.out.println("action tir" );
        if (e != null){
            if (e.estVivant()) {
                Projectile p = new ProjectileAigleA(this.getX(), this.getY(), e, env);
                env.ajouterProjectile(p);
                System.out.println("pv ennemi : " + e.getPvProperty());
            }
        }
    }
}
