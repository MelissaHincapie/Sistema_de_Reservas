package org.example.modelos;

public class Vehiculo {
    protected String idVehiculo;
    protected String marca;
    protected String modelo;
    protected int anno;
    protected double costoDiario;
    protected boolean disponible = true;

    public Vehiculo(String idVehiculo, String marca, String modelo, int anno, double costoDiario){
        this.idVehiculo = idVehiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.anno = anno;
        this.costoDiario = costoDiario;
    }

    public String getIdVehiculo() {
        return idVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnno() {
        return anno;
    }

    public double getCostoDiario() {
        return costoDiario;
    }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public double calcularPrecio (int dias, boolean seguro, boolean gps){

        // Cálcula costo basado en días
        double precio = dias * costoDiario;

        //Seguro: 10 % adicional al costo diario.
        if (seguro){
            precio += precio * 0.10;
        }

        //GPS: Cargo fijo de $5 por día.
        if (gps){
            precio += dias * 5;
        }
        return precio;
    }

    public String toString() {
        return idVehiculo + " - " + marca + " " + modelo + " (" + anno + ") $" + costoDiario + "/día";
    }
}
