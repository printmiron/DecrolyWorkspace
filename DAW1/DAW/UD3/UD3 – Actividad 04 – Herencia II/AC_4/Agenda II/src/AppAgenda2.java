
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AppAgenda2 {
    public static void main(String[] args) throws Exception {
       
        Scanner scanner = new Scanner(System.in);

        Agenda agenda = new Agenda();

        String opcion;

        do { 
            System.out.println("1.Anadir contacto");
            System.out.println("2.Eliminar contacto");
            System.out.println("3.Indica el nombre del contacto saber si exite o no");
            System.out.println("4.Ver toda la agenda");
            System.out.println("5.Busca el contacto por nombre que devulve su posicion");
            System.out.println("6.Salir");

            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":

                    System.out.println("Que tipo de contaco anadir?");
                    System.out.println("1.Contacto persona");
                    System.out.println("2.Contacto empresa");
                    String num = scanner.nextLine();

                    if ("1".equals(num)) {
                        System.out.println("Introduce el nombre del contacto: ");
                        String nombreP = scanner.nextLine();

                        System.out.println("Introduce su numero de telfono: ");
                        String numeroTP = scanner.nextLine();

                        System.out.println("Introduce su cumpleanos (ej: 1998-12-02): ");
                        String cumple = scanner.nextLine();
                        LocalDate cumpleanos = LocalDate.parse(cumple, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        ContactoPersona p = new ContactoPersona(nombreP, numeroTP, cumpleanos);
                        agenda.anadirContacto(p);
                    }else if ("2".equals(num)){
                        System.out.println("Introduce el nombre del contacto: ");
                        String nombreE = scanner.nextLine();

                        System.out.println("Introduce su numero de telfono: ");
                        String numeroTE = scanner.nextLine();

                        System.out.println("Introduce la Pagina Web: ");
                        String paginaWeb = scanner.nextLine();

                        Contacto e = new ContactoEmpresa(nombreE, numeroTE, paginaWeb);
                        agenda.anadirContacto(e);
                    }

                       
                    break;
                    
                case "2":
                        System.out.println("Introduce el nombre del contacto: ");
                        String nombreEliminar = scanner.nextLine();

                        if (agenda.eliminarContacto(nombreEliminar)) {  
                            System.out.println("Contacto eliminado: " + nombreEliminar);
                        }else{
                            System.out.println("No se encontro contacto: " + nombreEliminar);
                        }

                    break;

                case "3":
                        System.out.println("Introduce el contaco para saber si existe o no (true o false): ");
                        String contacoExiste = scanner.nextLine();

                        if (agenda.existeContacto(contacoExiste)) {
                            System.out.println("Si existe contacto " + contacoExiste);
                        }else{
                            System.out.println("No existe");
                        }
                    break;

                case "4":
                        agenda.listarContactos();
                    break;

                case "5":
                        System.out.println("Introduce el contacto para buscar su poicion: ");
                        String contactoBusca = scanner.nextLine();

                        int buscaContacto = agenda.buscaContacto(contactoBusca);

                        if (buscaContacto == -1) {
                            System.out.println("No hay contactos");
                        }else{
                            System.out.println("El contacto es en la poicion: " + buscaContacto);
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
