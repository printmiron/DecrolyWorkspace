

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class AppArrayList {
    public static void main(String[] args) throws Exception {
        
        //1.
        Productos2 productoTeclados = new Productos2("Teclados", 2);
        Productos2 productoTV = new Productos2("TV", 5);
        Productos2 productoOrdenadores = new Productos2("Ordenadores", 3);
        Productos2 productoTelefonos  = new Productos2("Telefonos", 10);
        Productos2 productoMonitores  = new Productos2("Monitores", 2);
        
        //2.
        LinkedList <Productos2> p = new LinkedList<>();

        System.out.println("Lista antes de eliminar: ");
        //3.
        p.add(productoTeclados);
        p.add(productoTV);
        p.add(productoOrdenadores);
        p.add(productoTelefonos);
        p.add(productoMonitores);

        //4.
        Iterator <Productos2> itera = p.iterator();
        Productos2 cadaProducto;
        while (itera.hasNext()) {
            cadaProducto = itera.next();
            System.out.println(cadaProducto.toString());
        } 
        
        System.out.println("Lista despues de eliminar: ");

        //5.
        p.remove(productoTV);
        p.remove(productoTeclados);

        for (Productos2 producto : p) {
            System.out.println(producto);
        }
        
        Productos2 productoRaton  = new Productos2("Raton", 10);

        //6.
        int posMedio = p.size() / 2;

        p.add(posMedio, productoRaton);

        System.out.println("Lista despues de insertar un 'Raton' en medio de la lista: ");

        //7.
        
        for (Productos2 producto : p) {
            System.out.println(producto);
        }
        
        //8.
        System.out.println("Lista ordenada por el nombre: ");

        Collections.sort(p);

        for (Productos2 producto : p) {
            System.out.println(producto);
        }

        //9-
        System.out.println("Lista despues de eliminar todos los valores: ");

        p.clear();

        for (Productos2 producto : p) {
            System.out.println(producto);
        }








    }
}
