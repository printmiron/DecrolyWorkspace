
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class AppArrayList {
    public static void main(String[] args) throws Exception {
        
        //1.
        Productos1 productoTeclados = new Productos1("Teclados", 2);
        Productos1 productoTV = new Productos1("TV", 5);
        Productos1 productoOrdenadores = new Productos1("Ordenadores", 3);
        Productos1 productoTelefonos  = new Productos1("Telefonos", 10);
        Productos1 productoMonitores  = new Productos1("Monitores", 2);
        
        //2.
        ArrayList <Productos1> p = new ArrayList<>();

        System.out.println("Lista antes de eliminar: ");
        //3.
        p.add(productoTeclados);
        p.add(productoTV);
        p.add(productoOrdenadores);
        p.add(productoTelefonos);
        p.add(productoMonitores);

        //4.
        Iterator <Productos1> itera = p.iterator();
        Productos1 cadaProducto;
        while (itera.hasNext()) {
            cadaProducto = itera.next();
            System.out.println(cadaProducto.toString());
        } 
        
        System.out.println("Lista despues de eliminar: ");

        //5.
        p.remove(productoTV);
        p.remove(productoTeclados);

        for (Productos1 producto : p) {
            System.out.println(producto);
        }
        
        Productos1 productoRaton  = new Productos1("Raton", 10);

        //6.
        int posMedio = p.size() / 2;

        p.add(posMedio, productoRaton);

        System.out.println("Lista despues de insertar un 'Raton' en medio de la lista: ");

        //7.
        
        for (Productos1 producto : p) {
            System.out.println(producto);
        }
        
        //8.
        System.out.println("Lista ordenada por el nombre: ");

        Collections.sort(p);

        for (Productos1 producto : p) {
            System.out.println(producto);
        }

        //9-
        System.out.println("Lista despues de eliminar todos los valores: ");

        p.clear();

        for (Productos1 producto : p) {
            System.out.println(producto);
        }








    }
}
