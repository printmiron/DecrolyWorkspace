
import java.util.Scanner;

public class AppAgendaUd4 {

    public static void main(String[] args) throws Exception {

        aAgenda agenda = new aAgenda(null);

        Scanner scanner = new Scanner(System.in);

        String opcion;

        do {
            System.out.println("1.Anadir contacto");
            System.out.println("2.Buscar contacto");
            System.out.println("3.Eliminar contacto");
            System.out.println("4.Visualizar agenda");
            System.out.println("5.Numero de contactos de mi agenda");
            System.out.println("6.Salir");

            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":

                    //Validar telefono
                    String telefono;

                    do {
                        System.out.println("Introduce el telefono: ");
                        telefono = scanner.nextLine();

                        if (!cContacto.validarTelefono(telefono)) {
                            System.out.println("Telefono no es valido");
                        }

                    } while (!cContacto.validarTelefono(telefono));

                    //Validar correo
                    String correo;
                    do {
                        System.out.println("Introduce el correo: ");
                        correo = scanner.nextLine();

                        if (!cContacto.validarCorreo(correo)) {
                            System.out.println("Correo no es valido");
                        }

                    } while (!cContacto.validarCorreo(correo));

                    //Validar nombre
                    String nombre;
                    do {
                        System.out.println("Introduce su Nombre: ");
                        nombre = scanner.nextLine();

                        if (!cContacto.validarNombre(nombre)) {
                            System.out.println("Nombre no valido");
                        }

                    } while (!cContacto.validarNombre(nombre));

                    cContacto contactos = new cContacto(nombre, telefono, correo);
                    agenda.anadeContacto(nombre, contactos);

                    break;

                case "2":

                    System.out.println("Introduce el nombre para buscar el contacto: ");
                    String nombreBuscar = scanner.nextLine();

                    agenda.buscaContacto(nombreBuscar);

                    break;

                case "3":

                    System.out.println("Introduce el nombre para eliminar el contacto: ");
                    String nombreEliminar = scanner.nextLine();

                    agenda.eliminaContacto(nombreEliminar);

                    break;

                case "4":

                    agenda.visualizaAgenda();

                    break;

                case "5":

                    
                    int tamano = agenda.size();

                    if (tamano == 0) {
                        System.out.println("No hay contactos en agenda");
                    }else{
                        System.out.println(tamano + " - contactos hay en agenda");
                    }

                    break;

                case "6":
                    System.out.println("Hasta luego!");
                    break;
                default:
            }
        } while (!"6".equals(opcion));

    }
}
