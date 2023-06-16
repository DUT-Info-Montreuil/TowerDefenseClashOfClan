package fr.iut.montreuil.projetFinal.modele.projectile;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;

public class Boulet extends Projectile {

    public Boulet(double x, double y, Ennemi e, Environnement env) {
        super(x, y, e, env, 50, 150);
    }
}
