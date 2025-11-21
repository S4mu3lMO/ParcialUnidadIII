package co.edu.uniquindio.parcialunidadiii.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cita {

    private Paciente paciente;
    private Consultorio consultorio;
    private LocalDate fecha;
    private LocalDateTime hora;
    private double precio;

    public Cita(Paciente paciente, Consultorio consultorio, LocalDate fecha, LocalDateTime hora, double precio) {
        this.paciente = paciente;
        this.consultorio = consultorio;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
    }


    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "paciente=" + paciente +
                ", consultorio=" + consultorio +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", precio=" + precio +
                '}';
    }
}