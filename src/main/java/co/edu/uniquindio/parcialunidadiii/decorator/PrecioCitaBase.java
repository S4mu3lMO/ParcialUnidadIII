package co.edu.uniquindio.parcialunidadiii.decorator;

public class PrecioCitaBase implements PrecioCita {

    private double precioBase;

    public PrecioCitaBase(double precioBase) {
        this.precioBase = precioBase;
    }

    @Override
    public double calcularPrecio() {
        return precioBase;
    }
}
