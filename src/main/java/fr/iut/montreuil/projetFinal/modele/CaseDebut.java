package fr.iut.montreuil.projetFinal.modele;

import java.util.ArrayList;

public class CaseDebut {
    private Environnement env;
    private ArrayList<Case> debut = new ArrayList<>();

    public CaseDebut(Environnement env){
        this.env = env;
        remplirDebut();
    }

    public void remplirDebut(){
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 75; j++) {
                if (env.getTerrain()[i][j]== 197){
                    Case caseDebut = new Case(j,i);
                    debut.add(caseDebut);
                }
            }
        }
    }



    public ArrayList<Case> getcaseDebut() {
        return debut;
    }


}