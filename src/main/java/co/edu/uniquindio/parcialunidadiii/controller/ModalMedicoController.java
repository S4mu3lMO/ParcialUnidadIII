package co.edu.uniquindio.parcialunidadiii.controller;

import co.edu.uniquindio.parcialunidadiii.facade.ClinicaFacade;
import co.edu.uniquindio.parcialunidadiii.model.Medico;
import co.edu.uniquindio.parcialunidadiii.model.TipoEspecialidad;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ModalMedicoController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private ComboBox<TipoEspecialidad> cmbEspecialidad;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;

    private ClinicaFacade facade = ClinicaFacade.getInstance();
    private Medico medicoEditar = null;

    @FXML
    public void initialize() {
        cmbEspecialidad.getItems().setAll(TipoEspecialidad.values());
    }

    public void setMedicoParaEditar(Medico m) {
        this.medicoEditar = m;
        if (m == null) return;
        txtId.setText(m.getIdMedico());
        txtNombre.setText(m.getNombre());
        cmbEspecialidad.setValue(m.getEspecialidad());
    }

    @FXML
    public void onGuardar() {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        TipoEspecialidad esp = cmbEspecialidad.getValue();

        if (id.isEmpty() || nombre.isEmpty() || esp == null) {
            mostrarError("Datos incompletos", "Complete todos los campos.");
            return;
        }

        Medico m = new Medico.Builder()
                .setIdMedico(id)
                .setNombre(nombre)
                .setEspecialidad(esp)
                .build();

        if (medicoEditar != null) {
            facade.actualizarMedicoFacade(medicoEditar.getIdMedico(), m);
        } else {
            facade.registrarMedicoFacade(m);
        }

        closeWindow();
    }

    @FXML public void onCancelar() { closeWindow(); }

    private void closeWindow() {
        Stage st = (Stage) btnCancelar.getScene().getWindow();
        st.close();
    }

    private void mostrarError(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR); a.setTitle(title); a.setHeaderText(null); a.setContentText(msg); a.showAndWait();
    }
}
