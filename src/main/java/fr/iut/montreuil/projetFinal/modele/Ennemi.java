package fr.iut.montreuil.projetFinal.modele;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Rectangle;

public class Ennemi {

    protected Environnement env;
    private String id;
    private int vitesse;
    private IntegerProperty xProperty, yProperty;
    private int orTroupe;
    private IntegerProperty pvProperty;
    public static int compteur = 0;
    protected Bfs bfs;
    private int suivant = 0;
    private CaseDebut caseDebut;
    private int dd = (int) (Math.random() * 10);
    private Hdv hdv;
    private Vague vague;
    private double degat;
    private Rectangle hitbox;


    public Ennemi(int y, int x, Environnement env, int pv, int v, int orTroupe,Hdv hdv, Vague vague, double degat) {
        this.vitesse = v;
        this.degat = degat;
        this.id = "A" + compteur;
        compteur++;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.env = env;
        this.pvProperty = new SimpleIntegerProperty(pv);
        this.bfs = new Bfs(this.env, 21, 2);
        caseDebut = new CaseDebut(env);
        this.suivant = 0;
        this.orTroupe = orTroupe;
        this.hdv = hdv;
        this.vague = vague;
        this.hitbox = new Rectangle(x, y, 16,16);
    }

    public void seDeplacer () {
        this.bfs = new Bfs(env, caseDebut.getcaseDebut().get(dd).getY(), caseDebut.getcaseDebut().get(dd).getX());

        if (suivant < bfs.vraiChemin.size() - 1) {
            suivant = suivant + this.vitesse;
            setY(bfs.vraiChemin.get(getSuivant()).getY() * 16);
            setX(bfs.vraiChemin.get(getSuivant()).getX() * 16);
        }

        if (suivant == bfs.vraiChemin.size() - 1 || suivant + 1 == bfs.vraiChemin.size() - 1) {
            hdv.setPv(hdv.getPv()- (int)(degat * vague.getVagueAugmenteDegat() ));
            System.out.println(hdv.getPv());
            this.setPvProperty(0);
        }
    }

    public double getDegat() {
        return degat;
    }

    public int getOrTroupe () {
        return orTroupe;
    }

    public int getSuivant () {
        return suivant;
    }

    public final int getPvProperty() {
        return pvProperty.getValue();
    }

    public void setPvProperty(int pvProperty){
        this.pvProperty.setValue(pvProperty);
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
        return getPvProperty() > 0;
    }

    public void recoitDegat ( int degatInflige){
        if ((this.pvProperty.getValue() - degatInflige) < 0) {
            this.pvProperty.setValue(0);
        }
        this.pvProperty.setValue(pvProperty.getValue() - degatInflige);
    }

    public final IntegerProperty pvProperty(){
        return this.pvProperty;
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

    public Rectangle getHitbox() {
        return hitbox;
    }

    @Override
    public String toString () {
        return "Ennemi{" +
                "id='" + id + '\'' +
                '}';
    }

}