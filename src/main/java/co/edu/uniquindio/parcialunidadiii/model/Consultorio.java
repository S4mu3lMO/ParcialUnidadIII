package co.edu.uniquindio.parcialunidadiii.model;

import co.edu.uniquindio.parcialunidadiii.model.Medico;
import co.edu.uniquindio.parcialunidadiii.state.*;

public class Consultorio {

    private String idConsultorio;
    private Medico medico;

    // Aquí usamos el Patrón State
    private EstadoConsultorio estado;

    public Consultorio(String idConsultorio, Medico medico) {
        this.idConsultorio = idConsultorio;
        this.medico = medico;
        this.estado = new EstadoVacio();  // Estado inicial
    }

    public String getIdConsultorio() {
        return idConsultorio;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public EstadoConsultorio getEstado() {
        return estado;
    }

    public void setEstado(EstadoConsultorio estado) {
        this.estado = estado;
    }

    // Acciones delegadas al estado
    public void ocupar() {
        estado.ocupar(this);
    }

    public void liberar() {
        estado.liberar(this);
    }

    public void usar() {
        estado.usar(this);
    }
}
