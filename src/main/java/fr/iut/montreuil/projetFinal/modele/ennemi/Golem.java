package fr.iut.montreuil.projetFinal.modele.ennemi;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.Hdv;
import fr.iut.montreuil.projetFinal.modele.Vague;

public class Golem extends Ennemi {
    public Golem(int y, int x, Environnement env, Hdv hdv, Vague vague) {
        super(y, x, env,(int) (1500 * vague.getVagueAugmentePv()),1,(int)50* vague.getVagueAugmenteOr(), hdv,vague,20* vague.getVagueAugmenteDegat());
    }
}
