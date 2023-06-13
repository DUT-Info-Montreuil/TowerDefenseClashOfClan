package fr.iut.montreuil.projetFinal.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Hdv {
    protected Environnement env;
    private IntegerProperty pv;
    public Hdv(Environnement env){
        pv= new SimpleIntegerProperty(100);
        this.env = env;
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

//    public void setPv(int pv) {
//        this.pv = pv;
//    }
}
