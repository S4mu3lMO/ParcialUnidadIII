package co.edu.uniquindio.parcialunidadiii.state;

import co.edu.uniquindio.parcialunidadiii.model.Consultorio;

public class EstadoOcupado implements EstadoConsultorio {

    @Override
    public void ocupar(Consultorio consultorio) {
        System.out.println("⚠ Ya está ocupado");
    }

    @Override
    public void liberar(Consultorio consultorio) {
        System.out.println("✔ Consultorio liberado. Ahora está VACÍO.");
        consultorio.setEstado(new EstadoVacio());
    }

    @Override
    public void usar(Consultorio consultorio) {
        System.out.println("❌ No se puede usar. Está ocupado por mantenimiento o limpieza.");
    }

    @Override
    public String getNombreEstado() {
        return "OCUPADO";
    }
}
