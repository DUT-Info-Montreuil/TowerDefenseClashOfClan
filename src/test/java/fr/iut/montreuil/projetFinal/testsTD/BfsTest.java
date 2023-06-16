package fr.iut.montreuil.projetFinal.testsTD;

import fr.iut.montreuil.projetFinal.modele.Bfs;
import fr.iut.montreuil.projetFinal.modele.Environnement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BfsTest {
   @Test
    public void testTailleTableauBFS() {
        Environnement environnement = new Environnement(75, 50);
        Bfs bfs = new Bfs(environnement, 21, 2);
        assertEquals(50, bfs.getBFS().length);
        assertEquals(75, bfs.getBFS()[0].length);
    }
    @Test
    public void testChemin() {
        Environnement terrain = new Environnement(75,50);
        Bfs bfs = new Bfs(terrain, 21, 2);
       bfs.chemin();
        assertEquals(2, bfs.chemin().size());
    }

}
