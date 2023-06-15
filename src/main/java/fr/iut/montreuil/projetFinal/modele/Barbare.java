package fr.iut.montreuil.projetFinal.modele;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;

public class Barbare extends Ennemi {

    public Barbare(int y, int x, Environnement env,Hdv hdv,Vague vague) {
        super( y, x,env,(int) (100* vague.getVagueAugmentePv()),2,(int)(2* vague.getVagueAugmenteOr()),hdv,vague,2* vague.getVagueAugmenteDegat());
    }
}
