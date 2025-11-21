package co.edu.uniquindio.parcialunidadiii.chainofresponsability;

import co.edu.uniquindio.parcialunidadiii.model.Clinica;
import co.edu.uniquindio.parcialunidadiii.model.Medico;
import co.edu.uniquindio.parcialunidadiii.model.Paciente;
import co.edu.uniquindio.parcialunidadiii.model.TipoEspecialidad;
import co.edu.uniquindio.parcialunidadiii.model.TipoEnfermedad;

public class UrgenciaHandler extends AsignacionHandler {

    @Override
    public Medico handle(Paciente paciente) {

        if (paciente.getEnfermedad().getGravedadEnfermedad() == TipoEnfermedad.URGENTE) {

            // Buscar médicos especialistas críticos
            for (Medico m : Clinica.getInstancia().getMedicos()) {
                if (m.getEspecialidad() == TipoEspecialidad.CARDIOLOGO ||
                        m.getEspecialidad() == TipoEspecialidad.NEUROLOGO) {

                    System.out.println("✔ Paciente URGENTE asignado a: " + m.getNombre());
                    return m;
                }
            }
        }

        // Dejar que otro handler lo intente
        if (next != null) return next.handle(paciente);

        return null;
    }
}
