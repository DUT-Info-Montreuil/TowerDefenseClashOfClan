package fr.iut.montreuil.projetFinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Lancement extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Lancement.class.getResource("vue.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1550, 850);
        stage.setTitle("Coc");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
