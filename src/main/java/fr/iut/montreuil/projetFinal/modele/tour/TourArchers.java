package fr.iut.montreuil.projetFinal.modele.tour;

import fr.iut.montreuil.projetFinal.modele.*;
import fr.iut.montreuil.projetFinal.modele.projectile.Fleche;
import fr.iut.montreuil.projetFinal.modele.projectile.Projectile;

public class TourArchers extends Tour {

    public TourArchers (double x,double y, Environnement env){
        super("Tour d'archers",x,y,env,50 , 100 , 30);
    }

    @Override
    public void tir() {
        Ennemi e = this.essaieTir();
        System.out.println("action tir" + e);
        if (e != null){
            if (e.estVivant()) {
                Projectile p = new Fleche(this.getX(), this.getY(), e, env);
                env.ajouterProjectile(p);
                System.out.println("pv ennemi : " + e.getPvProperty());
            }
        }
    }
}
