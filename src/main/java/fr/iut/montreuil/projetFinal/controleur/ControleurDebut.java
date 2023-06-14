package fr.iut.montreuil.projetFinal.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleurDebut {
    @FXML
    private Pane pane;
    @FXML
    private void lancerLeJeux(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/fr/iut/montreuil/projetFinal/vue.fxml");
        Parent root = fxmlLoader.load(resource);
        Scene scene = new Scene(root, 1550, 850);
        primaryStage.setResizable(false);
        primaryStage.setTitle("coc Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
