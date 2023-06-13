package fr.iut.montreuil.projetFinal.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ProgressBar;

public class Hdv {
    protected Environnement env;
    private IntegerProperty pv;
    private ProgressBar progressBar;
    public Hdv(Environnement env, ProgressBar p){
        pv= new SimpleIntegerProperty(100);
        this.env = env;
        this.progressBar = p;
    }

    public final int getPv(){
        return pv.getValue();
    }

    public final void setPv(int n){
        pv.setValue(n);
    }
    public final IntegerProperty pv(){
        return pv;
    }

    public boolean hdvGameOver(){
        return getPv()<=0;
    }

    public Environnement getEnv() {
        return env;
    }

    public IntegerProperty pvProperty() {
        return pv;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setEnv(Environnement env) {
        this.env = env;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}
