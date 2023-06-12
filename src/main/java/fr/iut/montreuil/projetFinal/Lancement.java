package fr.iut.montreuil.projetFinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Lancement extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Lancement.class.getResource("vue.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1550, 850);
        stage.setTitle("Clash of Clans Tower Defense");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
