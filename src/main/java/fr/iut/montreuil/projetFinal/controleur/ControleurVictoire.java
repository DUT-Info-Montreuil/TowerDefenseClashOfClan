package fr.iut.montreuil.projetFinal.controleur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ControleurVictoire {
    @FXML
    private void retourMenu2(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/fr/iut/montreuil/projetFinal/vue.fxml");
        Parent root = fxmlLoader.load(resource);
        Scene scene = new Scene(root, 1550, 850);
        primaryStage.setResizable(false);
        primaryStage.setTitle("coc victoire");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @FXML
    public void quitterApplication2(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}
