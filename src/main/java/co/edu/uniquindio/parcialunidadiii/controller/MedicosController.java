package co.edu.uniquindio.parcialunidadiii.controller;

import co.edu.uniquindio.parcialunidadiii.facade.ClinicaFacade;
import co.edu.uniquindio.parcialunidadiii.model.Medico;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MedicosController {

    private ClinicaFacade facade = ClinicaFacade.getInstance();

    @FXML private TableView<Medico> tablaMedicos;
    @FXML private TableColumn<Medico, String> colId, colNombre, colEspecialidad;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idMedico"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEspecialidad.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getEspecialidad() != null
                                ? cell.getValue().getEspecialidad().name()
                                : ""
                )
        );

        cargarDatos();
    }

    private void cargarDatos() {
        tablaMedicos.setItems(FXCollections.observableArrayList(facade.obtenerMedicosFacade()));
    }

    @FXML
    public void agregarMedico() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/parcialunidadiii/views/modal_medico.fxml"));
            Parent root = loader.load();

            Stage st = new Stage();
            st.initOwner(tablaMedicos.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
            st.setTitle("Crear Médico");
            st.setScene(new Scene(root));
            st.showAndWait();

            cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error", "No se pudo abrir el formulario de médico.");
        }
    }

    @FXML
    public void editarMedico() {
        Medico seleccionado = tablaMedicos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarInfo("Seleccione médico", "Seleccione un médico para editar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/parcialunidadiii/views/modal_medico.fxml"));
            Parent root = loader.load();

            ModalMedicoController ctrl = loader.getController();
            ctrl.setMedicoParaEditar(seleccionado);

            Stage st = new Stage();
            st.initOwner(tablaMedicos.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
            st.setTitle("Editar Médico");
            st.setScene(new Scene(root));
            st.showAndWait();

            cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error", "No se pudo abrir el formulario de edición.");
        }
    }

    @FXML
    public void eliminarMedico() {
        Medico seleccionado = tablaMedicos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarInfo("Seleccione médico", "Seleccione un médico para eliminar.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Eliminar médico " + seleccionado.getNombre() + "?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(b -> {
            if (b == ButtonType.YES) {
                facade.eliminarMedicoFacade(seleccionado.getIdMedico());
                cargarDatos();
            }
        });
    }



    private void mostrarInfo(String t, String m) {
        Alert a = new Alert(Alert.AlertType.INFORMATION); a.setTitle(t); a.setHeaderText(null); a.setContentText(m); a.showAndWait();
    }

    private void mostrarError(String t, String m) {
        Alert a = new Alert(Alert.AlertType.ERROR); a.setTitle(t); a.setHeaderText(null); a.setContentText(m); a.showAndWait();
    }
}
