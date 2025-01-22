
import java.util.Scanner;

public class AppAgendaUd4 {
    public static void main(String[] args) throws Exception {
        
        aAgenda agenda = new aAgenda(null);

        Scanner scanner = new Scanner(System.in);

        String opcion;

        do { 
            System.out.println("1.Anadir coche");
            System.out.println("2.Eliminar coche");
            System.out.println("6.Salir");
            

            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    

                    //Coche coche = new Coche(color, marca);
                    //taller.anadeElemento(matricula, coche);

                    break;
                    
                case "2":
                   
                    break;

                case "3":
                   
                    break;

                case "4":
                   
                    break;

                case "5":
                   
                    break;

                case "6":
                    System.out.println("Hasta luego!");
                    break;
                default:   
            }
        } while (!"6".equals(opcion));

    }
}
