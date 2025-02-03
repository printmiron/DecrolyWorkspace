import java.util.Scanner;

public class DawBank {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Pedir el nombre y el IBAN
        final String patronTitular = "[A-Z][a-z]+ [A-Z][a-z]+"; // Al menos Nombre y Apellido
        String titular = Utils.comprobarPatronRepetidamente(patronTitular, "Introduce su Nombre y Apellido: ");

        final String patronIBAN = "[A-Z]{2}[0-9]{22}";
        String iban = Utils.comprobarPatronRepetidamente(patronIBAN, "Introduce su IBAN (ejemplo: ES1234567890123456789012): ");

        // Crear la cuenta bancaria
        CuentaBancaria cuenta = new CuentaBancaria(iban, titular, 0);

        String opcion;

        // Menu
        do {
            System.out.println("1. Datos de la cuenta");
            System.out.println("2. IBAN");
            System.out.println("3. Titular");
            System.out.println("4. Saldo");
            System.out.println("5. Ingreso");
            System.out.println("6. Retirada");
            System.out.println("7. Movimientos");
            System.out.println("8. Salir");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println(cuenta.infoCuentaBancaria());
                    break;
                case "2":
                    System.out.println("IBAN: " + cuenta.getIBAN());
                    break;
                case "3":
                    System.out.println("Titular: " + cuenta.getTitular());
                    break;
                case "4":
                    System.out.println(cuenta.infoSaldo());
                    break;
                case "5":
                    System.out.print("Cuanto dinero quieres ingresar? ");
                    double ingreso = sc.nextDouble();
                    
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
                            System.out.println(mov.mostrarInfoMovimiento());
                        }
                    }
                    break;
                case "8":
                    System.out.println("Hasta luego");
                    break;
                default:
                    System.out.println("Opcion no valida, intenta de nuevo.");
            }
        } while (!"8".equals(opcion));

        sc.close();
    }
}
