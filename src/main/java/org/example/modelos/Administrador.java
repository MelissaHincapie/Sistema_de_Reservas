package org.example.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Administrador {
    private List<Vehiculo> flota = new ArrayList<>();


    public void añadirVehiculo(Vehiculo vehiculo){
        flota.add(vehiculo);
    }


    public boolean verificarDisponibilidad(Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin, List<Reserva> reservas){
        if (!vehiculo.isDisponible()) {
            return false;
        };
        for (Reserva r : reservas) {
            if (r.getVehiculo().equals(vehiculo) && r.isConfirmada()) {
                boolean repetida = !(fechaFin.isBefore(r.getFechaInicio()) || fechaInicio.isAfter(r.getFechaFin()));
                if (repetida) return false;
            }
        }
        return true;
    }

    public List<Vehiculo> listarVehiculosDisponibles(){
        List<Vehiculo> disponibles = new ArrayList<>();
        for (Vehiculo v : flota){
            if (v.isDisponible()) disponibles.add(v);
        }
        return disponibles;
    }
}
