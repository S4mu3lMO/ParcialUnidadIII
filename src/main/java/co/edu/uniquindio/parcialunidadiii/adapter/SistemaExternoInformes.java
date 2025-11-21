package co.edu.uniquindio.parcialunidadiii.adapter;

public class SistemaExternoInformes {

    public String createReport(String nombre, String id, String enfermedad) {
        return "Reporte externo:\nPaciente: " + nombre +
                "\nID: " + id +
                "\nEnfermedad: " + enfermedad;
    }
}
