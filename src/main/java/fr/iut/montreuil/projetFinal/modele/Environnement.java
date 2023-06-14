package fr.iut.montreuil.projetFinal.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {
    private int width, height;
    private int[][] terrain;
    private ObservableList<Ennemi> ennemi;
    private IntegerProperty nbToursProperty;
    private ObservableList<Projectile> listeProjectile;
    private ObservableList<Tour> listeTour;
    private IntegerProperty orProperty;
    private StringProperty messageProperty;

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
        this.orProperty = new SimpleIntegerProperty(150);
        this.messageProperty = new SimpleStringProperty("Bienvenue sur le Tower Defense Clash of Clans");
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

        for (Ennemi e : this.getEnnemis()) {
            e.seDeplacer();
            System.out.println("dans un tour : id : " + e.getId() + " suivant : "+ e.getSuivant());
            System.out.println("x ennemi : " +e.getX() + ", y ennemi : " + e.getY());
        }

        for (int i = ennemi.size()-1; i>=0; i--){
            Ennemi e = ennemi.get(i);
            if (!e.estVivant()){
                System.out.println("mort de : " + e.toString());
                ennemi.remove(i);
            }
        }

        for (Tour t: listeTour) {
            if (t.getX() > 0 && t.getY() > 0){
                t.tir();
            } else {
                System.out.println("MM");
            }

        }
        for (Projectile p : listeProjectile){
            System.out.println("x projectile : "+ (int)p.getX()+ " , y projectile : " + (int)p.getY());
            p.deplacementProjectile();

            /*if (p.cibleTouchee()){
                enleverProjectile(p);
            }*/
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

    public int getTour() {
        return getNbToursProperty();
    }
}
