package fr.iut.montreuil.projetFinal.modele;

import java.util.ArrayList;
public class Bfs {

    private Environnement terrain;
    private int[][] BFS;
    private int plusPetit = 100000;
    private Case c;
    private int nbr;
    private int pionPoser = 0;
    private boolean entrainDeFaire = false;

    private ArrayList<Case> aFaire;
    private ArrayList<Integer> voisin = new ArrayList<>();
    ArrayList<Case> chemin;
    ArrayList<Case> vraiChemin;
    ArrayList<Case> debut;
    Case debutCase;
    private static int index;

    public Bfs(Environnement t) {
        index = (int)(Math.random()*10 );
        this.terrain = t;
        this.BFS = new int[terrain.getHeight()][terrain.getWidth()];
        debut = new ArrayList<>();
//        BFS[x][y] =0;
//        this.c = new Case(x,y);
        this.c= new Case(2,21);
        for (int i = 0; i < BFS.length; i++) {
            for (int j = 0; j < BFS[i].length; j++) {

                BFS[i][j] = -1; //code par défaut pour les tours
                if (terrain.getTerrain()[i][j]== 197){
                    debutCase = new Case(i,j);
                    debut.add(debutCase);
                }

                else if (terrain.getTerrain()[i][j] == 53) {
                    BFS[i][j] = -5;
                }

                if (terrain.getTerrain()[i][j] == 197 ) {
                    if (3 == index) {
                        BFS[i][j] = 0;
                        this.c = new Case(i, j);
                    }
                    else {
                        index++;
                        System.out.println("index : " + index);
                        break;
                    }
                    break;
                }


            }
        }
        System.out.println("taille de debut : " + debut);
//        int dd = (int)(Math.random()*debut.size() );
//        BFS[debut.get(dd).getX()][debut.get(dd).getY()] = 0;
//        this.c = new Case(debut.get(dd).getX(), debut.get(dd).getY());
//        System.out.println(dd);
//        System.out.println("x:" + debut.get(dd).getX() + " y : " + debut.get(dd).getY());

        this.aFaire = new ArrayList<>();

        remplirGrilleBFS();
        //voirTab();
    }

    public void setC(Case c) {
        this.c = c;
    }

    public void remplirGrilleBFS() {
        voisin(terrain, c,0);
        chemin();
    }


