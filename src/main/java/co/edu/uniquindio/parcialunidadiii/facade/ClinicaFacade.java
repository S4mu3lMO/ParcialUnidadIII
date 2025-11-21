package co.edu.uniquindio.parcialunidadiii.facade;

import co.edu.uniquindio.parcialunidadiii.model.Cita;
import co.edu.uniquindio.parcialunidadiii.model.Consultorio;
import co.edu.uniquindio.parcialunidadiii.model.Medico;
import co.edu.uniquindio.parcialunidadiii.model.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ClinicaFacade {

    private static ClinicaFacade instancia;

    private ClinicaServicio servicio;

    private ClinicaFacade() {
        this.servicio = new ClinicaServicio();
    }

    public static ClinicaFacade getInstance() {
        if (instancia == null) {
            instancia = new ClinicaFacade();
        }
        return instancia;
    }

    // -------------------------------------
    // PACIENTES
    // -------------------------------------

    public void registrarPacienteFacade(Paciente p) {
        servicio.agregarPaciente(p);
    }

    public boolean eliminarPacienteFacade(String id) {
        return servicio.eliminarPaciente(id);
    }

    public boolean actualizarPacienteFacade(String id, Paciente actualizado) {
        return servicio.actualizarPaciente(id, actualizado);
    }

    public ArrayList<Paciente> obtenerPacientesFacade() {
        return servicio.listarPacientes();
    }

    // -------------------------------------
    // MÉDICOS
    // -------------------------------------

    public void registrarMedicoFacade(Medico m) {
        servicio.agregarMedico(m);
    }

    public boolean eliminarMedicoFacade(String idMedico) {
        return servicio.eliminarMedico(idMedico);
    }

    public boolean actualizarMedicoFacade(String idMedico, Medico actualizado) {
        return servicio.actualizarMedico(idMedico, actualizado);
    }

    public ArrayList<Medico> obtenerMedicosFacade() {
        return servicio.listarMedicos();
    }

    // -------------------------------------
    // CITAS
    // -------------------------------------

    public void crearCitaFacade(Paciente p, Consultorio c, LocalDate fecha, LocalDateTime hora, double precio) {
        servicio.crearCita(p, c, fecha, hora, precio);
    }

    public ArrayList<Cita> obtenerCitasFacade() {
        return servicio.obtenerCitas();
    }

    public boolean eliminarCitaFacade(Cita cita) {
        return servicio.eliminarCita(cita);
    }
}
