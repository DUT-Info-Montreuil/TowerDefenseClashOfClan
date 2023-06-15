package fr.iut.montreuil.projetFinal.modele;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Duration;

public class Projectile  {

    private DoubleProperty x,y;
    private DoubleProperty xCible, yCible;
    private double dx, dy;
    private String id;
    private int vitesse;
    public static int compteur;
    protected Environnement environnement;

    public Projectile(double x, double y, Ennemi e, Environnement env){
        this.id = "P"+compteur;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.xCible = new SimpleDoubleProperty(e.getX());
        this.yCible = new SimpleDoubleProperty(e.getY());
        this.dx = (e.getX() - this.getX()) / distanceTir();
        this.dy = (e.getY() - this.getY()) / distanceTir();
        this.vitesse = 5;
        this.compteur++;
        this.environnement = env;
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

    public double distanceTir(){
        return Math.sqrt(((xCible.getValue() - getX())) + ((yCible.getValue() - getY())));
    }

    public boolean cibleTouchee(){
        if (this.getX() == getxCible() && this.getY() == getyCible()){
            return  true;
        }
        return false;
    }

    public void deplacementProjectile() {

        double deltaX = dx * this.vitesse;
        double deltaY = dy * this.vitesse;

        if (!(getX() == getxCible()) || (getY() == getyCible())){
            x.setValue(getX() + deltaX);
            y.setValue(getY() + deltaY);
        }
    }
}
