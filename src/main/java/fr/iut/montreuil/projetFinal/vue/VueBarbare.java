package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import javafx.scene.layout.Pane;

public class VueBarbare extends VueEnnemi{
    public VueBarbare(Pane pane, Ennemi e) {
        super(pane, e, "barbareV.jpeg");
    }
}
