package co.edu.uniquindio.parcialunidadiii.model;

public class AdultoMayor extends Paciente {
    public boolean jubilacion;

    public AdultoMayor(Builder builder){
        super(builder);
        this.jubilacion = builder.jubilacion;
    }

    //Builder
    public static class Builder extends Paciente.Builder<Builder> {

        public boolean jubilacion;

        public Builder setJubilacion(boolean jubilacion) {
            this.jubilacion = jubilacion;
            return this;
        }

        public boolean isJubilacion() { return jubilacion; }


        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public AdultoMayor build() {
            return new AdultoMayor(this);
        }
    }
}
