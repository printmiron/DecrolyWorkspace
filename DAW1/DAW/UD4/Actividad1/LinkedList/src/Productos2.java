public class Productos2 implements Comparable<Productos2>{
    
    private String nombre;
    private int cantidad;
    
    public Productos2(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    @Override
    public int compareTo(Productos2 producto){
        return this.nombre.compareTo(producto.getNombre());
    }

    @Override
    public String toString() {
        return "Productos [nombre=" + nombre + ", cantidad=" + cantidad + "]";
    }

    
}
