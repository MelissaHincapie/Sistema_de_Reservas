package org.example.herencia;

import org.example.modelos.Vehiculo;

public class Autobus extends Vehiculo {
    protected int capacidadPasajeros;

    public Autobus(String idVehiculo, String marca, String modelo, int anno, double costoDiario, int capacidadPasajeros) {
        super(idVehiculo, marca, modelo, anno, costoDiario);
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }
}
