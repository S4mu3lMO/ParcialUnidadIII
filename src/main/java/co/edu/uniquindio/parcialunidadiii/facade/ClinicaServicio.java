package co.edu.uniquindio.parcialunidadiii.facade;

import co.edu.uniquindio.parcialunidadiii.CitasService;
import co.edu.uniquindio.parcialunidadiii.model.Cita;
import co.edu.uniquindio.parcialunidadiii.model.Consultorio;
import co.edu.uniquindio.parcialunidadiii.model.Medico;
import co.edu.uniquindio.parcialunidadiii.model.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class ClinicaServicio {

    private ArrayList<Paciente> pacientes;
    private ArrayList<Medico> medicos;
    private ArrayList<Cita> citas;

    public ClinicaServicio() {
        pacientes = new ArrayList<>();
        medicos = new ArrayList<>();
        citas = new ArrayList<>();
    }

    // -----------------------
    // CRUD PACIENTES
    // -----------------------

    public void agregarPaciente(Paciente p) {
        pacientes.add(p);
    }

    public boolean eliminarPaciente(String id) {
        return pacientes.removeIf(p -> p.getId().equals(id));
    }

    public Optional<Paciente> buscarPaciente(String id) {
        return pacientes.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public ArrayList<Paciente> listarPacientes() {
        return new ArrayList<>(pacientes);
    }

    public boolean actualizarPaciente(String id, Paciente nuevoPaciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getId().equals(id)) {
                pacientes.set(i, nuevoPaciente);
                return true;
            }
        }
        return false;
    }

    // -----------------------
    // CRUD MÉDICOS
    // -----------------------

    public void agregarMedico(Medico m) {
        medicos.add(m);
    }

    public boolean eliminarMedico(String idMedico) {
        return medicos.removeIf(m -> m.getIdMedico().equals(idMedico));
    }

    public Optional<Medico> buscarMedico(String idMedico) {
        return medicos.stream()
                .filter(m -> m.getIdMedico().equals(idMedico))
                .findFirst();
    }

    public ArrayList<Medico> listarMedicos() {
        return new ArrayList<>(medicos);
    }

    public boolean actualizarMedico(String idMedico, Medico nuevoMedico) {
        for (int i = 0; i < medicos.size(); i++) {
            if (medicos.get(i).getIdMedico().equals(idMedico)) {
                medicos.set(i, nuevoMedico);
                return true;
            }
        }
        return false;
    }

    // -------------------------------------
    // CITAS
    // -------------------------------------

    public void crearCita(Paciente p, Consultorio c, LocalDate fecha, LocalDateTime hora, double precio) {

        CitasService citasService = new CitasService();
        Cita cita = citasService.crearCita(p, c, hora, fecha);

        cita.setPrecio(precio);
        citas.add(cita);
    }

    public ArrayList<Cita> obtenerCitas() {
        return new ArrayList<>(citas);
    }

    public boolean eliminarCita(Cita cita) {
        return citas.remove(cita);
    }
}
