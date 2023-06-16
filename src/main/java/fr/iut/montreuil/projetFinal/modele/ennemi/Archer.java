package fr.iut.montreuil.projetFinal.modele.ennemi;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.Hdv;
import fr.iut.montreuil.projetFinal.modele.Vague;

public class Archer extends Ennemi {

    public Archer(int y, int x, Environnement env, Hdv hdv, Vague vague) {
        super(y, x, env,(int) (70 * vague.getVagueAugmentePv()),2,(int)1* vague.getVagueAugmenteOr(), hdv,vague,1* vague.getVagueAugmenteDegat());
    }

}
