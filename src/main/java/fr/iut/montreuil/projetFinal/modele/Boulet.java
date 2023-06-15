package fr.iut.montreuil.projetFinal.modele;

public class Boulet extends Projectile{
    public Boulet(double x, double y, Ennemi e, Environnement env) {
        super(x, y, e, env, 50, 5);
    }
}
