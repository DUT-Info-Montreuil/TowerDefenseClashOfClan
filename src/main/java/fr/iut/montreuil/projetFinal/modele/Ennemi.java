package fr.iut.montreuil.projetFinal.modele;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Ennemi {

    protected Environnement env;
    private String id;
    //private int v;
    private IntegerProperty xProperty, yProperty;

    private IntegerProperty pv;
    public static int compteur=0;
    private Bfs bfs;
    private int suivant = 0;
    private CaseDebut caseDebut;
    private int dd = (int)(Math.random()*10 );


    public Ennemi(int y, int x,Environnement env){
        this.id = "A"+compteur;
        compteur++;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.env = env;
        this.pv = new SimpleIntegerProperty(100);
        this.bfs = new Bfs(this.env,21,2);
        caseDebut = new CaseDebut(env);
        this.suivant = 0;
    }

    public  void seDeplacer(){
        System.out.println("dd : " + dd);
        this.bfs = new Bfs(env, caseDebut.getcaseDebut().get(dd).getY(),caseDebut.getcaseDebut().get(dd).getX());
        System.out.println("caseDebut : " + caseDebut.getcaseDebut() + " get 3 : " + caseDebut.getcaseDebut().get(3).getY());
        //Case c = new Case(caseDebut.getcaseDebut().get(3).getY(),caseDebut.getcaseDebut().get(3).getX());


        //this.bfs = new Bfs(this.env, bfs.getDebut().get(controleur.getDd()).getX(), bfs.getDebut().get(controleur.getDd()).getY());
        //Case c = new Case(bfs.getDebut().get(controleur.getDd()).getX(), bfs.getDebut().get(controleur.getDd()).getY());
        //bfs.setC(c);
        //Case d = new Case(bfs.getDebut().get(5).getY(),bfs.getDebut().get(5).getX());
        //bfs.setC(d);

        System.out.println("ennemi : " + getId() + " e.setY : " + bfs.vraiChemin.get(getSuivant()).getY()*16 + " e.setX : " + bfs.vraiChemin.get(getSuivant()).getX()*16 + " suivant : " + getSuivant());


        if (suivant< bfs.vraiChemin.size()-1){
            suivant++;
            setY(bfs.vraiChemin.get(getSuivant()).getY()*16);
            setX(bfs.vraiChemin.get(getSuivant()).getX()*16);
        }
        if (suivant == bfs.vraiChemin.size()-1){
            this.setPv(0);
        }
        System.out.println("--------------------------------------------------------------------------------");

    }

    public int getSuivant() {
        return suivant;
    }

    public final int getPv() {
        return pv.getValue();
    }

    private void setPv(int pv) {
        this.pv.setValue(pv);
    }

    public int getX() {
        return xProperty.getValue();
    }

    public void setX(int n){
        xProperty.setValue(n);
    }

    public final IntegerProperty xProperty(){
        return this.xProperty;
    }

    public int getY() {
        return yProperty.getValue();
    }

    public void setY(int n){
        yProperty.setValue(n);
    }

    public final IntegerProperty yProperty(){
        return this.yProperty;
    }

    public String getId() {
        return id;
    }

    public boolean estVivant() {
        return getPv() > 0;
    }

    public void recoitDegat(int degatInflige){
        if ((this.pv.getValue() - degatInflige) < 0){
            this.pv.setValue(0);
        }
        this.pv.setValue(pv.getValue() - degatInflige);
    }

    public boolean estArriver(){
        return env.getTour() == bfs.vraiChemin.size();
    }

    public int getBfs() {
        return bfs.vraiChemin.size()-1;
    }

    public Tour hitBox(){
        for (Tour t : this.env.getListeTour()){
            while ((this.getX()-2 <= t.getX() && t.getX()<=this.getX()+2) && (this.getY()-2 <= t.getY() && t.getY()<=this.getY()+2)){
                return t;
            }
        }
        return null;
    }
}