import java.util.Scanner;

public class AppTaller {
    public static void main(String[] args) throws Exception {
        
        Taller taller = new Taller(null);

        Scanner scanner = new Scanner(System.in);

        String opcion;

        do { 
            System.out.println("1.Anadir coche");
            System.out.println("2.Eliminar coche");
            System.out.println("3.Salir");
            

            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    System.out.println("Introduce el color del coche: ");
                    String color = scanner.nextLine();

                    System.out.println("Introduce la marca del coche: ");
                    String marca = scanner.nextLine();

                    System.out.println("Introduce la matricula del coche: ");
                    String matricula = scanner.nextLine();

                    Coche coche = new Coche(color, marca);
                    taller.anadeElemento(matricula, coche);

                    break;
                    
                case "2":
                    System.out.println("Introduce la matricula para eliminar el coche: ");
                    String eliminarCoche = scanner.nextLine();
                    taller.eliminaElemento(eliminarCoche);
                    break;

                case "3":
                    System.out.println("Hasta luego!");
                    break;
                default:   
            }
        } while (!"3".equals(opcion));

        System.out.println("Matriculas: ");
        taller.visualizaMatriculas();

        System.out.println("Coches: ");
        taller.visualizaCoches();

        System.out.println("Taller: ");
        taller.visualizaTaller();


    }
}
