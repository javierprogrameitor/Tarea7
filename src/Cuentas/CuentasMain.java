package Cuentas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class CuentasMain {

    static CuentaBancaria[] cuenta; //Objeto de la clase abstracta CuentaBancaria.
    static int iCuenta = 0;   //Variable en la que almaceno el conjunto de objetos
    static String iban;  // Atributos estáticos necesarios para acceder a los métodos fuera del Main
    static Double saldo;
    static Calendar actual = Calendar.getInstance();

    public static void main(String[] args) throws Exception {

        cuenta = new CuentaBancaria[100];  // Creación del array CuentaBancaria

        String opciones;
        do {   // El Menu con sus opciones dentro del switch
            opciones = JOptionPane.showInputDialog("Menú\n1.Abrir una nueva cuenta \n2. "
                    + "Ver un listado de las cuentas disponibles\n3. " + "Obtener los datos de una cuenta concreta\n4."
                    + "Realizar un ingreso en una cuenta\n5. "
                    + "Retirar efectivo de una cuenta\n6. " + "Consultar el saldo actual de una cuenta\n7."
                    + "Salir de la aplicación ");
            switch (opciones) {  // LLamada a los métodos
                case "1":
                    abrirNueva(cuenta);
                    break;
                case "2":
                    verListado();
                    break;
                case "3":
                    obtenerDatosConcretos(iban);
                    break;
                case "4":
                    ingresoCuenta(iban, saldo);
                    break;
                case "5":
                    retiradaCuenta(iban, saldo);
                    break;
                case "6":
                    switch (obtenerSaldo(iban)) {
                        case -1:
                            System.out.println("Esta cuenta no existe");
                            break;
                        case 0:
                            break;
                    }
                    break;
                case "7":
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        } while (!opciones.equals("7"));
    }
    //Método para leer lo que le pedimos al usuario 

    public static String leer(String texto) throws Exception {
        //Método para leer los datos que introducimos por teclado.
        Scanner entrada = new Scanner(System.in);   //Lector de la clase Scanner
        String cadena;   // Variable para almacenar y devolver el resultado.
        System.out.println(texto);
        cadena = (entrada.nextLine());
        return cadena;
    }

    public static void abrirNueva(CuentaBancaria[] cuenta) throws Exception {

        Persona titular = new Persona(); // Creo un objeto de la clase persona en este método.
        titular.setNombre(leer(" Introduce su Nombre :"));// para los datos de la persona en cada cuenta.
        titular.setApellidos(leer(" Introduce sus Apellidos :"));
        String fecha_nacimiento = null;
        LocalDate fecha = null;

        do {  // Creo un bucle para validar el formato correcto de fecha nacimiento.           
            fecha_nacimiento = leer(" Introduce tu fecha de nacimiento en formato(dd-mm-yyyy) : ");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            fecha = LocalDate.parse(fecha_nacimiento, dtf);
            try {
                if (actual.get(Calendar.YEAR) >= fecha.getYear()) {
                    titular.setFecha_nacimiento(fecha_nacimiento);
                } else {
                    System.out.println("Año Incorrecto");
                    break;
                }  ///Si el año es incorrecto lo señala  pero lo inserta en la cuenta         
            } catch (Exception e) {
                e.getMessage();
            }
        } while (fecha == null);

        //Pregunto al usuario por cada tipo de cuenta y la creo en cada opción del switch.
        System.out.println("Tipo de cuenta que deseas abrir :");
        System.out.println("1. Cuenta de Ahorro  :");
        System.out.println("2. Cuenta Corriente Personal :");
        System.out.println("3. Cuenta Corrienta Empresa  :");

        String tipocuenta = leer(" "); //Variable para el switch y llamo al método leer

        CuentaBancaria nuevaCuenta = null;

        switch (tipocuenta) {
            //     Creo la Cuenta Ahorro
            case "1":
                System.out.println("--------------Cuenta de Ahorro------------------------:");
                do {  //Se debe hacer el bucle para que se valide correctamente el número de cuenta
                    iban = leer("Introduce el Número de Cuenta Bancaria ES + (20-dig): ");
                } while (!validarCCC(iban));
                saldo = Double.parseDouble(leer(" ¿Cual es el Saldo para Iniciar la cuenta ?"));
                Float interes = Float.parseFloat(leer(" Tipo de interes anual :"));
                nuevaCuenta = new CuentaAhorro(saldo, iban, titular, interes); // Constructor para la cuenta ahorro
                System.out.println(" Cuenta Ahorro Creada ");
                //
                break;
            // Fin de la CuentaAhorro.
            //                   Cuenta Corriente Personal
            case "2":
                System.out.println("----------------Cuenta Corriente Personal------------------:");
                do {  // Se tiene que repetir código para crear cada una de las cuentas.
                    iban = leer("Introduce el Número de Cuenta Bancaria ES + (20-dig): ");
                } while (!validarCCC(iban));
                saldo = Double.parseDouble(leer(" ¿Cual es el Saldo para Iniciar la cuenta ?"));
                Float com_mant = Float.parseFloat(leer(" ¿Comision de mantenimiento anual ?"));
                nuevaCuenta = new CuentaCorrientePersonal(saldo, iban, titular, com_mant);  // Constructor para la cuenta personal
                System.out.println(" Cuenta Corriente Personal Creada ");
                //
                break;
            // Fin de la CuentaCorrientePersonal
            //              Cuenta Corriente Empresa
            case "3":
                System.out.println("-----------------Cuenta Corriente Empresa-------------------:");
                do {
                    iban = leer("Introduce el Número de Cuenta Bancaria ES + (20-dig): ");
                } while (!validarCCC(iban));
                saldo = Double.parseDouble(leer(" ¿Cual es el Saldo para Iniciar la cuenta ?"));
                Float interes_desc = Float.parseFloat(leer(" ¿Interes descubierto ?"));
                Float maximo_desc = Float.parseFloat(leer(" ¿ Maximo descubierto ?"));
                Float comisi_desc = Float.parseFloat(leer("¿Comision descubierto ?"));
                nuevaCuenta = new CuentaCorrienteEmpresa(saldo, iban, titular, interes_desc, maximo_desc, comisi_desc);  // Constructor para la cuenta empresa
                System.out.println(" Cuenta Corriente Empresa Creada ");
                //
                break;
            //Fin de la CuentaCorrienteEmpresa
        }
        cuenta[iCuenta++] = nuevaCuenta;  // Introduzco en el array las cuentas creadas.
    }

    public static boolean validarCCC(String iban) {
        // Método para validar el -IBAN-. Devuelve true o false
        try {
            if (iban.matches("ES[0-9]{20}")) { // Cadena de caracteres para validación del -IBAN-
                return true;
            } else {
                System.out.println("IBAN incorrecto");
                return false;
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return true;
    }

    public static void verListado() {
//Este método permite ver el listado de las cuentas que hemos ingresado. Siempre dentro de un bucle para recorrer el array.
        try {
            System.out.println("---------------- listado de Cuentas----------------------------");
            for (int i = 0; i < iCuenta; i++) {
                System.out.println(" Código de Cuenta: "
                        + cuenta[i].getCCC() + " Titular: "
                        + cuenta[i].getTitular() + " Saldo Actual: "
                        + cuenta[i].getSaldo()
                        + cuenta[i].toString());
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    public static boolean obtenerDatosConcretos(String iban) {
//Método al que le paso el iban como me indican y dentro de un bucle for con la condición de que sean iguales los CCC se lista dicha cuenta
        try {
            System.out.println("---------Obtener Datos de una Cuenta Concreta-------------------------------- :");
            String CC = leer("Introduce el --IBAN-- de la Cuenta que quieres consultar  ES + (20-dig): ");
            for (int i = 0; i < iCuenta; i++) {  //Bucle para recorrer el array
                if (CC.equals(cuenta[i].getCCC())) {  // Condición para listar
                    System.out.println(" Código de Cuenta: "
                            + cuenta[i].getCCC() + " Titular: "
                            + cuenta[i].getTitular() + " Saldo Actual: "
                            + cuenta[i].getSaldo()
                            + cuenta[i].toString());
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        System.out.println("Esta cuenta no existe");  // Si no encuentra la condición te lanza el error
        return false;
    }

    public static boolean ingresoCuenta(String iban, Double saldo) {
//Método al que le paso el iban  y el saldo como me indican, dentro de un bucle for, con la condición de que sean iguales los CCC 
// y además sumo los saldos e imprimo el resulatdo
        try {
            System.out.println("---------Ingresar Efectivo en una Cuenta-------------------------------- :");
            String CC = leer("Introduce el --IBAN-- de la Cuenta que quieres ingresar  ES + (20-dig): ");
            Double cantidad = Double.parseDouble(leer(" ¿Que cantidad vas a ingresar? "));

            for (int i = 0; i < iCuenta; i++) { // Bucle
                if (CC.equals(cuenta[i].getCCC())) { // Condición
                    System.out.println(" Saldo antes de ingresar : " + cuenta[i].getSaldo());
                    cantidad = cantidad + cuenta[i].getSaldo();  // Sumo el nuevo saldo e imprimo, con la variable local cantidad.
                    System.out.println(" Actualmente tienes en saldo de : " + cantidad);
                    cuenta[i].setSaldo(cantidad);
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        System.out.println("Esta cuenta no existe");
        return false;
    }

    public static boolean retiradaCuenta(String iban, Double saldo) {
//Método al que le paso el iban  y el saldo como me indican, dentro de un bucle for, con la condición de que sean iguales los CCC 
// y además resto los saldos e imprimo el resulatdo
        try {
            System.out.println("---------Retirar Efectivo en una Cuenta-------------------------------- :");
            String CC = leer("Introduce el --IBAN-- de la Cuenta que quieres retirar  ES + (20-dig): ");
            Double cantidad = Double.parseDouble(leer(" ¿Que cantidad vas a retirar? "));
            for (int i = 0; i < iCuenta; i++) {
                if (CC.equals(cuenta[i].getCCC())) {
                    System.out.println(" Saldo antes de retirar el efectivo : " + cuenta[i].getSaldo());
                    cantidad = cuenta[i].getSaldo() - cantidad;  // Restostatic Calendar actual = Calendar.getInstance();  el nuevo saldo e imprimo, con la variable local cantidad.
                    System.out.println(" Actualmente tienes en saldo de : " + cantidad);
                    cuenta[i].setSaldo(cantidad);
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        System.out.println("Esta cuenta no existe");
        return false;
    }

    public static int obtenerSaldo(String iban) {
//Paso el iban dentro del bucle for, si son iguales los -IBAN- imprimo el saldo y  sino devuelvo un entero
// que imprime que no encuentra la cuenta.
        try {
            System.out.println("---------Obtener Saldo de una  Cuenta-------------------------------- :");
            String CC = leer("Introduce el --IBAN-- de la Cuenta para consultar su Saldo  ES + (20-dig): ");
            for (int i = 0; i < iCuenta; i++) {
                if (CC.equals(cuenta[i].getCCC())) {
                    System.out.println(" Su saldo es : " + cuenta[i].getSaldo());
                    return 0;
                }
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
        return -1;
    }

}
