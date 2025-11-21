package co.edu.uniquindio.parcialunidadiii.model;

public abstract class Paciente {
    protected String nombre;
    protected String id;
    protected Enfermedad enfermedad;

    protected Paciente(Builder<?> builder) {
        this.nombre = builder.nombre;
        this.id = builder.id;
        this.enfermedad = builder.enfermedad;
    }


    //Builder
    public abstract static class Builder<T extends Builder<T>> {
        private String nombre;
        private String id;
        private Enfermedad enfermedad;

        public T setNombre(String nombre) {
            this.nombre = nombre;
            return self();
        }

        public T setId(String id) {
            this.id = id;
            return self();
        }

        public T setEnfermedad(Enfermedad enfermedad) {
            this.enfermedad = enfermedad;
            return self();
        }


        protected abstract T self();
        protected abstract Paciente build();
    }

    // getters y setters

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }
}
