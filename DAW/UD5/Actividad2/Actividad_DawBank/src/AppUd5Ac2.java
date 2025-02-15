
import java.util.ArrayList;
import java.util.Scanner;

public class AppUd5Ac2 {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Al menos Nombre y Apellido
        final String patronNombre = "[A-Z][a-z]+ [A-Z][a-z]+";
        String nombre = Utils.comprobarPatronRepetidamente(patronNombre, "Introduce su Nombre y Apellido (Pavel Miron): ");

        //Pide DNI: Y12345678K
        final String patronDni = "[0-9]{8}[A-Za-z]";
        String dni = Utils.comprobarPatronRepetidamente(patronDni, "Introduce su DNI (12345678K): ");

        //Pide fecha: Ejemplo 12-05-2025
        final String patronFecha = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-[0-9]{4}$";
        String fechaNacimiento = Utils.comprobarPatronRepetidamente(patronFecha, "Introduce su fecha de nacimiento (12-05-2025): ");

        //Pide telefono sin validar
        System.out.println("introudce su telefono: ");
        String telefono = sc.nextLine();

        //Pide email que acaba en @gmail.com
        final String patronEmail = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";//Ejemplo pavelmiron@gmail.com
        String email = Utils.comprobarPatronRepetidamente(patronEmail, "Introduce su email (pavelmiron@gmail.com): ");

        //Direccion sin validar
        System.out.println("introudce su direccion: ");
        String direccion = sc.nextLine();

        //Pide IBAN 
        final String patronIBAN = "[A-Z]{2}[0-9]{22}";
        String iban = Utils.comprobarPatronRepetidamente(patronIBAN, "Introduce su IBAN (ejemplo: ES1234567890123456789012): ");



        // Crear la cuenta bancaria
        CuentaBancaria cuenta = new CuentaBancaria(nombre, dni, fechaNacimiento, telefono, email, direccion, iban, 0, new ArrayList<>(), 0);

        String opcion;

        // Menu
        do {
            System.out.println("1. Datos de la cuenta");
            System.out.println("2. IBAN");
            System.out.println("3. Cliente");
            System.out.println("4. Saldo");
            System.out.println("5. Ingreso");
            System.out.println("6. Retirada");
            System.out.println("7. Movimientos");
            System.out.println("8. Salir");
            opcion = sc.nextLine();

            try {

                switch (opcion) {
                    case "1":
                        System.out.println(cuenta.infoCuentaBancaria());
                        break;
                    case "2":
                        System.out.println("IBAN: " + cuenta.getIban());
                        break;
                    case "3":
                        System.out.println("Cliente: " + cuenta.getCliente());
                        break;
                    case "4":
                        System.out.println(cuenta.infoSaldo());
                        break;
                    case "5":
                        System.out.print("Cuanto dinero quieres ingresar? ");
                        double ingreso = sc.nextDouble();
                        sc.nextLine();

                        Movimiento ingresoMov = new Movimiento("Ingreso", (int) ingreso);
                        cuenta.ingresarDinero(ingresoMov);
                        System.out.println("Ingreso realizado.");
                        break;
                    case "6":
                        System.out.print("Cuanto dinero quieres retirar? ");
                        double retiro = sc.nextDouble();

                        Movimiento retiroMov = new Movimiento("Retiro", (int) retiro);
                        if (cuenta.retirarDinero(retiroMov)) {
                            System.out.println("Retiro realizado.");
                        } else {
                            System.out.println("No se pudo realizar el retiro.");
                        }
                        break;
                    case "7":
                        System.out.println("Movimientos realizados:");
                        for (Movimiento mov : cuenta.getMovimientos()) {
                            if (mov != null) {
                                System.out.println(mov.toString());
                            }
                        }
                        break;
                    case "8":
                        System.out.println("Hasta luego");
                        break;
                    default:
                        System.out.println("Opcion no valida, intenta de nuevo.");
                }

            } catch (CuentaException e) {
                System.out.println("Error en la cuenta: " + e.getMessage());
                e.printStackTrace();
            } catch (AvisarHaciendaException e) {
                System.out.println("Aviso a la haceinda sobre su ingreso: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Se ha producido un error: " + e.getMessage());
                e.printStackTrace();
            }

        } while (!"8".equals(opcion));

        sc.close();
    }
}
