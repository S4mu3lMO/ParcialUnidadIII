package co.edu.uniquindio.parcialunidadiii.controller;

import co.edu.uniquindio.parcialunidadiii.facade.ClinicaFacade;
import co.edu.uniquindio.parcialunidadiii.model.Medico;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MedicosController {

    private ClinicaFacade facade = ClinicaFacade.getInstance();

    @FXML
    private TableView<Medico> tablaMedicos;

    @FXML
    private TableColumn<Medico, String> colId, colNombre, colEspecialidad;

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("idMedico"));
        colNombre.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nombre"));
        colEspecialidad.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("especialidad"));

        cargarDatos();
    }

    private void cargarDatos() {
        tablaMedicos.setItems(
                FXCollections.observableArrayList(facade.obtenerMedicosFacade())
        );
    }

    @FXML
    public void agregarMedico() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/parcialidadiii/views/modal_medico.fxml"));
            Parent root = loader.load();
            Stage st = new Stage();
            st.initOwner(tablaMedicos.getScene().getWindow());
            st.setScene(new Scene(root));
            st.setTitle("Crear Médico");
            st.showAndWait();
            cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editarMedico() {
        Medico sel = tablaMedicos.getSelectionModel().getSelectedItem();
        if (sel == null) {
            showAlert(Alert.AlertType.INFORMATION, "Seleccione", "Seleccione un médico para editar.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/parcialidadiii/views/modal_medico.fxml"));
            Parent root = loader.load();
            ModalMedicoController ctrl = loader.getController();
            ctrl.setMedicoParaEditar(sel);
            Stage st = new Stage();
            st.initOwner(tablaMedicos.getScene().getWindow());
            st.setScene(new Scene(root));
            st.setTitle("Editar Médico");
            st.showAndWait();
            cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void eliminarMedico() {
        Medico sel = tablaMedicos.getSelectionModel().getSelectedItem();
        if (sel == null) {
            showAlert(Alert.AlertType.INFORMATION, "Seleccione", "Seleccione un médico para eliminar.");
            return;
        }
        Alert confirma = new Alert(Alert.AlertType.CONFIRMATION, "¿Eliminar médico seleccionado?", ButtonType.YES, ButtonType.NO);
        confirma.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.YES) {
                facade.eliminarMedicoFacade(sel.getIdMedico());
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
