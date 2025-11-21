package co.edu.uniquindio.parcialunidadiii.decorator;

public class DescuentoSeguroDecorator extends PrecioCitaDecorator {

    private double porcentajeDescuento;

    public DescuentoSeguroDecorator(PrecioCita wrappee, double porcentajeDescuento) {
        super(wrappee);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double calcularPrecio() {
        double precio = super.calcularPrecio();
        double descuento = precio * porcentajeDescuento;
        return precio - descuento;
    }
}
