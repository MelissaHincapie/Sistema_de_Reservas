package org.example.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int idCliente;
    private String nombre;
    private List<Reserva> reservas = new ArrayList<>();

    public Cliente (int idCliente, String nombre){
        this.idCliente = idCliente;
        this.nombre = nombre;
    }

    public int getIdCliente() {

        return idCliente;
    }

    public String getNombre() {

        return nombre;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }


    public void reservarVehiculo (Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin, boolean seguro, boolean gps){
        if (!vehiculo.isDisponible()) {
            throw new IllegalStateException("El vehículo no esta disponible");
        }
        Reserva reserva = new Reserva("R-" + (reservas.size() + 1), this, vehiculo, fechaInicio, fechaFin, seguro, gps);
        reservas.add(reserva);
        reserva.confirmarReserva();


//        Crea una reserva para el cliente si el
//        vehículo está disponible y lo añade a la lista de reservas.
    }
}
