package fr.iut.montreuil.projetFinal.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;

public class Tour{

    private String nom;
    private String id;
    private DoubleProperty x;
    private DoubleProperty y;
    private int degat;
    private int portee;
    public static int compteur = 0;
    protected Environnement env;
    private Projectile projectile;

    public Tour(String nom, double x,double y, Environnement env){
        this.nom = nom;
        this.id = "T" + compteur;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.portee = 100;
        this.degat = 30;
        this.env = env;
        this.compteur++;
    }

    public String getId(){
        return this.id;
    }

    public int getDegat() {
        return degat;
    }

    public final double getX() {
        return x.getValue();
    }

    public final DoubleProperty getXProperty(){return x;}

    public final double getY() {
        return y.getValue();
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
            Projectile p = new Projectile(this.getX(), this.getY(), e.getX(), e.getY(), env);
            env.ajouterProjectile(p);

            System.out.println("ennemi touché");
            e.recoiDegat(degat);
            System.out.println("pv ennemi : " + e.getPv());
        }
    }

}
