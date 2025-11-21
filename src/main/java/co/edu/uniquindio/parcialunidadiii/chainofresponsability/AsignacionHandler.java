package co.edu.uniquindio.parcialunidadiii.chainofresponsability;

import co.edu.uniquindio.parcialunidadiii.model.Medico;
import co.edu.uniquindio.parcialunidadiii.model.Paciente;

public abstract class AsignacionHandler {

    protected AsignacionHandler next;

    public AsignacionHandler setNext(AsignacionHandler next) {
        this.next = next;
        return next;
    }

    public abstract Medico handle(Paciente paciente);
}
