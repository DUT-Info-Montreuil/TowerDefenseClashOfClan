package fr.iut.montreuil.projetFinal.modele;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;

public class Tour extends Node {

    private String nom;
    DoubleProperty x;
    DoubleProperty y;
    private int degat;
    private int portee;
    protected Environnement env;
    private Projectile projectile;

    public Tour(String nom, double x,double y, Environnement env){
        this.nom = nom;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.portee = 100;
        this.degat = 30;
        this.env = env;
    }

    public int getDegat() {
        return degat;
    }

    public final double getX() {
        return x.get();
    }

    public final DoubleProperty getXProperty(){return x;}

    public final double getY() {
        return y.get();
    }

    public final DoubleProperty getYProperty(){return y;}

    public Ennemi essaieTir(){
        for (Ennemi e : this.env.getEnnemis()){
            while ((this.getX()-portee <= e.getX() && e.getX()<=this.getX()+portee) && (this.getY()-portee <= e.getY() && e.getY()<=this.getY()+portee)){
                return e;
            }
        }
        return null;
    }

    public void tir(){
        Ennemi e = this.essaieTir();
        if (e != null ){
            /*Projectile p = new Projectile(this.getX(), this.getY(), e);
            env.ajouterProjectile(p);
            p.trajectoreProjectile();*/
            System.out.println("ennemi touché");
            e.recoiDegat(degat);
            System.out.println("pv ennemi : " + e.getPv());

        }
    }

    /*public boolean cibleTouchee(Ennemi e){
        if (projectile.getX() == e.getX() && projectile.getY() == e.getY()){
            return  true;
        }
        return false;
    }*/
}
