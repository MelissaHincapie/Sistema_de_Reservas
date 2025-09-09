package org.example;
import org.example.modelos.*;
import org.example.herencia.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;



public class Main {
    private static final Administrador admin = new Administrador();
    private static final Scanner leer = new Scanner(System.in);
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public static void main(String[] args) {
        datosIniciales();

        boolean salir = false;

        while (!salir) {
            System.out.println("\nIngresar como:");
            System.out.println("1. Cliente");
            System.out.println("2. Administrador");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            try {
                int rol = leer.nextInt();
                leer.nextLine();

                switch (rol) {
                    case 1:
                        menuCliente();
                        break;
                    case 2:
                        menuAdmin();
                        break;
                    case 3:
                        salir = true;
                        System.out.println("Gracias por utilizar nuestro sistema de reservas");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                leer.nextLine();
            }
        }
    }

    //Menu Cliente
    private static void menuCliente() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n ---------Gestión de Reservas---------");
            System.out.println("1. Ver lista de vehículos disponibles");
            System.out.println("2. Reservar");
            System.out.println("3. Salir");
            System.out.println("Elige una opción");

            try {
                int opcion = leer.nextInt();
                leer.nextLine();

                switch (opcion) {
                    case 1:
                        listarVehiculos();
                        break;
                    case 2:
                        reservarVehiculo();
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                leer.nextLine(); // Limpiar buffer
            }
        }
    }

    //Menu Admin
    private static void menuAdmin() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Añadir vehículo");
            System.out.println("2. Listar vehículos disponibles");
            System.out.println("3. Lista de reservas");
            System.out.println("4. Lista de flota completa");
            System.out.println("5. Salir");
            System.out.println("Elige una opción");

