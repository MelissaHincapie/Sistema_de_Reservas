package org.example;
import org.example.modelos.*;
import org.example.herencia.*;

import java.time.LocalDate;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Administrador admin = new Administrador();
    private static final Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;


        while(!salir){
            System.out.println("\n ---------Gestión de Reservas---------");
            System.out.println("1. Añadir vehículo");
            System.out.println("2. Listar vehículos disponibles");
            System.out.println("3. Resgitrar cliente y reservar");
            System.out.println("4. Salir");
            System.out.println("Elige una opción");

            int opcion = leer.nextInt();
            leer.nextLine();


            switch (opcion){
                case 1 : {
                    añadirVehiculo();
                    break;
                }
                case 2 : {
                    listarVehiculos();
                    break;
                }
//                case 3 : {
//                    reservarVehiculo();
//                    break;
//                }
                case 4 : {
                    salir = true;
                    break;
                }
                default : System.out.println("Opción inválida.");
            }
        }
        System.out.println("Gracias por utilizar nuestro sistema de reservas");
    }

    private static void añadirVehiculo(){
        System.out.println("---------Añadir vehículo---------");
        System.out.println("tipo : \n1. Auto \n2. Moto \n3. Camioneta \n4. Autobús");
        int tipo = leer.nextInt();
        leer.nextLine();

        System.out.print("ID: "); String id = leer.nextLine();
        System.out.print("Marca: "); String marca = leer.nextLine();
        System.out.print("Modelo: "); String modelo = leer.nextLine();
        System.out.print("Año: "); int anno = leer.nextInt();
        System.out.print("Costo diario: "); double costo = leer.nextDouble();
        leer.nextLine();

        Vehiculo v = null;
        switch (tipo){
            case 1 : {
                System.out.println("Tipo de conbustible:");
                String combustible = leer.nextLine();
                v = new Auto(id, marca, modelo, anno, costo, combustible);
                break;
            }
            case 2 : {
                System.out.println("Cilindrada (cc): ");
                int cc = leer.nextInt();
                v = new Moto(id, marca, modelo, anno, costo, cc);
                break;
            }
            case 3 :{
                System.out.println("Capacidad de carga (Kg): ");
                double carga= leer.nextDouble();
                v = new Camioneta(id, marca, modelo, anno, costo, carga);
                break;
            }
            case 4 : {
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
        System.out.println("---------Vehículos disponibles---------");
        for (Vehiculo v : admin.listarVehiculosDisponibles()){
            System.out.println(" - " + v);
        }
    }


}