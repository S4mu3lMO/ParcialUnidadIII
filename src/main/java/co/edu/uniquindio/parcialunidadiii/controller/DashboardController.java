package co.edu.uniquindio.parcialunidadiii.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class DashboardController {

    public StackPane contentPane;

    private void cargarVista(String ruta) {
        try {
            Node vista = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/parcialunidadiii/views/" + ruta));
            contentPane.getChildren().setAll(vista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirPacientes() {
        cargarVista("pacientes.fxml");
    }

    public void abrirMedicos() {
        cargarVista("medicos.fxml");
    }

    public void abrirCitas() {
        cargarVista("citas.fxml");
    }
}
