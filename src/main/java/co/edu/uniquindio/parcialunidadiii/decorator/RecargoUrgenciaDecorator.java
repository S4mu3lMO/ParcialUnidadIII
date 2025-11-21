package co.edu.uniquindio.parcialunidadiii.decorator;

public class RecargoUrgenciaDecorator extends PrecioCitaDecorator {

    private double extra;

    public RecargoUrgenciaDecorator(PrecioCita wrappee, double extra) {
        super(wrappee);
        this.extra = extra;
    }

    @Override
    public double calcularPrecio() {
        return super.calcularPrecio() + extra;
    }
}
