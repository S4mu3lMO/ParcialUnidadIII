package co.edu.uniquindio.parcialunidadiii.chainofresponsability;

import co.edu.uniquindio.parcialunidadiii.model.*;

public class EsperaHandler extends AsignacionHandler {

    @Override
    public Medico handle(Paciente paciente) {

        if (paciente.getEnfermedad().getGravedadEnfermedad() == TipoEnfermedad.ESPERA) {

            for (Medico m : Clinica.getInstancia().getMedicos()) {
                if (m.getEspecialidad() == TipoEspecialidad.GENERAL ||
                        m.getEspecialidad() == TipoEspecialidad.ORTOPEDICO) {

                    System.out.println("✔ Paciente ESPERA asignado a: " + m.getNombre());
                    return m;
                }
            }
        }

        if (next != null) return next.handle(paciente);

        return null;
    }
}
