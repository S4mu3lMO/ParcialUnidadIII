package co.edu.uniquindio.parcialunidadiii.state;

import co.edu.uniquindio.parcialunidadiii.model.Consultorio;

public class EstadoVacio implements EstadoConsultorio {

    @Override
    public void ocupar(Consultorio consultorio) {
        System.out.println("✔ El consultorio ahora está OCUPADO");
        consultorio.setEstado(new EstadoOcupado());
    }

    @Override
    public void liberar(Consultorio consultorio) {
        System.out.println("⚠ El consultorio ya está vacío");
    }

    @Override
    public void usar(Consultorio consultorio) {
        System.out.println("✔ El consultorio pasa a EN USO");
        consultorio.setEstado(new EstadoEnUso());
    }

    @Override
    public String getNombreEstado() {
        return "VACÍO";
    }
}
