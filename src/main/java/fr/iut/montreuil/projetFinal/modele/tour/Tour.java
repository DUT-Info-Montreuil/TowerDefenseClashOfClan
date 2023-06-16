package fr.iut.montreuil.projetFinal.modele.tour;


import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public abstract class Tour {

    private String nom;
    private String id;
    private DoubleProperty x;
    private DoubleProperty y;
    private int degat;
    private int portee;
    public static int compteur = 0;
    protected Environnement env;
    private IntegerProperty prix;
    private int vente;

    public Tour(String nom, double x, double y, Environnement env, int prix, int portee, int vente) {
        this.nom = nom;
        this.id = "T" + compteur;
        compteur++;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.portee = portee;
        this.env = env;
        this.prix = new SimpleIntegerProperty(prix);
        this.vente = vente;
    }

    public String getId() {
        return this.id;
    }

    public String getNom() {
        return nom;
    }

    public final int getPrix() {
        return prix.getValue();
    }
    
    public final IntegerProperty getPrixProperty(){return prix;}

    public int getVente(){
        return vente;
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

    public Ennemi essaieTir(){
        for (Ennemi e : this.env.getEnnemis()){
            while ((this.getX()-this.portee <= e.getX() && e.getX()<=this.getX()+this.portee) && (this.getY()-this.portee <= e.getY() && e.getY()<=this.getY()+this.portee)){
                return e;
            }
        }
        return null;
    }

    public abstract void tir();
}
