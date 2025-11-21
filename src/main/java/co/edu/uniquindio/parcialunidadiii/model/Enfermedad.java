package co.edu.uniquindio.parcialunidadiii.model;

public class Enfermedad {
    private String nombreEnfermedad;
    private TipoEnfermedad gravedadEnfermedad;

    public Enfermedad(String nombreEnfermedad, TipoEnfermedad gravedadEnfermedad) {
        this.nombreEnfermedad = nombreEnfermedad;
        this.gravedadEnfermedad = gravedadEnfermedad;
    }

    public String getNombreEnfermedad() {
        return nombreEnfermedad;
    }

    public void setNombreEnfermedad(String nombreEnfermedad) {
        this.nombreEnfermedad = nombreEnfermedad;
    }

    public TipoEnfermedad getGravedadEnfermedad() {
        return gravedadEnfermedad;
    }

    public void setGravedadEnfermedad(TipoEnfermedad gravedadEnfermedad) {
        this.gravedadEnfermedad = gravedadEnfermedad;
    }
}
