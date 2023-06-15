package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import javafx.scene.layout.Pane;

public class VueGeant extends VueEnnemi{
    public VueGeant(Pane pane, Ennemi e) {
        super(pane, e, "géant.png");
    }
}
