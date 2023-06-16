package fr.iut.montreuil.projetFinal.modele.ennemi;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.Hdv;
import fr.iut.montreuil.projetFinal.modele.Vague;

public class Pekka extends Ennemi {
    public Pekka(int y, int x, Environnement env, Hdv hdv, Vague vague) {
        super(y, x, env,(int) (2000 * vague.getVagueAugmentePv()),1,(int)50* vague.getVagueAugmenteOr(),hdv,vague,20* vague.getVagueAugmenteDegat());
        System.out.println("pv archer " + getDegat());
    }
}
