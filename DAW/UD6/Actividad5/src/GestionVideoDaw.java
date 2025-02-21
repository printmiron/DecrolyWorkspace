
import java.util.Scanner;

public class GestionVideoDaw {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);

        String opcion = null;

        do { 

            System.out.println("1) Crear y registrar VideoClub en la franquicia");
            System.out.println("2) Registrar pelicula en videoclub");
            System.out.println("3) Crear y registrar cliente en videoclub");
            System.out.println("4) Alquilar");
            System.out.println("5) Devolver");
            System.out.println("6) Dar de baja cliente");
            System.out.println("7) Dar de baja articulo");
            System.out.println("8) Salir");

            switch (opcion) {
                case "1":
                    
                    break;
                default:
                    throw new AssertionError();
            }
            
        } while (!"8".equals(opcion));

    }
}
