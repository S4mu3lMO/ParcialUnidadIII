package co.edu.uniquindio.parcialunidadiii.model;

import java.util.ArrayList;

public class Clinica {

    private static Clinica instancia;

    private String nombre;
    private String nit;
    private ArrayList<Medico> medicos;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Cita> citas;

    private Clinica() {
        medicos = new ArrayList<>();
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();
    }

    public static Clinica getInstancia() {
        if (instancia == null) {
            instancia = new Clinica();
        }
        return instancia;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }

    public ArrayList<Medico> getMedicos() { return medicos; }

    public ArrayList<Paciente> getPacientes() { return pacientes; }

    public ArrayList<Cita> getCitas() { return citas; }
}
