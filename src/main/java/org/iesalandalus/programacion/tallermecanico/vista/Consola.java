package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";
    private Consola() {}
    public static void mostrarCabecera(String mensaje){
        System.out.println(mensaje);
        String subrayado = String.format(String.format("%%0%dd", mensaje.length()), 0).replace('0', '-');
        System.out.println(subrayado);
    }
    public static void  mostrarMenu(){
        mostrarCabecera("Gestión de un taller mecánico.");
        for (Opcion opcion:Opcion.values()) {
            System.out.println(opcion);
        }
    }

    private static float leerReal(String mensaje){
        System.out.println(mensaje);
        return Entrada.real();
    }

    private static int leerEntero(String mensaje){
        System.out.println(mensaje);
        return Entrada.entero();
    }

    private static String leerCadena(String mensaje){
        System.out.println(mensaje);
        return Entrada.cadena();
    }

    static LocalDate leerFecha(String mensaje) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        LocalDate fecha = null;
        String fechaTexto = null;
        while (fecha == null) {
            System.out.println(mensaje);
            System.out.println("Por favor, introduce una fecha en el formato dd/MM/yyyy:");
            fechaTexto = Entrada.cadena();
            try {
                fecha = LocalDate.parse(fechaTexto, formatoFecha);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha no válida. Por favor, vuelve a intentarlo.");
            }
        }
        return fecha;
    }

    public static Opcion elegirOpcion(){
        int numeroOpcion;
        while (true) {
            System.out.print("Introduce el número de la opción: ");
            numeroOpcion = Entrada.entero();
            if (Opcion.esValida(numeroOpcion)) {
                return Opcion.opciones.get(numeroOpcion);
            } else {
                System.out.println("Opción no válida. Por favor, vuelve a intentarlo.");
            }
        }
    }

    public static Cliente leerCliente(){
        String nombre = leerCadena("Introduce un nombre válido: ");
        String dni = leerCadena("Introduce un DNI válido: ");
        String telefono = leerCadena("Introduce un teléfono válido: ");
        return new Cliente(nombre, dni, telefono);
    }

    public static Cliente leerClienteDni(){
        return Cliente.get(leerCadena("Introduce el DNI: "));
    }

    public static String leerNuevoNombre(){
        return leerCadena("Introduce el nuevo nombre del cliente:");
    }

    public static String leerNuevoTelefono(){
        return leerCadena("Introduce el nuevo teléfono del cliente:");
    }

    public static Vehiculo leerVehiculo(){
        String marca = leerCadena("Introduce una marca válida: ");
        String modelo = leerCadena("Introduce un modelo válido: ");
        String matricula = leerCadena("Introduce una matrícula válida: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Vehiculo leerVehiculoMatricula(){
        return Vehiculo.get(Consola.leerCadena("Introduce la matrícula: "));
    }

    public static Revision leerRevision(){
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio: ");
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    public static int leerHoras(){
        return leerEntero("Introduce el número de horas correspondientes: ");
    }

    public static float leerPrecioMaterial(){
        System.out.print("Introduce su correspondiente precio material: ");
        return Entrada.real();
    }

    public static LocalDate leerFechaCierre(){
        return leerFecha("Introduce la fecha de cierre de la revisión: ");
    }
}