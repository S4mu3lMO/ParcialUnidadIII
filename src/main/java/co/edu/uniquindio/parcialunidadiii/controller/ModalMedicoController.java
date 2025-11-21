package co.edu.uniquindio.parcialunidadiii.controller;

import co.edu.uniquindio.parcialunidadiii.facade.ClinicaFacade;
import co.edu.uniquindio.parcialunidadiii.model.Medico;
import co.edu.uniquindio.parcialunidadiii.model.TipoEspecialidad;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Arrays;

public class ModalMedicoController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private ComboBox<TipoEspecialidad> cmbEspecialidad;

    private Medico editar = null;
    private ClinicaFacade facade = ClinicaFacade.getInstance();

    @FXML
    public void initialize() {
        cmbEspecialidad.getItems().setAll(Arrays.asList(TipoEspecialidad.values()));
    }

    public void setMedicoParaEditar(Medico m) {
        this.editar = m;
        if (m != null) {
            txtId.setText(m.getIdMedico());
            txtNombre.setText(m.getNombre());
            cmbEspecialidad.setValue(m.getEspecialidad());
        }
    }

    @FXML
    public void onGuardar() {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        TipoEspecialidad esp = cmbEspecialidad.getValue();

        if (id.isEmpty() || nombre.isEmpty() || esp == null) {
            showAlert(Alert.AlertType.ERROR, "Datos incompletos", "Complete todos los campos.");
            return;
        }

        Medico m = new Medico.Builder()
                .setIdMedico(id)
                .setNombre(nombre)
                .setEspecialidad(esp)
                .build();

        if (editar != null) {
            facade.actualizarMedicoFacade(editar.getIdMedico(), m);
        } else {
            facade.registrarMedicoFacade(m);
        }

        closeWindow();
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
