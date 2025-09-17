
import java.util.Scanner;

public class AppUd5Ac1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Ejercicio1----------------------
        // try {
        //     System.out.println("Introduce un valor entero ");
        //     int num = scanner.nextInt();
        //     System.out.println("Valor introducido: " + num);
        // } catch (InputMismatchException e) {
        //     System.out.println("Valor introducido incorrecto");
        // }finally{
        //     scanner.close();
        // }

        //Ejercicio2-----------------------
        // try {
        //     System.out.println("Introduce primer valor");
        //     int num1 = scanner.nextInt();
        //     System.out.println("Introduce segundo valor");
        //     int num2 = scanner.nextInt();
        //     int resultado = num1/num2;
        //     System.out.println("Resultado: " + resultado);
        // } catch (InputMismatchException e) {
        //     System.out.println("Asegurate de introducir numeros entero.");
        // } catch (ArithmeticException e) {
        //     System.out.println("ERROR! No se puede dividir por cero.");   
        // } finally {
        //     scanner.close();
        // }

        //Ejercicio3--------------------------
        // double [] nums = new double[5];
        // int pos = 0;
        // do { 
        //     try {
        //         System.out.println("Introduce el valor ");
        //         nums[pos] = scanner.nextDouble();
        //         pos++;
        //     } catch (Exception e) {
        //         System.out.println("Tienes que introducir un numero valido");
        //         scanner.nextLine();
        //     }
        // } while (pos < nums.length);
        // for (int i = 0; i < nums.length; i++) {
        //     System.out.println("Posicion: " + (i+1) + ": " + nums[i]);
        // }

        //Ejercicio4--------------------------
        // do {
        //     try {
        //         System.out.print("Introduce un número: ");
        //         int numero = scanner.nextInt();
        //         try {
        //             ExceptionsClass.imprimePositivo(numero);
        //         } catch (Exception e) {
        //             System.out.println("Error: " + e.getMessage());
        //         }
        //         try {
        //             ExceptionsClass.imprimeNegativo(numero);
        //         } catch (Exception e) {
        //             System.out.println("Error: " + e.getMessage());
        //         }
        //         scanner.nextLine();
        //     } catch (Exception e) {
        //         System.out.println("Tienes que introducir un numero valido.");
        //         scanner.nextLine();
        //     }
        // } while (true);

        //Ejercicio5--------------------------
        // do {
        //     try {
        //         System.out.println("Introduce nombre del gato: ");
        //         String nombre = scanner.nextLine();
        //         try {
        //             Gato.validarNombre(nombre);
        //         } catch (Exception e) {
        //             System.out.println("Error: " + e.getMessage());
        //         }
        //         System.out.println("Introduce edad del gato: ");
        //         int edad = scanner.nextInt();
        //         try {
        //             Gato.validarEdad(edad);
        //         } catch (Exception e) {
        //             System.out.println("Error: " + e.getMessage());
        //         }
        //         scanner.nextLine();
        //     } catch (Exception e) {
        //         System.out.println("Tienes que introducir valor valido.");
        //         scanner.nextLine();
        //     }
        // } while (true);

        //Ejercicio6--------------------------
        //Para que se registre bien el gato tiene que los dos valores ser bien introducidos.
        // ArrayList<Gato> gatos = new ArrayList<>();
        // int cont = 0;

        // do {
        //     try {

        //         System.out.println("Introduce nombre del gato: ");
        //         String nombre = scanner.nextLine();

        //         try {
        //             Gato.validarNombre(nombre);
        //         } catch (Exception e) {
        //             System.out.println("Error: " + e.getMessage());
        //         }

        //         System.out.println("Introduce edad del gato: ");
        //         int edad = scanner.nextInt();

        //         try {
        //             Gato.validarEdad(edad);
        //         } catch (Exception e) {
        //             System.out.println("Error: " + e.getMessage());
        //         }

        //         scanner.nextLine();

        //         Gato g1 = new Gato(nombre, edad);
        //         gatos.add(g1);
        //         cont++;

        //     } catch (Exception e) {
        //         System.out.println("Tienes que introducir valor valido.");
        //         scanner.nextLine();
        //     }

        // } while (cont < 5);

        // System.out.println("Info de los gatos: ");
        // for (Gato gato : gatos) {
        //     System.out.println(gato);
        // }

    }
}
