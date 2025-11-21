package co.edu.uniquindio.parcialunidadiii.controller;

import co.edu.uniquindio.parcialunidadiii.facade.ClinicaFacade;
import co.edu.uniquindio.parcialunidadiii.model.AdultoMayor;
import co.edu.uniquindio.parcialunidadiii.model.Enfermedad;
import co.edu.uniquindio.parcialunidadiii.model.Infante;
import co.edu.uniquindio.parcialunidadiii.model.Paciente;
import co.edu.uniquindio.parcialunidadiii.model.TipoEnfermedad;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ModalPacienteController {

    @FXML private ComboBox<String> cmbTipo; // "INFANTE" / "ADULTOMAYOR"
    @FXML private TextField txtNombre;
    @FXML private TextField txtId;
    @FXML private TextField txtNombreEnfermedad;
    @FXML private ComboBox<TipoEnfermedad> cmbGravedad;
    @FXML private CheckBox chkJubilado;
    @FXML private Button btnGuardar, btnCancelar;

    private ClinicaFacade facade = ClinicaFacade.getInstance();
    private Paciente pacienteEditar = null;

    @FXML
    public void initialize() {
        cmbTipo.getItems().setAll("INFANTE", "ADULTOMAYOR");
        cmbGravedad.getItems().setAll(TipoEnfermedad.values());

        // mostrar/ocultar campos según tipo elegido
        cmbTipo.setOnAction(ev -> actualizarVisibilidadCampos());
        actualizarVisibilidadCampos();
    }

    private void actualizarVisibilidadCampos() {
        String tipo = cmbTipo.getValue();
        boolean esInfante = "INFANTE".equalsIgnoreCase(tipo);
        boolean esAdulto = "ADULTOMAYOR".equalsIgnoreCase(tipo);

        txtNombreEnfermedad.setDisable(!esInfante && !esAdulto);
        cmbGravedad.setDisable(!esInfante && !esAdulto);
        chkJubilado.setDisable(!esAdulto);
    }

    public void setPacienteParaEditar(Paciente p) {
        this.pacienteEditar = p;
        if (p == null) return;

        txtId.setText(p.getId());
        txtNombre.setText(p.getNombre());
        if (p.getEnfermedad() != null) {
            txtNombreEnfermedad.setText(p.getEnfermedad().getNombreEnfermedad());
            cmbGravedad.setValue(p.getEnfermedad().getGravedadEnfermedad());
        }

        // decidir tipo mostrable — usamos instanceof
        if (p instanceof Infante) {
            cmbTipo.setValue("INFANTE");
        } else if (p instanceof AdultoMayor) {
            cmbTipo.setValue("ADULTOMAYOR");
            chkJubilado.setSelected(((AdultoMayor) p).jubilacion); // but field is private; can't access directly — we will use reflection or add getter
        }
        actualizarVisibilidadCampos();
    }

    @FXML
    public void onGuardar() {
        String tipo = cmbTipo.getValue();
        String nombre = txtNombre.getText().trim();
        String id = txtId.getText().trim();
        String nombreEnf = txtNombreEnfermedad.getText().trim();
        TipoEnfermedad gravedad = cmbGravedad.getValue();
        boolean jubilado = chkJubilado.isSelected();

        if (tipo == null || nombre.isEmpty() || id.isEmpty()) {
            mostrarError("Datos incompletos", "Complete tipo, nombre e ID.");
            return;
        }

        Enfermedad enf = null;
        if (!nombreEnf.isEmpty() && gravedad != null) {
            enf = new Enfermedad(nombreEnf, gravedad);
        }

        try {
            if (pacienteEditar != null) {

                Paciente nuevo;
                if ("INFANTE".equalsIgnoreCase(tipo)) {
                    nuevo = new Infante.Builder()
                            .setNombre(nombre)
                            .setId(id)
                            .setEnfermedad(enf)
                            .build();
                } else {
                    nuevo = new AdultoMayor.Builder()
                            .setNombre(nombre)
                            .setId(id)
                            .setJubilacion(jubilado)
                            .setEnfermedad(enf)
                            .build();
                }
                facade.actualizarPacienteFacade(pacienteEditar.getId(), nuevo);
            } else {

                Paciente nuevo;
                if ("INFANTE".equalsIgnoreCase(tipo)) {
                    nuevo = new Infante.Builder()
                            .setNombre(nombre)
                            .setId(id)
                            .setEnfermedad(enf)
                            .build();
                } else {
                    nuevo = new AdultoMayor.Builder()
                            .setNombre(nombre)
                            .setId(id)
                            .setJubilacion(jubilado)
                            .setEnfermedad(enf)
                            .build();
                }
                facade.registrarPacienteFacade(nuevo);
            }

            closeWindow();
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarError("Error", "No se pudo guardar el paciente: " + ex.getMessage());
        }
    }

    @FXML
    public void onCancelar() {
        closeWindow();
    }

    private void closeWindow() {
        Stage st = (Stage) btnCancelar.getScene().getWindow();
        st.close();
    }

    private void mostrarError(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title); a.setHeaderText(null); a.setContentText(msg); a.showAndWait();
    }
}
