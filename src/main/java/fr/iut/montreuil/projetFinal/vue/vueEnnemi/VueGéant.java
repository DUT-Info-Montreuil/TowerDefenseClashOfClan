package fr.iut.montreuil.projetFinal.vue.vueEnnemi;

import fr.iut.montreuil.projetFinal.modele.Ennemi;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueGéant extends VueEnnemi {
    private ImageView imageView;
    public VueGéant(Pane pane, Ennemi e) {
        super(pane, e, "géant.png");

    }
}
