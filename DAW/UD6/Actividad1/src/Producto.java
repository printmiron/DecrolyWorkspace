public class Producto {

    private String codigo;
    private String nombre;
    private int cantidad;
    private double precio;
    
    public Producto(String codigo, String nombre, int cantidad, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Producto [codigo = " + codigo + ", nombre = " + nombre + ", cantidad = " + cantidad + ", precio = " + precio
                + "]";
    }

    

}
