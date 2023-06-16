module com.application.projetfinal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;


    exports fr.iut.montreuil.projetFinal;
    opens fr.iut.montreuil.projetFinal.modele;
    exports fr.iut.montreuil.projetFinal.modele;
    opens fr.iut.montreuil.projetFinal;
    exports fr.iut.montreuil.projetFinal.controleur;
    opens fr.iut.montreuil.projetFinal.controleur;
    exports fr.iut.montreuil.projetFinal.modele.ennemi;
    opens fr.iut.montreuil.projetFinal.modele.ennemi;
    exports fr.iut.montreuil.projetFinal.modele.tour;
    opens fr.iut.montreuil.projetFinal.modele.tour;
    exports fr.iut.montreuil.projetFinal.modele.projectile;
    opens fr.iut.montreuil.projetFinal.modele.projectile;
}