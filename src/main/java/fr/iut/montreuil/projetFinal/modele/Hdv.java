package fr.iut.montreuil.projetFinal.modele;

public class Hdv {
    protected Environnement env;
    private int pv;
    public Hdv(Environnement env){
        pv=100;
        this.env = env;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }
}
