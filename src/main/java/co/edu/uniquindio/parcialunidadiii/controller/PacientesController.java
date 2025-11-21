package co.edu.uniquindio.parcialunidadiii.controller;

import co.edu.uniquindio.parcialunidadiii.facade.ClinicaFacade;
import co.edu.uniquindio.parcialunidadiii.model.Paciente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PacientesController {

    private ClinicaFacade facade = ClinicaFacade.getInstance();

    @FXML
    private TableView<Paciente> tablaPacientes;

    @FXML
    private TableColumn<Paciente, String> colId, colNombre, colEnfermedad;

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nombre"));
        colEnfermedad.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("enfermedad"));

        cargarDatos();
    }

    private void cargarDatos() {
        tablaPacientes.setItems(
                FXCollections.observableArrayList(facade.obtenerPacientesFacade())
        );
    }

    @FXML
    public void agregarPaciente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/parcialidadiii/views/modal_paciente.fxml"));
            Parent root = loader.load();
            Stage st = new Stage();
            st.initOwner(tablaPacientes.getScene().getWindow());
            st.setScene(new Scene(root));
            st.setTitle("Crear Paciente");
            st.showAndWait();
            cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editarPaciente() {
        Paciente sel = tablaPacientes.getSelectionModel().getSelectedItem();
        if (sel == null) {
            showAlert(Alert.AlertType.INFORMATION, "Seleccione", "Seleccione un paciente para editar.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/parcialidadiii/views/modal_paciente.fxml"));
            Parent root = loader.load();
            ModalPacienteController ctrl = loader.getController();
            ctrl.setPacienteParaEditar(sel);
            Stage st = new Stage();
            st.initOwner(tablaPacientes.getScene().getWindow());
            st.setScene(new Scene(root));
            st.setTitle("Editar Paciente");
            st.showAndWait();
            cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void eliminarPaciente() {
        Paciente sel = tablaPacientes.getSelectionModel().getSelectedItem();
        if (sel == null) {
            showAlert(Alert.AlertType.INFORMATION, "Seleccione", "Seleccione un paciente para eliminar.");
            return;
        }
        Alert confirma = new Alert(Alert.AlertType.CONFIRMATION, "¿Eliminar paciente seleccionado?", ButtonType.YES, ButtonType.NO);
        confirma.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.YES) {
                facade.eliminarPacienteFacade(sel.getId());
                cargarDatos();
            }
        });
    }

    public void showAlert(Alert.AlertType t, String title, String msg) {
        Alert a = new Alert(t);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

}
