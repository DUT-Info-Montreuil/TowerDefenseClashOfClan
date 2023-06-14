package fr.iut.montreuil.projetFinal.modele;

public class Archer extends Ennemi{

    public Archer(int y, int x, Environnement env, Hdv hdv,Vague vague) {
        super(y, x, env,(int) (70 * vague.getVagueAugmentePv()),2,(int)1* vague.getVagueAugmenteOr(), hdv,vague,1* vague.getVagueAugmenteDegat());
        System.out.println("pv archer " + getDegat());
    }

}
