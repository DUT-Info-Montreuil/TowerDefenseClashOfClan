package fr.iut.montreuil.projetFinal.modele;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;

public class Barbare extends Ennemi {

    public Barbare(int y, int x, Environnement env, Hdv hdv) {

        super( y, x,env,100,1,1, hdv);

    }

}
