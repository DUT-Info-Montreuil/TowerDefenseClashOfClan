package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import javafx.scene.layout.Pane;

public class VueArcher extends VueEnnemi{
    public VueArcher(Pane pane, Ennemi e) {
        super(pane, e, "archerCoc.png");
    }
}
