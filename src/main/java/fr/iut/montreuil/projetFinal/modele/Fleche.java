package fr.iut.montreuil.projetFinal.modele;

public class Fleche extends Projectile{
    public Fleche(double x, double y, Ennemi e, Environnement env) {
        super(x, y, e, env, 25, 10);
    }
}
