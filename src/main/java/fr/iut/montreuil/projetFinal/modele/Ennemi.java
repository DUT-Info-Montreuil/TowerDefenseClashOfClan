package fr.iut.montreuil.projetFinal.modele;


import fr.iut.montreuil.projetFinal.controleur.Controleur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Ennemi {

    protected Environnement env;
    private String id;
    private int vitesse;
    private IntegerProperty xProperty, yProperty;
    private int orTroupe;

    private IntegerProperty pv;
    public static int compteur = 0;
    private Bfs bfs;
    private int suivant = 0;
    private CaseDebut caseDebut;
    private int dd = (int) (Math.random() * 10);
    private Hdv hdv;


    public Ennemi(int y, int x, Environnement env, int pv, int v, int orTroupe, Hdv hdv) {
        this.vitesse = v;

        this.id = "A" + compteur;
        compteur++;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.env = env;
        this.pv = new SimpleIntegerProperty(pv);
        this.bfs = new Bfs(this.env, 21, 2);
        caseDebut = new CaseDebut(env);
        this.suivant = 0;
        this.orTroupe = orTroupe;
        this.hdv = hdv;
    }

    public void seDeplacer () {
        this.bfs = new Bfs(env, caseDebut.getcaseDebut().get(dd).getY(), caseDebut.getcaseDebut().get(dd).getX());


        if (suivant < bfs.vraiChemin.size() - 1) {
            suivant = suivant + this.vitesse;
            setY(bfs.vraiChemin.get(getSuivant()).getY() * 16);
            setX(bfs.vraiChemin.get(getSuivant()).getX() * 16);
        }

        if (suivant == bfs.vraiChemin.size() - 1 || suivant + 1 == bfs.vraiChemin.size() - 1) {
            hdv.setPv(hdv.getPv()-1);
            System.out.println(hdv.getPv());
            this.setPv(0);
        }
        System.out.println("--------------------------------------------------------------------------------");

    }

    public int getOrTroupe () {
        return orTroupe;
    }

    public int getSuivant () {
        return suivant;
    }

    public final int getPv () {
        return pv.getValue();
    }

    private void setPv ( int pv){
        this.pv.setValue(pv);
    }

    public int getX () {
        return xProperty.getValue();
    }

    public void setX ( int n){
        xProperty.setValue(n);
    }

    public final IntegerProperty xProperty () {
        return this.xProperty;
    }

    public int getY () {
        return yProperty.getValue();
    }

    public void setY ( int n){
        yProperty.setValue(n);
    }

    public final IntegerProperty yProperty () {
        return this.yProperty;
    }

    public String getId () {
        return id;
    }

    public boolean estVivant () {
        return getPv() > 0;
    }

    public void recoitDegat ( int degatInflige){
        if ((this.pv.getValue() - degatInflige) < 0) {
            this.pv.setValue(0);
        }
        this.pv.setValue(pv.getValue() - degatInflige);
    }

    public boolean estArriver () {
        return env.getTour() == bfs.vraiChemin.size();
    }

    public int getBfs () {
        return bfs.vraiChemin.size() - 1;
    }

    public int getVitesse () {
        return vitesse;
    }

    @Override
    public String toString () {
        return "Ennemi{" +
                "id='" + id + '\'' +
                '}';
    }

}