package fr.iut.montreuil.projetFinal.modele.ennemi;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.Hdv;
import fr.iut.montreuil.projetFinal.modele.Vague;

public class Géant extends Ennemi {

    public Géant(int y, int x, Environnement env, Hdv hdv, Vague vague) {
        super(y, x, env,(int) (400 * vague.getVagueAugmentePv()),1,(int)5* vague.getVagueAugmenteOr(),hdv,vague,5* vague.getVagueAugmenteDegat());
        System.out.println("pv archer " + getDegat());
    }



}
