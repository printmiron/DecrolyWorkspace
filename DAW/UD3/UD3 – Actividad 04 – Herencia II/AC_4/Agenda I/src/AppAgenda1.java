
import java.util.Scanner;

public class AppAgenda1 {
    public static void main(String[] args) throws Exception {
       
        Scanner scanner = new Scanner(System.in);

        Agenda1 agenda = new Agenda1(null);

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
                        //crear el contacto
                        System.out.println("Introduce el nombre del contacto: ");
                        String nombre = scanner.nextLine();

                        System.out.println("Introduce su numero de telfono: ");
                        String numeroT = scanner.nextLine();

                        Contacto1 c = new Contacto1(nombre, numeroT);

                        //añdir el contacto en agenda con metodo creado en agenda
                        boolean anadirContacto = agenda.anadirContacto(c);

                        if (anadirContacto) {
                            System.out.println("El contacdo se registro en la agenda");
                        }else{
                            System.out.println("Contacto se pudo registrar en agenda");
                        }
                    break;
                    
                case "2":
                        System.out.println("Introduce el nombre del contacto: ");
                        String nombreEliminar = scanner.nextLine();

                        boolean eliminarContaco = agenda.eliminarContacto(nombreEliminar);

                        if (eliminarContaco) {
                            System.out.println(nombreEliminar + " ha sido eliminado");
                        }

                    break;

                case "3":
                        System.out.println("Introduce el contaco para saber si existe o no (true o false): ");
                        String contacoExiste = scanner.nextLine();

                        boolean existeContacto = agenda.existeContacto(contacoExiste);

                        if (existeContacto) {
                            System.out.println("Si existe contacto " + contacoExiste);
                        }else{
                            System.out.println("No existe " + contacoExiste);
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
