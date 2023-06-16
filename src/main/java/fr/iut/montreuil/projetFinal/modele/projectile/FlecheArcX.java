package fr.iut.montreuil.projetFinal.modele.projectile;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;

public class FlecheArcX extends Projectile {

    public FlecheArcX(double x, double y, Ennemi e, Environnement env) {
        super(x, y, e, env, 15, 20);
    }
}
