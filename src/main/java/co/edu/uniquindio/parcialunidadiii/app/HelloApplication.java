package co.edu.uniquindio.parcialunidadiii.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/co/edu/uniquindio/parcialunidadiii/views/dashboard.fxml"
        ));

        Scene scene = new Scene(loader.load(), 900, 600);


        stage.setScene(scene);
        stage.setTitle("Sistema de Gestión Clínica UQ");
        stage.setMinWidth(800);
        stage.setMinHeight(500);



        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}