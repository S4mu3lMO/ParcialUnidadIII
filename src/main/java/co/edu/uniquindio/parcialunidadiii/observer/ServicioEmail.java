package co.edu.uniquindio.parcialunidadiii.observer;

import co.edu.uniquindio.parcialunidadiii.model.Cita;

public class ServicioEmail implements ObservadorCitas {
    @Override
    public void actualizar(Cita cita) {
        System.out.println("EMAIL: Enviando correo al médico y paciente sobre la cita: " + cita.getHora());
    }
}
