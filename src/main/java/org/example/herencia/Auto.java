package org.example.herencia;

import org.example.modelos.Vehiculo;

public class Auto extends Vehiculo {
    private String tipoCombustible;

    public Auto(String idVehiculo, String marca, String modelo, int anno, double costoDiario, String tipoCombustible) {
        super(idVehiculo, marca, modelo, anno, costoDiario);
        this.tipoCombustible=tipoCombustible;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    @Override
    public String toString() {
        return super.toString() + " | Combustible: " + tipoCombustible;
    }
}
