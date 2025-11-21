package co.edu.uniquindio.parcialunidadiii.app;

import co.edu.uniquindio.parcialunidadiii.factory.PacienteFactory;
import co.edu.uniquindio.parcialunidadiii.model.*;
import co.edu.uniquindio.parcialunidadiii.observer.AgendaCitas;
import co.edu.uniquindio.parcialunidadiii.observer.ServicioEmail;
import co.edu.uniquindio.parcialunidadiii.observer.ServicioSMS;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class MainPruebas {
    public static void main(String[] args) {
        // 1. Crear el Sujeto observable
        AgendaCitas gestor = new AgendaCitas();

        // 2. Crear los Observadores
        ServicioSMS sms = new ServicioSMS();
        ServicioEmail email = new ServicioEmail();

        // 3. Registrar los observadores en el sujeto
        gestor.addObserver(sms);
        gestor.addObserver(email);

        // 4. Cuando ocurre un evento (una cita agendada):
        Medico medico = new Medico.Builder()
                .setIdMedico("222")
                .setNombre("PEPE")
                .setEspecialidad(TipoEspecialidad.CARDIOLOGO)
                .build();

        Paciente paciente = PacienteFactory.createPaciente("INFANTE" ,"Juanito", "1234"); // Usando tus clases Paciente
        Paciente paciente2 = PacienteFactory.createPaciente("ADULTOMAYOR" ,"PEDRO", "1235");
        Consultorio consultorio = new Consultorio("111", medico);
        Cita cita = new Cita(paciente, consultorio, LocalDate.now(), LocalDateTime.now(), 1234.0);
        Cita cita2 = new Cita(paciente2, consultorio, LocalDate.now(), LocalDateTime.now(), 1234.0);


        gestor.agendarNuevaCita(cita);

        // Si decides que ya no quieres notificaciones SMS:
        gestor.removeObserver(sms);

        System.out.println("\n--- Agendando segunda cita sin SMS ---");
        gestor.agendarNuevaCita(cita2);
    }
}
