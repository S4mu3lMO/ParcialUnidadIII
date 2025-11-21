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
            showAlert(Alert.AlertType.ERROR, "Datos faltantes", "Complete todos los campos.");
            return;
        }

        LocalTime lt;
        try {
            lt = LocalTime.parse(horaStr);
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Hora inválida", "Use formato HH:mm, por ejemplo 14:30");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.ERROR, "Precio inválido", "Ingrese un número válido para el precio.");
            return;
        }

        LocalDateTime hora = LocalDateTime.of(fecha, lt);
        Consultorio consultorio = new Consultorio(idConsultorio, m);

        // Llamamos al facade: ajustar si tu firma es diferente
        try {
            facade.crearCitaFacade(p, consultorio, fecha, hora, precio);
            closeWindow();
        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo crear la cita: " + ex.getMessage());
        }
    }

    @FXML
    public void onCancelar() {
        closeWindow();
    }

    private void closeWindow() {
        Stage st = (Stage) txtPrecio.getScene().getWindow();
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
