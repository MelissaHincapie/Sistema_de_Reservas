package org.example.modelos;

import org.example.herencia.Auto;
import org.example.herencia.Autobus;
import org.example.herencia.Camioneta;
import org.example.herencia.Moto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Administrador {
    private List<Vehiculo> flota = new ArrayList<>();


    public void añadirVehiculo(Vehiculo vehiculo){
        boolean existe = flota.stream()
                .anyMatch(v -> v.getIdVehiculo().equals(vehiculo.getIdVehiculo()));

        if (existe) {
            throw new IllegalArgumentException("Ya existe un vehículo con el ID: " + vehiculo.getIdVehiculo());
        }
        flota.add(vehiculo);
    }

    public void listarVehiculosDisponibles(){
        System.out.println("\n--- Vehículos disponibles ---");

        System.out.println("Autos:");
        flota.stream()
                .filter(v -> v instanceof Auto && v.isDisponible())
                .forEach(v -> System.out.println(" - " + v));

        System.out.println("\n Motos:");
        flota.stream()
                .filter(v -> v instanceof Moto && v.isDisponible())
                .forEach(v -> System.out.println(" - " + v));

        System.out.println("\n Camionetas:");
        flota.stream()
                .filter(v -> v instanceof Camioneta && v.isDisponible())
                .forEach(v -> System.out.println(" - " + v));

        System.out.println("\n Autobuses:");
        flota.stream()
                .filter(v -> v instanceof Autobus && v.isDisponible())
                .forEach(v -> System.out.println(" - " + v));
    }

    public List<Vehiculo> getDisponibles() {
        List<Vehiculo> disponibles = new ArrayList<>();
        for (Vehiculo v : flota) {
            if (v.isDisponible()) {
                disponibles.add(v);
            }
        }
        return disponibles;
    }

    public void listarReservas(List<Cliente> clientes) {
        System.out.println("\n--- Reservas registradas ---");
        for (Cliente c : clientes) {
            if (c.getReservas().isEmpty()) {
                System.out.println("Cliente " + c.getNombre() + " no tiene reservas.");
            } else {
                for (Reserva r : c.getReservas()) {
                    System.out.println(r);
                }
            }
        }
    }

    public void listarTodosLosVehiculos() {
        System.out.println("\n--- Flota completa ---");
        for (Vehiculo v : flota) {
            if (v.isDisponible()) {
                System.out.println("✅ " + v);
            } else {
                System.out.println("❌ " + v + " (NO disponible)");
            }
        }
    }
}
