package co.edu.uniquindio.parcialunidadiii.controller;

import co.edu.uniquindio.parcialunidadiii.factory.PacienteFactory;
import co.edu.uniquindio.parcialunidadiii.facade.ClinicaFacade;
import co.edu.uniquindio.parcialunidadiii.model.Enfermedad;
import co.edu.uniquindio.parcialunidadiii.model.Paciente;
import co.edu.uniquindio.parcialunidadiii.model.TipoEnfermedad;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Arrays;

public class ModalPacienteController {

    @FXML private ComboBox<String> cmbTipo;
    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtEnfermedad;
    @FXML private ComboBox<TipoEnfermedad> cmbGravedad;

    private Paciente pacienteEditar = null;
    private ClinicaFacade facade = ClinicaFacade.getInstance();

    @FXML
    public void initialize() {
        // Asume tipos existentes en la factory: INFANTE / ADULTOMAYOR
        cmbTipo.getItems().addAll("INFANTE", "ADULTOMAYOR");
        cmbGravedad.getItems().setAll(Arrays.asList(TipoEnfermedad.values()));
    }

    public void setPacienteParaEditar(Paciente p) {
        this.pacienteEditar = p;
        if (p != null) {
            txtId.setText(p.getId());
            txtNombre.setText(p.getNombre());
            if (p.getEnfermedad() != null) {
                txtEnfermedad.setText(p.getEnfermedad().getNombreEnfermedad());
                cmbGravedad.setValue(p.getEnfermedad().getGravedadEnfermedad());
            }
            // No sabemos el tipo concreto (Infante/AdultoMayor), deja el combo para que usuario seleccione si quiere.
        }
    }

    @FXML
    public void onGuardar() {
        String tipo = cmbTipo.getValue();
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String enfNombre = txtEnfermedad.getText().trim();
        TipoEnfermedad gravedad = cmbGravedad.getValue();

        if (tipo == null || id.isEmpty() || nombre.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Datos incompletos", "Debe completar tipo, id y nombre.");
            return;
        }

        Enfermedad enf = null;
        if (!enfNombre.isEmpty() && gravedad != null) {
            enf = new Enfermedad(enfNombre, gravedad);
        }

        try {
            Paciente p = new PacienteFactory().createPaciente(tipo, nombre, id);
            // assign enfermedad via builder pattern? many concrete Paciente may not have setter - try reflection or assume setter exists.
            // If no setter, skip; but most models had enfermedad field with setter earlier — if missing adjust.
            // Try to set via reflection if no setter:
            try {
                p.getClass().getMethod("setEnfermedad", Enfermedad.class).invoke(p, enf);
            } catch (NoSuchMethodException ex) {
                // ignore if no setter
            }

            if (pacienteEditar != null) {
                // actualizar: eliminar viejo e insertar nuevo (facade tiene actualizarPaciente)
                facade.actualizarPacienteFacade(pacienteEditar.getId(), p);
            } else {
                facade.registrarPacienteFacade(p);
            }

            closeWindow();
        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo guardar el paciente: " + ex.getMessage());
        }
    }

    @FXML
    public void onCancelar() {
        closeWindow();
    }

    private void closeWindow() {
        Stage st = (Stage) txtNombre.getScene().getWindow();
        st.close();
    }

    private void showAlert(Alert.AlertType t, String title, String msg) {
        Alert a = new Alert(t);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
