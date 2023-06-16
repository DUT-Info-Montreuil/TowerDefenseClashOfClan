package fr.iut.montreuil.projetFinal.modele.projectile;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;

public class ProjectileAigleA extends Projectile {

    public ProjectileAigleA(double x, double y, Ennemi e, Environnement env) {
        super(x, y, e, env, 50, 7);
    }
}
