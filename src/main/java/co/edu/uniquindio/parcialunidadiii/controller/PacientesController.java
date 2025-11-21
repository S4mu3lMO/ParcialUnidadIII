package co.edu.uniquindio.parcialunidadiii.controller;

import co.edu.uniquindio.parcialunidadiii.facade.ClinicaFacade;
import co.edu.uniquindio.parcialunidadiii.model.AdultoMayor;
import co.edu.uniquindio.parcialunidadiii.model.Infante;
import co.edu.uniquindio.parcialunidadiii.model.Paciente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PacientesController {

    private ClinicaFacade facade = ClinicaFacade.getInstance();

    @FXML private TableView<Paciente> tablaPacientes;
    @FXML private TableColumn<Paciente, String> colId, colNombre, colTipo, colEnfermedad, colGravedad, colJubilado;
    @FXML private Label lblTotal;

    @FXML
    public void initialize() {
        // Configurar columnas de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));


        colTipo.setCellValueFactory(cell -> {
            Paciente p = cell.getValue();
            String tipo = "";
            if (p instanceof Infante) {
                tipo = "INFANTE";
            } else if (p instanceof AdultoMayor) {
                tipo = "ADULTO MAYOR";
            }
            return new javafx.beans.property.SimpleStringProperty(tipo);
        });


        colEnfermedad.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getEnfermedad() != null
                                ? cell.getValue().getEnfermedad().getNombreEnfermedad()
                                : "N/A"
                )
        );


        colGravedad.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getEnfermedad() != null
                                ? cell.getValue().getEnfermedad().getGravedadEnfermedad().name()
                                : "N/A"
                )
        );

        // Columna Jubilado - solo para Adulto Mayor
        colJubilado.setCellValueFactory(cell -> {
            Paciente p = cell.getValue();
            String jubilado = "N/A";
            if (p instanceof AdultoMayor) {

                try {
                    java.lang.reflect.Field field = AdultoMayor.class.getDeclaredField("jubilacion");
                    field.setAccessible(true);
                    boolean esJubilado = (boolean) field.get(p);
                    jubilado = esJubilado ? "SÍ" : "NO";
                } catch (Exception e) {
                    jubilado = "N/A";
                }
            }
            return new javafx.beans.property.SimpleStringProperty(jubilado);
        });

        cargarDatos();
    }

    private void cargarDatos() {
        tablaPacientes.setItems(FXCollections.observableArrayList(facade.obtenerPacientesFacade()));


        if (lblTotal != null) {
            lblTotal.setText(String.valueOf(tablaPacientes.getItems().size()));
        }
    }

    @FXML
    public void agregarPaciente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/parcialunidadiii/views/modal_paciente.fxml"));
            Parent root = loader.load();

            Stage st = new Stage();
            st.initOwner(tablaPacientes.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
            st.setTitle("Crear Paciente");
            st.setScene(new Scene(root));
            st.showAndWait();

            cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error", "No se pudo abrir el formulario de paciente.");
        }
    }

    @FXML
    public void editarPaciente() {
        Paciente seleccionado = tablaPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarInfo("Seleccione paciente", "Seleccione un paciente para editar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/parcialunidadiii/views/modal_paciente.fxml"));
            Parent root = loader.load();


            ModalPacienteController ctrl = loader.getController();
            ctrl.setPacienteParaEditar(seleccionado);

            Stage st = new Stage();
            st.initOwner(tablaPacientes.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
            st.setTitle("Editar Paciente");
            st.setScene(new Scene(root));
            st.showAndWait();

            cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error", "No se pudo abrir el formulario de edición.");
        }
    }

    @FXML
    public void eliminarPaciente() {
        Paciente seleccionado = tablaPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarInfo("Seleccione paciente", "Seleccione un paciente para eliminar.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Está seguro de eliminar al paciente " + seleccionado.getNombre() + "?",
                ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirmar eliminación");
        confirm.setHeaderText(null);

        confirm.showAndWait().ifPresent(b -> {
            if (b == ButtonType.YES) {
                try {
                    facade.eliminarPacienteFacade(seleccionado.getId());
                    cargarDatos();
                    mostrarInfo("Éxito", "Paciente eliminado correctamente.");
                } catch (Exception e) {
                    e.printStackTrace();
                    mostrarError("Error", "No se pudo eliminar el paciente: " + e.getMessage());
                }
            }
        });
    }



    private void mostrarInfo(String titulo, String mensaje) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }
}