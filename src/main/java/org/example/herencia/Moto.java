package org.example.herencia;

import org.example.modelos.Vehiculo;

public class Moto extends Vehiculo {
    private int cilindrada;


    public Moto(String idVehiculo, String marca, String modelo, int anno, double costoDiario, int cilindrada) {
        super(idVehiculo, marca, modelo, anno, costoDiario);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }
}
