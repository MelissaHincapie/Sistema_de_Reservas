package org.example.modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private static int contador = 1;
    private final String idReserva;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double costoTotal;
    private boolean confirmada;
    private boolean seguro;
    private boolean gps;

    public Reserva(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin, boolean seguro, boolean gps) {
        this.idReserva = "R" + contador++ ;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;


        //Calcula la diración en días de la reserva
        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        this.costoTotal = vehiculo.calcularPrecio((int) dias, seguro, gps);
        this.seguro = seguro;
        this.gps = gps;
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
        int dias = (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin);

        StringBuilder sb = new StringBuilder();
        sb.append("Reserva ").append(idReserva).append(": ")
                .append(cliente.getNombre()).append(" -> ").append(vehiculo)
                .append(" del ").append(fechaInicio).append(" al ").append(fechaFin).append("\n");

        double costoBase = dias * vehiculo.getCostoDiario();
        sb.append("  Costo base (").append(dias).append(" días): $").append(costoBase).append("\n");

        if (seguro) {
            double extraSeguro = costoBase * 0.10;
            sb.append("  Seguro (10%): $").append(extraSeguro).append("\n");
        }

        if (gps) {
            double extraGps = dias * 5;
            sb.append("  GPS ($5/día): $").append(extraGps).append("\n");
        }

        sb.append("  TOTAL: $").append(costoTotal);

        return sb.toString();
    }
}