    public void voisin(Environnement t, Case c, int n) {
        voisin.add(5);
        nbr = n;

        while (!voisin.isEmpty()) {
            nbr++;
            voisin.clear();

            //Pour les voisin en bas
            if (terrain.dansLeTerrain(c.getY() + 1, c.getX()) && terrain.getTerrain()[c.getY() + 1][c.getX()] == 213  ) {
                if (BFS[c.getY() + 1][c.getX()] != -1 && BFS[c.getY() + 1][c.getX()] -1 > BFS[c.getY()][c.getX()] ){
                    BFS[c.getY() + 1][c.getX()] = nbr;
                    voisin.add(1);
                }
                else if (BFS[c.getY() + 1][c.getX()] == -1){
                    BFS[c.getY() + 1][c.getX()] = nbr;
                    voisin.add(1);
                }
            }

            //Pour les voisin en haut
            if (terrain.dansLeTerrain(c.getY() - 1, c.getX()) && terrain.getTerrain()[c.getY() - 1][c.getX()] == 213) {
                if (BFS[c.getY() - 1][c.getX()] != -1 && BFS[c.getY() - 1][c.getX()] -1 > BFS[c.getY()][c.getX()] ){
                    BFS[c.getY() - 1][c.getX()] = nbr;
                    voisin.add(2);
                }
                else if (BFS[c.getY() - 1][c.getX()] == -1){
                    BFS[c.getY() - 1][c.getX()] = nbr;
                    voisin.add(2);
                }
            }

            //Pour les voisin à droite
            if (terrain.dansLeTerrain(c.getY(), c.getX() + 1) && terrain.getTerrain()[c.getY()][c.getX() + 1] == 213 ) {
                if (BFS[c.getY()][c.getX() + 1] != -1 && BFS[c.getY()][c.getX() + 1] -1 > BFS[c.getY()][c.getX()] ){
                    BFS[c.getY()][c.getX() + 1] = nbr;
                    voisin.add(3);
                }
                else if (BFS[c.getY()][c.getX() + 1] == -1){
                    BFS[c.getY()][c.getX() + 1] = nbr;
                    voisin.add(3);
                }
            }

            //Pour les voisin à gauche
            if (terrain.dansLeTerrain(c.getY(), c.getX() - 1) && terrain.getTerrain()[c.getY()][c.getX() - 1] == 213 ) {
                if (BFS[c.getY()][c.getX() - 1] != -1 && BFS[c.getY()][c.getX() - 1] -1 > BFS[c.getY()][c.getX()] ){
                    BFS[c.getY()][c.getX() - 1] = nbr;
                    voisin.add(4);
                }
                else if (BFS[c.getY()][c.getX() - 1] == -1){
                    BFS[c.getY()][c.getX() - 1] = nbr;
                    voisin.add(4);
                }
            }

            pionPoser = 0;
            for (int p : voisin) {
                if (p == 1) {
                    c.setY(c.getY() + 1);
                    pionPoser++;
                }
                if (p == 2) {
                    c.setY(c.getY() - 1);
                    pionPoser++;
                }
                if (p == 3) {
                    c.setX(c.getX() + 1);
                    pionPoser++;
                }
                if (p == 4) {
                    c.setX(c.getX() - 1);
                    pionPoser++;
                }

                if (pionPoser >= 2) {
                    vérifie();
                    if (!aFaire.isEmpty() && entrainDeFaire !=true ){
                        while (!aFaire.isEmpty()){
                            c.setY(aFaire.get(0).getY());
                            c.setX(aFaire.get(0).getX());
                            entrainDeFaire = true;
                            voisin(terrain,c,BFS[aFaire.get(0).getY()][aFaire.get(0).getX()]);
                            entrainDeFaire = false;
                            aFaire.remove(0);
                        }
                    }
                    break;
                }
            }
        }
    }

    public ArrayList<Case> vérifie() {
        for (int i = 0; i < BFS.length; i++) {
            for (int j = 0; j < BFS[i].length; j++) {
                if (BFS[i][j] == nbr) {
                    Case faireCase = new Case(i,j);
                    c.setY(i);
                    c.setX(j);
                    aFaire.add(faireCase);
                }
            }
        }
        return aFaire;
    }

    public void voirTab() {
        for (int i = 0; i < BFS.length; i++) {
            for (int j = 0; j < BFS[i].length; j++) {
                System.out.print("| " + BFS[i][j] + "| ");
            }
            System.out.println(" ");
        }
    }

