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
}