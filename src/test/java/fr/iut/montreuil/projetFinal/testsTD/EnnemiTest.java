package fr.iut.montreuil.projetFinal.testsTD;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import fr.iut.montreuil.projetFinal.modele.Hdv;
import fr.iut.montreuil.projetFinal.modele.Vague;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnnemiTest {
    private Ennemi ennemi;
    private Environnement environnement;
    private Hdv hdv;
    private Vague vague;


     @Test
    public void testRecoitDegat() {
         environnement = new Environnement(75,50);
         hdv = new Hdv(environnement);
         vague = new Vague(environnement);

         Ennemi e = new Ennemi(0, 0, environnement, 100, 2, 10, hdv, vague, 5.0);

        e.recoitDegat(20);

        assertEquals(80, e.getPvProperty());


    }
}
