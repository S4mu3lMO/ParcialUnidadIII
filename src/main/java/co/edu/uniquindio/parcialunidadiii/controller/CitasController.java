package co.edu.uniquindio.parcialunidadiii.controller;

import co.edu.uniquindio.parcialunidadiii.facade.ClinicaFacade;
import co.edu.uniquindio.parcialunidadiii.model.Cita;
import co.edu.uniquindio.parcialunidadiii.model.Consultorio;
import co.edu.uniquindio.parcialunidadiii.model.Medico;
import co.edu.uniquindio.parcialunidadiii.model.Paciente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CitasController {

    private ClinicaFacade facade = ClinicaFacade.getInstance();

    @FXML private TableView<Cita> tablaCitas;
    @FXML private TableColumn<Cita, String> colPaciente, colMedico, colFecha, colHora;
    @FXML private TableColumn<Cita, Double> colPrecio;

    @FXML
    public void initialize() {
        colPaciente.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getPaciente().getNombre()));
        colMedico.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getConsultorio().getMedico().getNombre()));
        colFecha.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getFecha().toString()));
        colHora.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getHora().toLocalTime().toString()));
        colPrecio.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("precio"));

        cargarDatos();
    }

    private void cargarDatos() {
        tablaCitas.setItems(FXCollections.observableArrayList(facade.obtenerCitasFacade()));
    }

    @FXML
    public void crearCita() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/parcialunidadiii/views/modal_cita.fxml"));
            Parent root = loader.load();

            Stage st = new Stage();
            st.initOwner(tablaCitas.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
            st.setTitle("Crear Cita");
            st.setScene(new Scene(root));
            st.showAndWait();

            cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error", "No se pudo abrir el formulario de cita.");
        }
    }

    @FXML
    public void eliminarCita() {
        Cita sel = tablaCitas.getSelectionModel().getSelectedItem();
        if (sel == null) {
            mostrarInfo("Seleccionar", "Selecciona una cita para eliminar.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Eliminar cita?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(b -> {
            if (b == ButtonType.YES) {
                facade.eliminarCitaFacade(sel);
                cargarDatos();
            }
        });
    }


    private void mostrarInfo(String t, String m) { Alert a = new Alert(Alert.AlertType.INFORMATION); a.setTitle(t); a.setHeaderText(null); a.setContentText(m); a.showAndWait(); }
    private void mostrarError(String t, String m) { Alert a = new Alert(Alert.AlertType.ERROR); a.setTitle(t); a.setHeaderText(null); a.setContentText(m); a.showAndWait(); }
}
