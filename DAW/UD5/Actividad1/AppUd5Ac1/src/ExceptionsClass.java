public class ExceptionsClass {

public static void  imprimePositivo(int p) throws Exception {
    if (p < 0) {
        throw new Exception("Valor no positivo");
    }
    System.out.println("Valor positivo " + p);
}

public static void  imprimeNegativo(int n) throws Exception {
    if (n >= 0) {
        throw new Exception("Valor no negaativo");
    }
    System.out.println("Valor negativo " + n);
}

}
