package org.example.herencia;

import org.example.modelos.Vehiculo;

public class Camioneta extends Vehiculo {
    private double capacidadCarga;

    public Camioneta(String idVehiculo, String marca, String modelo, int anno, double costoDiario, double capacidadCarga) {
        super(idVehiculo, marca, modelo, anno, costoDiario);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }
}
