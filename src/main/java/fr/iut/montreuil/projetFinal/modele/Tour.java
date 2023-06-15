package fr.iut.montreuil.projetFinal.modele;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Tour {

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

    private int vente;

    private boolean estVendue;

    public Tour(String nom, double x, double y, Environnement env, int prix, int portee, int degat , int vente) {
        this.nom = nom;
        this.id = "T" + compteur;
        compteur++;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.portee = portee;
        this.degat = degat;
        this.env = env;
        this.prix = prix;
        this.vente = vente;
        this.estVendue = false;
    }

    public boolean getEstVendue(){return estVendue;}

    public void setEstVendue(boolean b){this.estVendue = b;}

    public String getId() {
        return this.id;
    }

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }

    public int getVente(){
        return vente;
    }

    public int getDegat() {
        return degat;
    }

    public final double getX() {
        return x.getValue();
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public void setY(double y) {
        this.y.set(y);
    }

    public final DoubleProperty getXProperty() {
        return x;
    }

    public final double getY() {
        return y.getValue();
    }

    public final DoubleProperty getYProperty() {
        return y;
    }

//    public Tour chercherTour(double x, double y) {
//        for (Tour t : env.getListeTour()) {
//            if (t.getX() <= x && t.getY() <= y && x < t.getX()+48 && y < t.getY()+48){
//            //if ((this.getX()-48 <= t.getX() && t.getX()<=this.getX()+48) && (this.getY()-48 <= t.getY() && t.getY()<=this.getY()+48)){
//                System.out.println("rentré dans tour");
//                return t;
//            }
//        }
//        return null;
//    }

    public Ennemi essaieTir(){
        for (Ennemi e : this.env.getEnnemis()){
            while ((this.getX()-this.portee <= e.getX() && e.getX()<=this.getX()+this.portee) && (this.getY()-this.portee <= e.getY() && e.getY()<=this.getY()+this.portee)){
                return e;
            }
        }
        return null;
    }

    public void tir(){
        Ennemi e = this.essaieTir();
        if (e != null){
            if (e.estVivant()) {
                Projectile p = new Projectile(this.getX(), this.getY(), e, env);
                env.ajouterProjectile(p);

                e.recoitDegat(degat);
            }
        }

    }

}
