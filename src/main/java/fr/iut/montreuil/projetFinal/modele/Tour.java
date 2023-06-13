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
    private int prix;

    public Tour(String nom, double x,double y, Environnement env , int prix , int portee ,int degat){
        this.nom = nom;
        this.id = "T" + compteur;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.portee = portee;
        this.degat = degat;
        this.env = env;
        this.compteur++;
        this.prix = prix;
    }

    public String getId(){
        return this.id;
    }

    public String getNom(){return nom;}

    public  int getPrix(){return prix;}

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
            while ((this.getX()-this.portee <= e.getX() && e.getX()<=this.getX()+this.portee) && (this.getY()-this.portee <= e.getY() && e.getY()<=this.getY()+this.portee)){
                System.out.println("Portée : " + portee);
                return e;
            }
        }
        return null;
    }

    public void tir(){
        Ennemi e = this.essaieTir();
        System.out.println("action tir" );
        if (e != null){
            if (e.estVivant()) {
                System.out.println("ennemi non nul");
                Projectile p = new Projectile(this.getX(), this.getY(), e, env);
                env.ajouterProjectile(p);

                System.out.println("ennemi touché");
                e.recoitDegat(degat);
                System.out.println("pv ennemi : " + e.getPv());
            }

            /*if (p.cibleTouchee()) {
                   env.enleverProjectile(p);
               }*/
        }
    }

}
