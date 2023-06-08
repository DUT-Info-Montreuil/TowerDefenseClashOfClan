package fr.iut.montreuil.projetFinal.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Projectile  {

    private DoubleProperty x,y;
    private double dx, dy;
    private int vitesse;
    private Ennemi ennemi;

    public Projectile(double x, double y, Ennemi e){
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.ennemi = e;
        this.dx = (ennemi.getX() - x) / distanceTir();
        this.dy = (ennemi.getY() - y) / distanceTir();
        this.vitesse = 5;
    }

    public double getX() {
        return x.getValue();
    }

    public DoubleProperty getxProperty(){
        return x;
    }

    public double getY() {
        return y.getValue();
    }

    public DoubleProperty getyProperty(){
        return y;
    }

    public double distanceTir(){
        double distance  = Math.sqrt(((ennemi.getX() - x.getValue())*2) + ((ennemi.getY() - y.getValue())));
        return distance;
    }

    public void trajectoreProjectile(){
        double nposX = this.getX() + (dx * vitesse);
        double nposY = this.getY() + (dy * vitesse);

        this.x.setValue(nposX);
        this.y.setValue(nposY);
    }
}
