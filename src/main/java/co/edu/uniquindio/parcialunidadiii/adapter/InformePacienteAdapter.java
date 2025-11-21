package co.edu.uniquindio.parcialunidadiii.adapter;

import co.edu.uniquindio.parcialunidadiii.model.Paciente;

public class InformePacienteAdapter implements InformePaciente {

    private SistemaExternoInformes sistemaExterno;

    public InformePacienteAdapter(SistemaExternoInformes sistemaExterno) {
        this.sistemaExterno = sistemaExterno;
    }

    @Override
    public String generarResumen(Paciente paciente) {
        return sistemaExterno.createReport(
                paciente.getNombre(),
                paciente.getId(),
                paciente.getEnfermedad().getNombreEnfermedad()
        );
    }
}
