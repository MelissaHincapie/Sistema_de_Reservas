package org.example.modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private final String idReserva;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double costoTotal;
    private boolean confirmada;

    public Reserva(String idReserva, Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin, boolean seguro, boolean gps) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;

        //Calcula la diración en días de la reserva
        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        this.costoTotal = vehiculo.calcularPrecio((int) dias, seguro, gps);
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void confirmarReserva(){
        vehiculo.setDisponible(false);
        confirmada = true;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    @Override
    public String toString() {
        return "Reserva " + idReserva + ": " + cliente.getNombre() + " -> " + vehiculo +
                " del " + fechaInicio + " al " + fechaFin + " $" + costoTotal;
    }
}
