package org.example.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Administrador {
    protected List<Vehiculo> flota = new ArrayList<>();


    public void añadirVehiculo(Vehiculo vehiculo){
        flota.add(vehiculo);
    }


    public boolean verificarDisponibilidad(Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin){
        return vehiculo.isDisponible();
    }

    public List<Vehiculo> listarVehiculosDisponibles(){
        List<Vehiculo> disponibles = new ArrayList<>();
        for (Vehiculo v : flota){
            if (v.isDisponible()) disponibles.add(v);
        }
        return disponibles;
    }
}
