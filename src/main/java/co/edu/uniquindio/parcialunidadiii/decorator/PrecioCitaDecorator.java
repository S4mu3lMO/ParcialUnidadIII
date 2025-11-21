package co.edu.uniquindio.parcialunidadiii.decorator;

public abstract class PrecioCitaDecorator implements PrecioCita {

    protected PrecioCita wrappee;

    public PrecioCitaDecorator(PrecioCita wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public double calcularPrecio() {
        return wrappee.calcularPrecio();
    }
}
