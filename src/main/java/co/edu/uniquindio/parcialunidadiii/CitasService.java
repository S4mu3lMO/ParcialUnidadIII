package co.edu.uniquindio.parcialunidadiii;

import co.edu.uniquindio.parcialunidadiii.model.Consultorio;
import co.edu.uniquindio.parcialunidadiii.observer.ObservadorCitas;
import co.edu.uniquindio.parcialunidadiii.model.Cita;
import co.edu.uniquindio.parcialunidadiii.model.Medico;
import co.edu.uniquindio.parcialunidadiii.model.Paciente;
import co.edu.uniquindio.parcialunidadiii.observer.SujetoCitas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CitasService implements SujetoCitas {
    private List<ObservadorCitas> observadores = new ArrayList<>();
    private Cita ultimaCitaCreada;


    @Override
    public void addObserver(ObservadorCitas o) {
        observadores.add(o);
    }

    @Override
    public void removeObserver(ObservadorCitas o) {
        observadores.remove(o);
    }

    @Override
    public void notifyObservers(Cita cita) {
        for (ObservadorCitas o : observadores) {
            o.actualizar(ultimaCitaCreada);
        }
    }


    public Cita crearCita(Paciente paciente, Consultorio consultorio, LocalDateTime hora, LocalDate fecha) {
        Cita cita = new Cita(paciente, consultorio, LocalDate.now(), LocalDateTime.now(), 1234.0);

        Cita nuevaCita = new Cita(paciente, consultorio, fecha, hora, 123.0);


        System.out.println("-> ServicioCitas: Cita creada exitosamente.");

        this.ultimaCitaCreada = nuevaCita;

        notifyObservers(cita);

        return nuevaCita;
    }

}
