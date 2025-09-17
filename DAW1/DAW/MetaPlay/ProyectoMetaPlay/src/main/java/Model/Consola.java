package Model;

public class Consola {
    private int id;
    private String nombre;
    private String empresaDesarrollo;

    public Consola(int id, String nombre, String empresaDesarrollo) {
        this.id = id;
        this.nombre = nombre;
        this.empresaDesarrollo = empresaDesarrollo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmpresaDesarrollo() {
        return empresaDesarrollo;
    }

    @Override
    public String toString() {
        return "Consola{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", empresaDesarrollo='" + empresaDesarrollo + '\'' +
                '}';
    }
}
