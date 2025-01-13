
import java.util.Scanner;

public class AppAgenda {
    public static void main(String[] args) throws Exception {
       
        Scanner scanner = new Scanner(System.in);

        Agenda agenda = new Agenda(null);

        String opcion;

        do { 
            System.out.println("1.Añadir contacto");
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

                        Contacto c = new Contacto(nombre, numeroT);

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
                        }else{
                            System.out.println("No se encontro el nombre " + nombreEliminar);
                        }

                    break;

                case "3":
                        
                    break;

                case "4":
                        
                    break;

                case "5":
                        
                    break;

                case "6":
                        
                    break;
                default:
                 
                
            }

        } while (!"6".equals(opcion));


    }
}
