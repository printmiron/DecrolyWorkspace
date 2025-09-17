
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static double leerDoublePantalla(String mensaje) {
        Scanner reader = new Scanner(System.in);
        System.out.println(mensaje);
        double valor = reader.nextDouble();
        reader.close();
        return valor;
    }

    public static void imprimir(String mensaje) {
        System.out.println(mensaje);
    }

    public static String leerTextoPantalla(String mensaje) {
        Scanner reader = new Scanner(System.in);
        System.out.println(mensaje);
        String valor = reader.nextLine();
        return valor;
    }

    public static boolean comprobarPatron(String patron, String texto) {
        boolean isOk = false;
        Pattern pat = Pattern.compile(patron);
        Matcher mat = pat.matcher(texto);
        isOk = mat.matches();
        return isOk;
    }

    public static String comprobarPatronRepetidamente(String patron, String mensaje) {
        boolean isOk = false;
        String texto;
        do {
            texto = Utils.leerTextoPantalla(mensaje);
            isOk = Utils.comprobarPatron(patron, texto);
            if (!isOk) {
                Utils.imprimir("El formato es incorrecto");
            }
        } while (!isOk);
        return texto;
    }
}
