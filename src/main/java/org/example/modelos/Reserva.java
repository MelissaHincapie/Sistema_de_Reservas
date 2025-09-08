package org.example.modelos;

import java.time.LocalDate;

public class Reserva {
    protected String idReserva;
    protected Cliente cliente;
    protected Vehiculo vehiculo;
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    protected double costoTotal;
    protected boolean confirmada;

    public Reserva(String idReserva, Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin, boolean seguro, boolean gps) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        long dias = chronoUnit.DAYS.between(fechaInicio, fechaFin);
        this.costoTotal = vehiculo.calcularPrecio((int) dias, seguro, gps);
    }

    public void confirmarReserva(){
        vehiculo.setDisponible(false);
        confirmada = true;
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
