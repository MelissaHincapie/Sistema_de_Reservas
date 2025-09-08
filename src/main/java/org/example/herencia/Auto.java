package org.example.herencia;

import org.example.modelos.Vehiculo;

public class Auto extends Vehiculo {
    protected String tipoCombustible;

    public Auto(String idVehiculo, String marca, String modelo, int anno, double costoDiario, String tipoCombustible) {
        super(idVehiculo, marca, modelo, anno, costoDiario);
        this.tipoCombustible=tipoCombustible;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }
}
