package org.example.herencia;

import org.example.modelos.Vehiculo;

public class Camioneta extends Vehiculo {
    protected int capacidadCarga;

    public Camioneta(String idVehiculo, String marca, String modelo, int anno, double costoDiario, int capacidadCarga) {
        super(idVehiculo, marca, modelo, anno, costoDiario);
        this.capacidadCarga = capacidadCarga;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }
}
