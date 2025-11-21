package co.edu.uniquindio.parcialunidadiii.state;

import co.edu.uniquindio.parcialunidadiii.model.Consultorio;

public interface EstadoConsultorio {
    void ocupar(Consultorio consultorio);
    void liberar(Consultorio consultorio);
    void usar(Consultorio consultorio);
    String getNombreEstado();
}
