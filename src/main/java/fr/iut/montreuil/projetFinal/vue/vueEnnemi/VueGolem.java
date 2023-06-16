package fr.iut.montreuil.projetFinal.vue.vueEnnemi;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import javafx.scene.layout.Pane;

public class VueGolem extends VueEnnemi {
    public VueGolem(Pane pane, Ennemi e) {
        super(pane, e, "golem.png");
    }
}
