package co.edu.uniquindio.parcialunidadiii.observer;

import co.edu.uniquindio.parcialunidadiii.model.Cita;

import java.util.ArrayList;
import java.util.List;

public class AgendaCitas implements SujetoCitas {
    private List<ObservadorCitas> observadores = new ArrayList<>();

    @Override
    public void addObserver(ObservadorCitas o) {
        observadores.add(o);
    }

    @Override
    public void removeObserver(ObservadorCitas o) {
        observadores.remove(o);
    }
    public void agendarNuevaCita(Cita nuevaCita) {
        System.out.println("Cita agendada. Notificando observadores...");
        notifyObservers(nuevaCita);
    }

    @Override
    public void notifyObservers(Cita cita) {
        for (ObservadorCitas o : observadores) {
            o.actualizar(cita);
        }
    }
}
