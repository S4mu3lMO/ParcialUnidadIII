package co.edu.uniquindio.parcialunidadiii.model;

public class Infante extends Paciente {
    private Enfermedad tipoEnfermedad;

    public Infante(Builder builder){
        super(builder);
        this.tipoEnfermedad = builder.tipoEnfermedad;
    }

    //Builder
    public static class Builder extends Paciente.Builder<Builder> {

        private Enfermedad tipoEnfermedad;

        public Builder setEnfermedad(Enfermedad enfermedad) {
            this.tipoEnfermedad = enfermedad;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Infante build() {
            return new Infante(this);
        }
    }
}
