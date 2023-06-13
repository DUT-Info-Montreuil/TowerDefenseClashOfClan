package fr.iut.montreuil.projetFinal.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Vague {
    private Environnement env;
    private IntegerProperty vagueProperty;
    private double vagueAugmenteDegat = 1;
    private double vagueAugmentePv = 1;
    private int vagueAugmenteOr = 1;

    public Vague(Environnement env){
        this.env = env;
        this.vagueProperty = new SimpleIntegerProperty(1);
    }

    public void augmenterVague(){
        setVagueProperty(getVagueProperty()+1);
        setVagueAugmenteDegat(getVagueAugmenteDegat()+0.5);
        setVagueAugmentePv(getVagueAugmentePv()+0.1);
        setVagueAugmenteOr(getVagueAugmenteOr()+1);
        System.out.println("vagueAugmenté : " + getVagueAugmenteDegat());
        System.out.println("vague Sup : " + getVagueProperty());
    }

    public double getVagueAugmenteDegat() {
        return vagueAugmenteDegat;
    }

    public void setVagueAugmenteDegat(double vagueAugmentePv) {
        this.vagueAugmenteDegat = vagueAugmentePv;
    }

    public double getVagueAugmentePv() {
        return vagueAugmentePv;
    }

    public void setVagueAugmentePv(double vagueAugmentePv) {
        this.vagueAugmentePv = vagueAugmentePv;
    }

    public final int getVagueProperty() {
        return vagueProperty.get();
    }

    public void setVagueProperty(int vagueProperty) {
        this.vagueProperty.set(vagueProperty);
    }

    public final IntegerProperty VagueProperty(){
        return this.vagueProperty;
    }

    public int getVagueAugmenteOr() {
        return vagueAugmenteOr;
    }

    public void setVagueAugmenteOr(int vagueAugmenteOr) {
        this.vagueAugmenteOr = vagueAugmenteOr;
    }
}
