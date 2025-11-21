package co.edu.uniquindio.parcialunidadiii.model;

public class Medico {
    private String nombre;
    private String idMedico;
    private TipoEspecialidad especialidad;

    private Medico (Builder builder) {
        this.nombre = builder.nombre;
        this.idMedico = builder.idMedico;
        this.especialidad = builder.especialidad;

    }

    //Builder
    public static class Builder {
        private String nombre;
        private String idMedico;
        private TipoEspecialidad especialidad;

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setIdMedico(String idMedico) {
            this.idMedico = idMedico;
            return this;
        }

        public Builder setEspecialidad(TipoEspecialidad especialidad) {
            this.especialidad = especialidad;
            return this;
        }


        public Medico build() {
            return new Medico(this);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public TipoEspecialidad getEspecialidad() {
        return especialidad;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "nombre='" + nombre + '\'' +
                ", idMedico='" + idMedico + '\'' +
                ", especialidad=" + especialidad +
                '}';
    }
}
