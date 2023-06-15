package fr.iut.montreuil.projetFinal.vue;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import javafx.scene.layout.Pane;

public class VueGéant extends VueEnnemi{
    public VueGéant(Pane pane, Ennemi e) {
        super(pane, e, "géant.png");
    }
}