            try {
                int opcion = leer.nextInt();
                leer.nextLine();

                switch (opcion) {
                    case 1:
                        aniadirVehiculo();
                        break;
                    case 2:
                        listarVehiculos();
                        break;
                    case 3:
                        admin.listarReservas(clientes);
                        break;
                    case 4:
                        admin.listarTodosLosVehiculos();
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                leer.nextLine(); // Limpiar buffer
            }
        }
    }

    private static void aniadirVehiculo() {
        int tipo = 0;
        boolean tipoValido = false;
        while (!tipoValido) {
            System.out.println("Seleccione el tipo de vehículo:");
            System.out.println("1. Auto");
            System.out.println("2. Moto");
            System.out.println("3. Camioneta");
            System.out.println("4. Autobús");
            System.out.print("Opción: ");

            try {
                tipo = leer.nextInt();
                leer.nextLine();
                if (tipo >= 1 && tipo <= 4) {
                    tipoValido = true;
                } else {
                    System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                leer.nextLine();
            }
        }

        // Solicitar datos comunes
        String id = "";
        boolean idValido = false;
        while (!idValido) {
            System.out.print("ID (obligatorio): ");
            id = leer.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("Error: El ID es obligatorio. Por favor, ingrese un ID.");
                continue;
            }
            idValido = true;
        }

        System.out.print("Marca: ");
        String marca = leer.nextLine().trim();
        System.out.print("Modelo: ");
        String modelo = leer.nextLine().trim();

        int anno = 0;
        boolean annoValido = false;
        while (!annoValido) {
            System.out.print("Año: ");
            try {
                anno = leer.nextInt();
                leer.nextLine();
                if (anno > 1900 && anno <= LocalDate.now().getYear() + 1) {
                    annoValido = true;
                } else {
                    System.out.println("Año no válido. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                leer.nextLine();
            }
        }

        double costo = 0;
        boolean costoValido = false;
        while (!costoValido) {
            System.out.print("Costo diario: ");
            try {
                costo = leer.nextDouble();
                leer.nextLine();
                if (costo > 0) {
                    costoValido = true;
                } else {
                    System.out.println("El costo debe ser mayor a cero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                leer.nextLine();
            }
        }

        // Crear vehículo según tipo
        Vehiculo v = null;
        switch (tipo) {
            case 1: {
                System.out.print("Tipo de combustible: ");
                String combustible = leer.nextLine().trim();
                v = new Auto(id, marca, modelo, anno, costo, combustible);
                break;
            }
            case 2: {
                int cc = 0;
                boolean ccValido = false;
                while (!ccValido) {
                    System.out.print("Cilindrada (cc): ");
                    try {
                        cc = leer.nextInt();
                        leer.nextLine();
                        if (cc > 0) {
                            ccValido = true;
                        } else {
                            System.out.println("La cilindrada debe ser mayor a cero.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número válido.");
                        leer.nextLine();
                    }
                }
                v = new Moto(id, marca, modelo, anno, costo, cc);
                break;
            }
            case 3: {
                double carga = 0;
                boolean cargaValida = false;
                while (!cargaValida) {
                    System.out.print("Capacidad de carga (Kg): ");
                    try {
                        carga = leer.nextDouble();
                        leer.nextLine();
                        if (carga > 0) {
                            cargaValida = true;
                        } else {
                            System.out.println("La capacidad debe ser mayor a cero.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número válido.");
                        leer.nextLine();
                    }
                }
                v = new Camioneta(id, marca, modelo, anno, costo, carga);
                break;
            }
            case 4: {
                int pasajeros = 0;
                boolean pasajerosValido = false;
                while (!pasajerosValido) {
                    System.out.print("Capacidad de pasajeros: ");
                    try {
                        pasajeros = leer.nextInt();
                        leer.nextLine();
                        if (pasajeros > 0) {
                            pasajerosValido = true;
                        } else {
                            System.out.println("La capacidad debe ser mayor a cero.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número válido.");
                        leer.nextLine();
                    }
                }
                v = new Autobus(id, marca, modelo, anno, costo, pasajeros);
                break;
            }
        }

        if (v != null) {
            admin.aniadirVehiculo(v);
            System.out.println("Vehículo añadido exitosamente: " + v);
        } else {
            System.out.println("Error al añadir el vehículo.");
        }
    }

    private static void listarVehiculos() {
        admin.listarVehiculosDisponibles();
    }

    private static void reservarVehiculo() {
        System.out.println("\n--- Realizar Reserva ---");

        // Obtener o crear cliente
        System.out.print("ID del cliente: ");
        String idC = leer.nextLine().trim();
        System.out.print("Nombre del cliente: ");
        String nombreC = leer.nextLine().trim();

        Cliente cliente = clientes.stream()
                .filter(c -> c.getIdCliente().equals(idC))
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            cliente = new Cliente(idC, nombreC);
            clientes.add(cliente);
            System.out.println("Nuevo cliente registrado: " + nombreC);
        }

        // Verificar si tiene reserva activa
        if (cliente.tieneReservaActiva()) {
            System.out.println("El cliente ya tiene una reserva activa y no puede hacer otra.");
            return;
        }

        // Mostrar vehículos disponibles
        listarVehiculos();
        if (admin.getDisponibles().isEmpty()) {
            System.out.println("No hay vehículos disponibles para reservar.");
            return;
        }

        // Seleccionar vehículo
        System.out.print("Ingrese el ID del vehículo a reservar: ");
        String idV = leer.nextLine().trim();

        Vehiculo elegido = admin.getDisponibles().stream()
                .filter(v -> v.getIdVehiculo().equalsIgnoreCase(idV))
                .findFirst().orElse(null);

        if (elegido == null) {
            System.out.println("Vehículo no encontrado o no disponible.");
            return;
        }

        // Solicitar fechas
        LocalDate inicio = null;
        LocalDate fin = null;
        boolean fechasValidas = false;

        while (!fechasValidas) {
            try {
                System.out.print("Fecha de inicio (dd/MM/yyyy): ");
                String fechaInicioStr = leer.nextLine().trim();
                inicio = LocalDate.parse(fechaInicioStr, dateFormatter);

                System.out.print("Fecha de fin (dd/MM/yyyy): ");
                String fechaFinStr = leer.nextLine().trim();
                fin = LocalDate.parse(fechaFinStr, dateFormatter);

                if (inicio.isBefore(fin) && !inicio.isBefore(LocalDate.now())) {
                    fechasValidas = true;
                } else {
                    System.out.println("Error: La fecha de inicio debe ser hoy o posterior, y la fecha de fin debe ser posterior a la de inicio.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Use el formato dd/MM/yyyy.");
            }
        }

        // Solicitar servicios adicionales
        System.out.print("¿Incluir seguro? (s/n): ");
        boolean seguro = leer.nextLine().equalsIgnoreCase("s");
        System.out.print("¿Incluir GPS? (s/n): ");
        boolean gps = leer.nextLine().equalsIgnoreCase("s");

        try {
            Reserva reservaTemp = new Reserva(cliente, elegido, inicio, fin, seguro, gps);
            System.out.println("\n--- RESUMEN DE RESERVA ---");
            System.out.println(reservaTemp);

            System.out.print("\n¿Confirmar reserva? (s/n): ");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                cliente.getReservas().add(reservaTemp);
                reservaTemp.confirmarReserva();
                System.out.println("Reserva confirmada exitosamente.");
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
        admin.aniadirVehiculo(auto1);
        admin.aniadirVehiculo(auto2);
        admin.aniadirVehiculo(auto3);

        admin.aniadirVehiculo(moto1);
        admin.aniadirVehiculo(moto2);
        admin.aniadirVehiculo(moto3);
        admin.aniadirVehiculo(moto4);

        admin.aniadirVehiculo(cam1);
        admin.aniadirVehiculo(cam2);
        admin.aniadirVehiculo(cam3);

        admin.aniadirVehiculo(bus1);
        admin.aniadirVehiculo(bus2);
        admin.aniadirVehiculo(bus3);

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