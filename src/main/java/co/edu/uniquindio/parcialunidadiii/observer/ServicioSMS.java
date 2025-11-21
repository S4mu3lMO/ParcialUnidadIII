package co.edu.uniquindio.parcialunidadiii.observer;

import co.edu.uniquindio.parcialunidadiii.model.Cita;

public class ServicioSMS implements ObservadorCitas {
    @Override
    public void actualizar(Cita cita) {
        System.out.println("SMS: Enviando mensaje de texto de confirmación.");
    }
}
