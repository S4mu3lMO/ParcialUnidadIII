package co.edu.uniquindio.parcialunidadiii.chainofresponsability;

import co.edu.uniquindio.parcialunidadiii.model.*;

public class LeveHandler extends AsignacionHandler {

    @Override
    public Medico handle(Paciente paciente) {

        if (paciente.getEnfermedad().getGravedadEnfermedad() == TipoEnfermedad.LEVE) {

            for (Medico m : Clinica.getInstancia().getMedicos()) {
                if (m.getEspecialidad() == TipoEspecialidad.GENERAL) {
                    System.out.println("✔ Paciente LEVE asignado a: " + m.getNombre());
                    return m;
                }
            }
        }

        if (next != null) return next.handle(paciente);

        return null;
    }
}
