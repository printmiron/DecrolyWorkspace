
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class AppArrayList {
    public static void main(String[] args) throws Exception {
        
        Productos productoTeclados = new Productos("Teclados", 2);
        Productos productoTV = new Productos("TV", 5);
        Productos productoOrdenadores = new Productos("Ordenadores", 3);
        Productos productoTelefonos  = new Productos("Telefonos", 10);
        Productos productoMonitores  = new Productos("Monitores", 2);
        
        ArrayList <Productos> p = new ArrayList<>();

        System.out.println("Lista antes de eliminar: ");

        p.add(productoTeclados);
        p.add(productoTV);
        p.add(productoOrdenadores);
        p.add(productoTelefonos);
        p.add(productoMonitores);

        Iterator <Productos> itera = p.iterator();
        Productos cadaProducto;
        while (itera.hasNext()) {
            cadaProducto = itera.next();
            System.out.println(cadaProducto.toString());
        } 
        
        System.out.println("Lista despues de eliminar: ");

        p.remove(productoTV);
        p.remove(productoTeclados);

        for (Productos producto : p) {
            System.out.println(producto);
        }
        
        Productos productoRaton  = new Productos("Raton", 10);

        int posMedio = p.size() / 2;

        p.add(posMedio, productoRaton);

        System.out.println("Lista despues de insertar un 'Raton' en medio de la lista: ");

        for (Productos producto : p) {
            System.out.println(producto);
        }

        System.out.println("Lista ordenada por el nombre: ");

        Collection.sort(Productos::compareTo);











    }
}
