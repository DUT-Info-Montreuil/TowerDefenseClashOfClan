package fr.iut.montreuil.projetFinal.modele;

import fr.iut.montreuil.projetFinal.controleur.Controleur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ProgressBar;

public class Environnement {
    private int width, height;
    private int[][] terrain;
    private ObservableList<Ennemi> ennemi;
    private IntegerProperty nbToursProperty;
    private ObservableList<Projectile> listeProjectile;
    private ObservableList<Tour> listeTour;
    private IntegerProperty orProperty;
    private StringProperty messageProperty;
    private Vague vague;
    private boolean pause = true;
    private Hdv hdv;
    //private ProgressBar VieEnnemi;

    public Environnement(int width, int height){
        this.width = width;
        this.height = height;
        this.terrain = new  int[][]  {
                {144,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,84,85,86,87,88,89,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,84,85,86,87,88,89,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,151,152},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,108,109,110,111,112,113,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,108,109,110,111,112,113,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,19,20,21,22,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,197,197,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,197,197,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,43,44,45,46,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,67,68,69,70,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,19,20,21,22,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,91,92,93,94,209,209,209,209,19,20,21,22,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,43,44,45,46,209,209,209,31,32,33,34,209,209,209,209,209,209,200},
                {168,209,115,116,117,118,209,209,209,209,43,44,45,46,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,67,68,69,70,209,209,209,55,56,57,58,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,67,68,69,70,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,91,92,93,94,209,209,209,79,80,81,82,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,91,92,93,94,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,63,63,63,209,209,209,209,209,209,209,115,116,117,118,209,209,209,103,104,105,106,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,115,116,117,118,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,63,63,63,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,213,213,213,213,213,209,213,213,213,213,213,213,213,209,209,209,209,209,209,209,209,209,209,213,213,63,63,63,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,213,213,213,213,213,209,213,213,213,213,213,213,213,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,19,20,21,22,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,63,63,63,213,213,209,213,213,63,63,63,213,213,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,43,44,45,46,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,63,63,63,213,213,209,213,213,63,63,63,213,213,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,67,68,69,70,209,209,200},
                {168,209,209,209,209,209,209,209,213,213,213,213,213,213,213,209,209,209,213,213,213,213,63,63,63,213,213,209,213,213,63,63,63,213,213,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,31,32,33,34,209,209,209,209,209,209,209,209,209,91,92,93,94,209,209,200},
                {168,209,209,209,209,209,209,209,213,213,213,213,213,213,213,63,63,63,213,213,213,213,209,209,209,213,213,213,213,213,209,209,209,213,213,213,213,213,213,213,213,213,213,213,213,213,213,209,209,209,209,209,209,209,209,55,56,57,58,209,209,209,209,209,209,209,209,209,115,116,117,118,209,209,200},
                {168,209,209,209,209,209,209,209,213,213,63,63,63,213,213,63,63,63,213,213,209,209,209,209,209,213,213,213,213,213,209,209,209,213,213,213,213,213,213,213,213,213,213,213,213,213,213,209,209,209,209,209,209,209,209,79,80,81,82,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {205,158,209,209,209,209,209,209,213,213,63,63,63,213,213,63,63,63,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,63,63,63,213,213,209,209,213,213,209,209,209,209,209,209,209,209,103,104,105,106,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {5,182,209,209,209,209,209,209,213,213,63,63,63,213,213,213,213,213,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,63,63,63,213,213,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {29,182,209,209,209,209,209,209,213,213,209,209,209,213,213,213,213,213,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,63,63,63,213,213,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {53,213,213,213,213,213,213,213,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,213,213,213,213,213,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {77,213,213,213,213,213,213,213,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,213,213,213,213,213,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {101,182,209,209,213,213,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {205,206,209,209,213,213,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,36,37},
                {168,209,209,209,213,213,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,60,61},
                {168,209,209,209,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,197,60,62},
                {168,209,209,209,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,197,60,62},
                {168,209,209,209,209,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,84,85},
                {168,209,209,209,209,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,108,109},
                {168,209,209,209,209,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,63,63,63,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,19,20,21,22,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,63,63,63,209,209,209,209,209,209,209,209,31,32,33,34,209,209,209,209,209,43,44,45,46,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,63,63,63,209,209,209,209,209,209,209,209,55,56,57,58,209,209,209,209,209,67,68,69,70,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,63,63,63,209,209,209,209,209,209,209,209,79,80,81,82,209,209,209,209,209,91,92,93,94,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,19,20,21,22,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,103,104,105,106,209,209,209,209,209,115,116,117,118,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,43,44,45,46,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,31,32,33,34,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,67,68,69,70,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,213,213,213,213,213,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,55,56,57,58,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,91,92,93,94,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,213,213,213,213,213,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,79,80,81,82,209,209,209,209,209,209,209,209,19,20,21,22,209,209,209,209,200},
                {168,209,209,209,209,209,115,116,117,118,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,209,209,209,209,209,103,104,105,106,209,209,209,209,209,209,209,209,43,44,45,46,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,31,32,33,34,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,67,68,69,70,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,55,56,57,58,209,209,209,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,63,63,63,209,209,209,209,209,209,209,209,209,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,209,209,91,92,93,94,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,79,80,81,82,209,209,209,209,209,209,209,209,209,209,63,63,63,213,213,209,209,209,63,63,63,209,209,209,209,31,32,33,34,209,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,213,209,209,115,116,117,118,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,103,104,105,106,209,209,209,209,209,209,209,209,209,209,63,63,63,213,213,209,209,209,63,63,63,209,209,209,209,55,56,57,58,209,209,209,63,63,63,209,209,209,209,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,19,20,21,22,209,209,209,63,63,63,213,213,209,209,209,209,209,209,209,209,209,209,79,80,81,82,209,209,209,63,63,63,209,19,20,21,22,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,31,32,33,34,209,209,209,209,209,209,209,209,209,209,209,209,43,44,45,46,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,103,104,105,106,209,209,209,63,63,63,209,43,44,45,46,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,55,56,57,58,209,209,209,209,209,209,209,209,209,209,209,209,67,68,69,70,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,67,68,69,70,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,79,80,81,82,209,209,209,209,209,209,209,209,209,209,209,209,91,92,93,94,209,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,91,92,93,94,209,209,209,209,209,213,213,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,103,104,105,106,209,209,209,209,209,209,209,209,209,209,209,209,115,116,117,118,209,209,209,209,209,209,197,197,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,115,116,117,118,209,209,209,209,209,197,197,209,209,209,209,209,209,209,209,209,209,200},
                {168,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,36,37,38,39,40,41,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,209,36,37,38,39,40,41,209,209,209,209,209,209,209,209,200},
                {216,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,60,61,62,63,64,65,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,217,60,61,62,63,64,65,217,217,217,217,217,217,217,217,224}
        };
        ennemi = FXCollections.observableArrayList();
        this.nbToursProperty = new SimpleIntegerProperty(0);
        this.listeProjectile = FXCollections.observableArrayList();
        this.listeTour =FXCollections.observableArrayList();
        this.orProperty = new SimpleIntegerProperty(550);
        this.messageProperty = new SimpleStringProperty("Bienvenue sur le Tower Defense Clash of Clans");
        this.hdv = new Hdv(this );
        this.vague = new Vague(this);
    }



    public void chercherTour(double x, double y) {
        for (int i = 0; i < getListeTour().size(); i++) {
            if (getListeTour().get(i).getX()-24 <= x && getListeTour().get(i).getY()-24 <= y && x < getListeTour().get(i).getX()+24 && y < getListeTour().get(i).getY()+24){
                System.out.println("Id : " + getListeTour().get(i).getId());
                System.out.println("rentré dans tour");
                retirerTour(getListeTour().get(i));
                System.out.println("Tour supprimée");
            }
        }
    }

    public String getmessageProperty() {
        return messageProperty.get();
    }

    public void setmessageProperty(String messageProperty) {
        this.messageProperty.set(messageProperty);
    }

    public final StringProperty messageProperty(){
        return messageProperty;
    }

    public int getorProperty() {
        return orProperty.get();
    }

    public void setorProperty(int orProperty) {
        this.orProperty.set(orProperty);
    }

    public final IntegerProperty orProperty(){
        return orProperty;
    }

    public boolean peutPayerTour(Tour t){return (getorProperty() - t.getPrix()) >= 0;}
    public void payerTour(Tour t){ setorProperty((getorProperty() - t.getPrix()));}

    public void vendreLaTour(Tour t){setorProperty(getorProperty() + t.getVente());}

    public void recupererOrTroupe(Ennemi e){setorProperty(getorProperty() + e.getOrTroupe());}

    public int getNbToursProperty() {
        return nbToursProperty.get();
    }

    public void setNbToursProperty(int nbToursProperty) {
        this.nbToursProperty.set(nbToursProperty);
    }

    public final IntegerProperty NbToursProperty(){
        return nbToursProperty;
    }

    public ObservableList<Ennemi> getEnnemis() {
        return ennemi;
    }

    public ObservableList<Tour> getListeTour() {
        return listeTour;
    }

    public ObservableList<Projectile> getListeProjectile() {
        return listeProjectile;
    }

    public Ennemi getEnnemi(String id){
        for (Ennemi e:ennemi) {
            if (e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }

    public void ajouterEnnemi (Ennemi e){
        ennemi.add(e);
    }

    public void unTour(){
        if (this.getNbToursProperty()%2 == 0 && pause == true ) {
            Ennemi archer = new Archer(45,45,this,hdv,vague);
            this.ajouterEnnemi(archer);
        }
        else if (this.getNbToursProperty() % 3 == 0 && pause == true && vague.getVagueProperty() >= 2){
            Ennemi barbare = new Barbare(50, 50, this,hdv, vague);
            this.ajouterEnnemi(barbare);
        }
        else if (this.getNbToursProperty()%5 == 0 && pause == true && vague.getVagueProperty() >= 3) {
            Ennemi géant = new Géant(50,50,this,hdv,vague);
            this.ajouterEnnemi(géant);
        }
        else {
            if (pause == true && vague.getVagueProperty() >= 4){
                Ennemi pekka = new Pekka(50, 50, this,hdv, vague);
                this.ajouterEnnemi(pekka);
            }
        }

        for (Ennemi e : this.getEnnemis()) {
            if (getTour()%2==0) {
                e.seDeplacer();
            }
        }

        for (int i = ennemi.size()-1; i>=0; i--){
            Ennemi e = ennemi.get(i);
            if (!e.estVivant()){
                ennemi.remove(i);
            }
        }

        for (Tour t: listeTour) {
            if (t.getX() > 0 && t.getY() > 0){
                t.tir();
            }
        }
        this.nbToursProperty.setValue(this.nbToursProperty.getValue()+1);
    }

    public int[][] getTerrain() {
        return terrain;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean dansLeTerrain(int y, int x){
        return (0 <= x && x<this.width && 0<=y && y< this.height);
    }

    public void ajouterProjectile (Projectile p){
        listeProjectile.add(p);
    }

    public void enleverProjectile(Projectile p){listeProjectile.remove(p);}

    public void ajouterTour(Tour t){
        listeTour.add(t);
    }

    public void retirerTour(Tour t){
        listeTour.remove(t);
    }

    public int getTour() {
        return getNbToursProperty();
    }

    public Hdv getHdv() {
        return hdv;
    }
}
