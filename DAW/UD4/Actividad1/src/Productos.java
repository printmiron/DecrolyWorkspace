public class Productos {
    private String nombre;
    private int cantidad;
    
    public Productos(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public int compareTo(Productos oProductos){
        return this.nombre.compareTo(oProductos.getNombre());
    }

    @Override
    public String toString() {
        return "Productos [nombre=" + nombre + ", cantidad=" + cantidad + "]";
    }

    
}
