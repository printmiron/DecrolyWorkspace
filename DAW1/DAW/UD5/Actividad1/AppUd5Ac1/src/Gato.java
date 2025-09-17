public class Gato {

    String nombre;
    int edad;

    public Gato(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static void validarNombre(String nombre) throws Exception {
        if (nombre.length() < 3) {
            throw new Exception("Nombre tiene que tener al menos 3 caracteres ");
        }
        System.out.println("Nombre del gato: " + nombre);
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public static void validarEdad(int edad) throws Exception {
        if (edad < 0) {
            throw new Exception("La edad no puede ser nagativo ");
        }
        System.out.println("Edad del gato: " + edad);
    }

    @Override
    public String toString() {
        return "Gato: [nombre=" + nombre + ", edad=" + edad + "]";
    }

    
    

}
