package co.edu.uniquindio.parcialunidadiii.controller;

import co.edu.uniquindio.parcialunidadiii.facade.ClinicaFacade;
import co.edu.uniquindio.parcialunidadiii.model.Consultorio;
import co.edu.uniquindio.parcialunidadiii.model.Medico;
import co.edu.uniquindio.parcialunidadiii.model.Paciente;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ModalCitaController {

    @FXML private ComboBox<Paciente> cmbPaciente;
    @FXML private ComboBox<Medico> cmbMedico;
    @FXML private TextField txtConsultorioId;
    @FXML private DatePicker datePicker;
    @FXML private TextField txtHora;
    @FXML private TextField txtPrecio;
    @FXML private Button btnCrear;
    @FXML private Button btnCancelar;

    private ClinicaFacade facade = ClinicaFacade.getInstance();

    @FXML
    public void initialize() {
        List<Paciente> pacientes = facade.obtenerPacientesFacade();
        List<Medico> medicos = facade.obtenerMedicosFacade();
        cmbPaciente.getItems().setAll(pacientes);
        cmbMedico.getItems().setAll(medicos);

        cmbPaciente.setConverter(new javafx.util.StringConverter<Paciente>() {
            @Override public String toString(Paciente p) { return p == null ? "" : p.getNombre() + " (" + p.getId() + ")"; }
            @Override public Paciente fromString(String s) { return null; }
        });

        cmbMedico.setConverter(new javafx.util.StringConverter<Medico>() {
            @Override public String toString(Medico m) { return m == null ? "" : m.getNombre() + " [" + m.getIdMedico() + "]"; }
            @Override public Medico fromString(String s) { return null; }
        });
    }

    @FXML
    public void onCrear() {
        Paciente p = cmbPaciente.getValue();
        Medico m = cmbMedico.getValue();
        String idConsultorio = txtConsultorioId.getText().trim();
        LocalDate fecha = datePicker.getValue();
        String horaStr = txtHora.getText().trim();
        String precioStr = txtPrecio.getText().trim();

        if (p == null || m == null || idConsultorio.isEmpty() || fecha == null || horaStr.isEmpty() || precioStr.isEmpty()) {
            mostrarError("Datos faltantes", "Complete todos los campos.");
            return;
        }

        LocalTime lt;
        try {
            lt = LocalTime.parse(horaStr);
        } catch (Exception ex) {
            mostrarError("Hora inválida", "Use formato HH:mm (ej. 14:30)");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException ex) {
            mostrarError("Precio inválido", "Ingrese un número válido.");
            return;
        }

        LocalDateTime hora = LocalDateTime.of(fecha, lt);
        Consultorio consultorio = new Consultorio(idConsultorio, m);

        try {
            facade.crearCitaFacade(p, consultorio, fecha, hora, precio);
            closeWindow();
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarError("Error", "No se pudo crear la cita: " + ex.getMessage());
        }
    }

    @FXML public void onCancelar() { closeWindow(); }

    private void closeWindow() { Stage st = (Stage) btnCancelar.getScene().getWindow(); st.close(); }

    private void mostrarError(String t, String m) { Alert a = new Alert(Alert.AlertType.ERROR); a.setTitle(t); a.setHeaderText(null); a.setContentText(m); a.showAndWait(); }
}