    public ArrayList<Case> chemin() {
        chemin = new ArrayList<>();
        vraiChemin = new ArrayList<>();
        Case cible = new Case(0,0);

        for (int i = 0; i < BFS.length; i++) {
            for (int j = 0; j < BFS[i].length; j++) {
                if (BFS[i][j] == -5){
                    cible = new Case(i,j);
                    chemin.add(cible);
                }
            }
        }

        cible = plusPetit(cible);
        chemin.add(cible);

        while (plusPetit != 1 ) {
            //Pour les voisin en bas
            if (terrain.dansLeTerrain(cible.getY() + 1, cible.getX()) && BFS[cible.getY() + 1][cible.getX()] < plusPetit && BFS[cible.getY() + 1][cible.getX()] > 0) {
                plusPetit = BFS[cible.getY() + 1][cible.getX()];
                cible = new Case(cible.getY() + 1, cible.getX());
                chemin.add(cible);
            }

            //Pour les voisin en haut
            if (terrain.dansLeTerrain(cible.getY() - 1, cible.getX()) && BFS[cible.getY() - 1][cible.getX()] < plusPetit && BFS[cible.getY() - 1][cible.getX()] > 0) {
                plusPetit = BFS[cible.getY() - 1][cible.getX()];
                cible = new Case(cible.getY() - 1, cible.getX());
                chemin.add(cible);
            }

            //Pour les voisin à droite
            if (terrain.dansLeTerrain(cible.getY(), cible.getX() + 1) && BFS[cible.getY()][cible.getX() + 1] < plusPetit && BFS[cible.getY()][cible.getX() + 1] > 0) {
                plusPetit = BFS[cible.getY()][cible.getX() + 1];
                cible = new Case(cible.getY(), cible.getX() + 1);
                chemin.add(cible);
            }

            //Pour les voisin à gauche
            if (terrain.dansLeTerrain(cible.getY(), cible.getX() - 1) && BFS[cible.getY()][cible.getX() - 1] < plusPetit && BFS[cible.getY()][cible.getX() - 1] > 0) {
                plusPetit = BFS[cible.getY()][cible.getX() - 1];
                cible = new Case(cible.getY(), cible.getX() - 1);
                chemin.add(cible);
            }

        }

        for (int i =chemin.size()-1; i>=0;i--){
            vraiChemin.add(chemin.get(i));
        }
        //System.out.println("le chemin : " + vraiChemin);
        return vraiChemin;
    }

    public Case plusPetit (Case cible) {
        //Pour les voisin en bas
        Case c = new Case(cible.getY(), cible.getX());
        if (terrain.dansLeTerrain(cible.getY() + 1, cible.getX()) && BFS[cible.getY() + 1][cible.getX()] < plusPetit && BFS[cible.getY() + 1][cible.getX()] > 0) {
            if (plusPetit > BFS[cible.getY() + 1][cible.getX()] && BFS[cible.getY() + 1][cible.getX()] > 0){
                plusPetit = BFS[cible.getY() + 1][cible.getX()];
                c.setY(cible.getY() + 1);
                c.setX(cible.getX());
            }
        }

        //Pour les voisin en haut
        if (terrain.dansLeTerrain(cible.getY() - 1, cible.getX()) && BFS[cible.getY() - 1][cible.getX()] < plusPetit && BFS[cible.getY() - 1][cible.getX()] > 0) {
            if (plusPetit > BFS[cible.getY() - 1][cible.getX()] && BFS[cible.getY() - 1][cible.getX()] > 0){
                plusPetit = BFS[cible.getY() - 1][cible.getX()];
                c.setY(cible.getY() - 1);
                c.setX(cible.getX());
            }
        }

        //Pour les voisin à droite
        if (terrain.dansLeTerrain(cible.getY(), cible.getX() + 1) && BFS[cible.getY()][cible.getX() + 1] < plusPetit && BFS[cible.getY()][cible.getX() + 1] > 0) {
            if (plusPetit > BFS[cible.getY()][cible.getX() + 1] && BFS[cible.getY()][cible.getX() + 1] > 0){
                plusPetit = BFS[cible.getY()][cible.getX() + 1];
                c.setY(cible.getY());
                c.setX(cible.getX() + 1);
            }
        }

        //Pour les voisin à gauche
        if (terrain.dansLeTerrain(cible.getY(), cible.getX() - 1) && BFS[cible.getY()][cible.getX() - 1] < plusPetit && BFS[cible.getY()][cible.getX() - 1] > 0) {
            if (plusPetit > BFS[cible.getY()][cible.getX() - 1] && BFS[cible.getY()][cible.getX() - 1] > 0){
                plusPetit = BFS[cible.getY()][cible.getX() - 1];
                c.setY(cible.getY());
                c.setX(cible.getX() - 1);
            }

        }

        cible = c;
        return cible;
    }

    public ArrayList<Case> getDebut() {
        return debut;
    }
}

