package co.edu.uniquindio.parcialunidadiii.observer;

import co.edu.uniquindio.parcialunidadiii.model.Cita;

public interface SujetoCitas {
    void addObserver(ObservadorCitas observer);
    void removeObserver(ObservadorCitas observer);
    void notifyObservers(Cita cita);
}
