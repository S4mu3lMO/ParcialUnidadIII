package co.edu.uniquindio.parcialunidadiii.chainofresponsability;

import co.edu.uniquindio.parcialunidadiii.model.Medico;
import co.edu.uniquindio.parcialunidadiii.model.Paciente;

public class ServicioAsignacion {

    private AsignacionHandler chain;

    public ServicioAsignacion() {


        chain = new UrgenciaHandler();
        chain.setNext(new EsperaHandler())
                .setNext(new LeveHandler());
    }

    public Medico asignarMedico(Paciente paciente) {
        return chain.handle(paciente);
    }
}

