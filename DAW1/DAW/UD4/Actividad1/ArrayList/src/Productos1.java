public class Productos1 implements Comparable<Productos1>{
    
    private String nombre;
    private int cantidad;
    
    public Productos1(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public int compareTo(Productos1 producto){
        return this.nombre.compareTo(producto.getNombre());
    }

    @Override
    public String toString() {
        return "Productos [nombre=" + nombre + ", cantidad=" + cantidad + "]";
    }

    
}
