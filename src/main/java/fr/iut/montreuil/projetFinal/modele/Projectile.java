package fr.iut.montreuil.projetFinal.modele;

import fr.iut.montreuil.projetFinal.vue.VueProjectile;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Projectile  {

    private DoubleProperty x,y;
    private DoubleProperty xCible, yCible;
    private double dx, dy;
    private String id;
    private int vitesse;
    public static int compteur;
    protected Environnement environnement;
    private Ennemi ennemi;
    private VueProjectile vueProjectile;

    public Projectile(double x, double y, Ennemi e, Environnement env){
        this.id = "P"+compteur;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.xCible = new SimpleDoubleProperty(e.getX());
        this.yCible = new SimpleDoubleProperty(e.getY());
        this.dx = (e.getX() - this.getX()) / distanceTir();
        this.dy = (e.getY() - this.getY()) / distanceTir();
        this.vitesse = 15;
        this.compteur++;
        this.environnement = env;
        this.ennemi = e;
    }

    public String getId(){
        return id;
    }

    public double getX() {
        return x.getValue();
    }

    public double getY() {
        return y.getValue();
    }

    public void setVueProjectile(VueProjectile vueProjectile) {
        this.vueProjectile = vueProjectile;
    }

    public double getxCible() {
        return xCible.getValue();
    }

    public double getyCible() {
        return yCible.getValue();
    }

    public DoubleProperty getxProperty(){
        return x;
    }

    public DoubleProperty getyProperty(){
        return y;
    }

    public int getVitesse() {
        return vitesse;
    }


    public Ennemi getCible() {
        return ennemi;
    }


    public double distanceTir(){
        return Math.sqrt(((xCible.getValue() - getX())) + ((yCible.getValue() - getY())));
    }

    public boolean cibleTouche() {
        double hitboxWidth = ennemi.getHitbox().getWidth();
        double hitboxHeight = ennemi.getHitbox().getHeight();

        if (this.getX() >= getxCible() && this.getX() <= getxCible() + hitboxWidth && this.getY() >= getyCible() && this.getY() <= getyCible() + hitboxHeight){
            System.out.println("ennemi touché");
            return true;
        }
        return false;
    }

    public void deplacementProjectile(double elapsedTime) {
        double deltaX = (dx * this.vitesse) * elapsedTime;
        double deltaY = (dy * this.vitesse) * elapsedTime;

        if (!(getX() == getxCible()) || (getY() == getyCible())){
            x.setValue(getX() + deltaX);
            y.setValue(getY() + deltaY);
        }
    }

}