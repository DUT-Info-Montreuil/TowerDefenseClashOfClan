package fr.iut.montreuil.projetFinal.controleur;

import fr.iut.montreuil.projetFinal.modele.*;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ListObsTour implements ListChangeListener<Tour> {

    private Environnement env;
    private Pane pane;

    public ListObsTour(Pane pane, Environnement env){
        this.pane = pane;
        this.env = env;
    }

    @Override
    public void onChanged(Change<? extends Tour> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (int i = 0; i < change.getAddedSubList().size(); i++) {
                    Tour tour = (Tour) change.getAddedSubList().get(i);
                    creerTour(tour);
                }
            }
            if (change.wasRemoved()) {
                for (int i = change.getRemoved().size() - 1; i >= 0; i--) {
                    Tour tour = (Tour) change.getRemoved().get(i);
                    //Node n = pane.lookup("#" + tour.getId());
                    //pane.getChildren().remove(n);
                }
            }
        }

    }

    void creerTour(Tour tour){
        Rectangle t = new Rectangle(50, 50);
        t.setFill(Color.BURLYWOOD);
        t.setTranslateX(tour.getX() - 25);
        t.setTranslateY(tour.getY() -25);
        pane.getChildren().add(t);
    }

    public void placerTour(MouseEvent event) {
        TourArchers tour = new TourArchers(event.getX(), event.getY(), env);
        env.ajouterTour(tour);
        creerTour(tour);
    }
}
