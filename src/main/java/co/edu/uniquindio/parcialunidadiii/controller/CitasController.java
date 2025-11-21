package co.edu.uniquindio.parcialunidadiii.controller;

import co.edu.uniquindio.parcialunidadiii.facade.ClinicaFacade;
import co.edu.uniquindio.parcialunidadiii.model.Cita;
import co.edu.uniquindio.parcialunidadiii.model.Consultorio;
import co.edu.uniquindio.parcialunidadiii.model.Paciente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CitasController {

    private ClinicaFacade facade = ClinicaFacade.getInstance();

    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, String> colPaciente, colMedico, colFecha, colHora;

    @FXML
    private TableColumn<Cita, Double> colPrecio;

    @FXML
    public void initialize() {

        colPaciente.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getPaciente().getNombre())
        );

        colMedico.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getConsultorio().getMedico().getNombre())
        );

        colFecha.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("fecha"));
        colHora.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("hora"));
        colPrecio.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("precio"));

        cargarDatos();
    }

    private void cargarDatos() {
        tablaCitas.setItems(
                FXCollections.observableArrayList(facade.obtenerCitasFacade())
        );
    }

    @FXML
    public void crearCita() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/parcialidadiii/views/modal_cita.fxml"));
            Parent root = loader.load();
            Stage st = new Stage();
            st.initOwner(tablaCitas.getScene().getWindow());
            st.setScene(new Scene(root));
            st.setTitle("Crear Cita");
            st.showAndWait();
            cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void eliminarCita() {
        Cita sel = tablaCitas.getSelectionModel().getSelectedItem();
        if (sel == null) {
            showAlert(Alert.AlertType.INFORMATION, "Seleccione", "Seleccione una cita para eliminar.");
            return;
        }
        Alert confirma = new Alert(Alert.AlertType.CONFIRMATION, "¿Eliminar cita seleccionada?", ButtonType.YES, ButtonType.NO);
        confirma.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.YES) {
                facade.eliminarCitaFacade(sel);
                cargarDatos();
            }
        });
    }
    private void showAlert(Alert.AlertType t, String title, String msg) {
        Alert a = new Alert(t);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

}
