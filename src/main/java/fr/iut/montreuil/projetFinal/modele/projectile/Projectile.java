package fr.iut.montreuil.projetFinal.modele.projectile;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
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
    private Ennemi ennemi;
    protected Environnement environnement;
    private boolean estTouche = false;
    private int portee;
    private int degat;

    public Projectile(double x, double y, Ennemi e, Environnement env, int degat, int vitesse){
        this.id = "P"+compteur;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.xCible = new SimpleDoubleProperty(e.getX());
        this.yCible = new SimpleDoubleProperty(e.getY());
        this.dx = (e.getX() - this.getX()) / distanceTir();
        this.dy = (e.getY() - this.getY()) / distanceTir();
        this.vitesse = vitesse;
        this.compteur++;
        this.environnement = env;
        this.ennemi = e;
        this.portee = 10;
        this.degat = degat;
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

    public int getDegat() {
        return degat;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public Ennemi ennemiPresent(){
        for (Ennemi e : this.environnement.getEnnemis()){
            while ((this.getX()-this.portee <= e.getX() && e.getX()<=this.getX()+this.portee) && (this.getY()-this.portee <= e.getY() && e.getY()<=this.getY()+this.portee)){
                return e;
            }
        }
        return null;
    }

    public void deplacementProjectile(double elapsedTime) {
        double deltaX = dx * this.vitesse * elapsedTime;
        double deltaY = dy * this.vitesse * elapsedTime;

        if (!(getX() >= getxCible()) || (getY() >= getyCible())){
            x.setValue(getX() + deltaX);
            y.setValue(getY() + deltaY);
        }
    }
}
