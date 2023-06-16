package fr.iut.montreuil.projetFinal.modele.projectile;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;

public class Fleche extends Projectile {

    public Fleche(double x, double y, Ennemi e, Environnement env) {
        super(x, y, e, env, 25, 200);
    }
}
