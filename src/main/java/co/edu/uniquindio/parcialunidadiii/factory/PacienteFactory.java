package co.edu.uniquindio.parcialunidadiii.factory;

import co.edu.uniquindio.parcialunidadiii.model.AdultoMayor;
import co.edu.uniquindio.parcialunidadiii.model.Infante;
import co.edu.uniquindio.parcialunidadiii.model.Paciente;

// Patron Factory Method
public class PacienteFactory {
    public static Paciente createPaciente(String tipo, String nombre, String id){
        switch (tipo.toUpperCase()){
            case "INFANTE":
                return new Infante.Builder()
                        .setId(id)
                        .setNombre(nombre)
                        .build();
            case "ADULTOMAYOR":
                return new AdultoMayor.Builder()
                        .setId(id)
                        .setNombre(nombre)
                        .build();
            default:
                throw new IllegalArgumentException(tipo + " no es un tipo valido de paciente");
        }
    }
}
