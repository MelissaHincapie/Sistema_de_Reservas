package org.example;
import org.example.modelos.*;
import org.example.herencia.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Administrador admin = new Administrador();
    private static final Scanner leer = new Scanner(System.in);

    private static final List<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        datosIniciales();

        boolean salir = false;


        while (!salir) {
            System.out.println("\n ---------Gestión de Reservas---------");
            System.out.println("1. Añadir vehículo");
            System.out.println("2. Listar vehículos disponibles");
            System.out.println("3. Resgitrar cliente y reservar");
            System.out.println("4. Lista de reservas");
            System.out.println("5. Lista de flota completa");
            System.out.println("6. Salir");
            System.out.println("Elige una opción");

            int opcion = leer.nextInt();
            leer.nextLine();


            switch (opcion) {
                case 1: {
                    añadirVehiculo();
                    break;
                }
                case 2: {
                    listarVehiculos();
                    break;
                }
                case 3: {
                    reservarVehiculo();
                    break;
                }
                case 4: {
                    admin.listarReservas(clientes);
                    break;
                }
                case 5: {
                    admin.listarTodosLosVehiculos();
                    break;
                }
                case 6: {
                    salir = true;
                    break;
                }
                default:
                    System.out.println("Opción inválida.");
            }
        }
        System.out.println("Gracias por utilizar nuestro sistema de reservas");
    }

    private static void añadirVehiculo() {
        System.out.println("---------Añadir vehículo---------");
        System.out.println("tipo : \n1. Auto \n2. Moto \n3. Camioneta \n4. Autobús");
        int tipo = leer.nextInt();
        leer.nextLine();

        System.out.print("ID: ");
        String id = leer.nextLine();
        System.out.print("Marca: ");
        String marca = leer.nextLine();
        System.out.print("Modelo: ");
        String modelo = leer.nextLine();
        System.out.print("Año: ");
        int anno = leer.nextInt();
        System.out.print("Costo diario: ");
        double costo = leer.nextDouble();
        leer.nextLine();

        Vehiculo v = null;
        switch (tipo) {
            case 1: {
                System.out.println("Tipo de conbustible:");
                String combustible = leer.nextLine();
                v = new Auto(id, marca, modelo, anno, costo, combustible);
                break;
            }
            case 2: {
                System.out.println("Cilindrada (cc): ");
                int cc = leer.nextInt();
                v = new Moto(id, marca, modelo, anno, costo, cc);
                break;
            }
            case 3: {
                System.out.println("Capacidad de carga (Kg): ");
                double carga = leer.nextDouble();
                v = new Camioneta(id, marca, modelo, anno, costo, carga);
                break;
            }
            case 4: {
                System.out.println("Capacidad de pasajeros: ");
                int pasajeros = leer.nextInt();
                v = new Autobus(id, marca, modelo, anno, costo, pasajeros);
                break;
            }
            default:
                System.out.println("Tipo no válido");

        }
        if (v != null) {
            admin.añadirVehiculo(v);
            System.out.println(" Vehículo añadido: " + v);
        }
    }

    private static void listarVehiculos() {
        admin.listarVehiculosDisponibles();
    }

    private static void reservarVehiculo() {
        System.out.println("---------Reserva---------");
        System.out.println("ID cliente: ");
        String idC = leer.nextLine();
        System.out.println("Nombre: ");
        String nombreC = leer.nextLine();
        Cliente cliente = clientes.stream()
                .filter(c -> c.getIdCliente().equals(idC))
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            cliente = new Cliente(idC, nombreC);
            clientes.add(cliente);
        }

        if (cliente.tieneReservaActiva()) {
            System.out.println("El cliente ya tiene una reserva activa y no puede hacer otra.");
            return;
        }

        listarVehiculos();
        System.out.println("Ingrese el ID del vehículo a reservar: ");
        String idV = leer.nextLine().trim();

        Vehiculo elegido = admin.getDisponibles().stream()
                .filter(v -> v.getIdVehiculo().equals(idV))
                .findFirst().orElse(null);

        if (elegido == null) {
            System.out.println("Vehículo no encontrado o no disponibles");
            return;
        }

        System.out.println("Días de alquiler");
        int dias = leer.nextInt();
        leer.nextLine();

        LocalDate inicio = LocalDate.now();
        LocalDate fin = inicio.plusDays(dias);

        System.out.print("¿Seguro? (s/n): ");
        boolean seguro = leer.nextLine().equalsIgnoreCase("s");
        System.out.print("¿GPS? (s/n): ");
        boolean gps = leer.nextLine().equalsIgnoreCase("s");

        try {
            Reserva reservaTemp = new Reserva(cliente, elegido, inicio, fin, seguro, gps);
            System.out.println("\n--- Resumen de reserva ---");
            System.out.println(reservaTemp);

            System.out.print("\n¿Confirmar reserva? (s/n): ");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                cliente.getReservas().add(reservaTemp);
                reservaTemp.confirmarReserva();
                System.out.println("Reserva confirmada.");
            } else {
                System.out.println("Reserva cancelada.");
            }

        } catch (IllegalStateException e) {
            System.out.println("Error al registrar la reserva: " + e.getMessage());
        }
    }

    private static void datosIniciales() {
        //Autos
        Vehiculo auto1 = new Auto("A1", "Toyota", "Corolla", 2022, 50.0, "Gasolina");
        Vehiculo auto2 = new Auto("A2", "Tesla", "Model 3", 2023, 120.0, "Eléctrico");
        Vehiculo auto3 = new Auto("A3", "Chevrolet", "Onix", 2021, 45.0, "Gasolina");

        //Motos
        Vehiculo moto1 = new Moto("M1", "Yamaha", "FZ", 2021, 30.0, 150);
        Vehiculo moto2 = new Moto("M2", "Kawasaki", "Ninja", 2022, 60.0, 600);
        Vehiculo moto3 = new Moto("M3", "Honda", "CBR500", 2020, 70.0, 500);
        Vehiculo moto4 = new Moto("M4", "Suzuki", "GN125", 2019, 25.0, 125);

        //Camionetas
        Vehiculo cam1 = new Camioneta("C1", "Ford", "Ranger", 2020, 80.0, 1000);
        Vehiculo cam2 = new Camioneta("C2", "Chevrolet", "Colorado", 2021, 90.0, 1200);
        Vehiculo cam3 = new Camioneta("C3", "Toyota", "Hilux", 2022, 95.0, 1100);

        //Autobuses
        Vehiculo bus1 = new Autobus("B1", "Mercedes", "Sprinter", 2019, 150.0, 20);
        Vehiculo bus2 = new Autobus("B2", "Volkswagen", "Crafter", 2021, 180.0, 25);
        Vehiculo bus3 = new Autobus("B3", "Scania", "Citywide", 2022, 220.0, 40);

        //Añadir a la flota
        admin.añadirVehiculo(auto1);
        admin.añadirVehiculo(auto2);
        admin.añadirVehiculo(auto3);

        admin.añadirVehiculo(moto1);
        admin.añadirVehiculo(moto2);
        admin.añadirVehiculo(moto3);
        admin.añadirVehiculo(moto4);

        admin.añadirVehiculo(cam1);
        admin.añadirVehiculo(cam2);
        admin.añadirVehiculo(cam3);

        admin.añadirVehiculo(bus1);
        admin.añadirVehiculo(bus2);
        admin.añadirVehiculo(bus3);

        //Clientes de prueba
        Cliente cliente1 = new Cliente("C1", "Melissa");
        Cliente cliente2 = new Cliente("C2", "Juan Pérez");
        Cliente cliente3 = new Cliente("C3", "Ana Gómez");

        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);

        //Reservas iniciales
        cliente1.reservarVehiculo(auto1, LocalDate.now(), LocalDate.now().plusDays(3), true, false);
        cliente2.reservarVehiculo(cam1, LocalDate.now(), LocalDate.now().plusDays(5), false, true);
        cliente3.reservarVehiculo(bus1, LocalDate.now(), LocalDate.now().plusDays(2), true, true);

        System.out.println("Datos iniciales cargados correctamente.");

    }
}