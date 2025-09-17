import java.util.LinkedList;
import java.util.List;

public class GerenteDep extends Persona{

    private int numTrabajadores;
    private String numeroTel;

    List <Trabajador> trabajadores = new LinkedList<>();

   

    public GerenteDep(String nombre, String fechaNacimiento, String dni, String direccion, int numTrabajadores,
            String numeroTel) {
        super(nombre, fechaNacimiento, dni, direccion);
        this.numTrabajadores = numTrabajadores;
        this.numeroTel = numeroTel;
        
    }


    public int getNumTrabajadores() {
        return numTrabajadores;
    }


    public String getNumeroTel() {
        return numeroTel;
    }

    public List<Trabajador> getTrabajadores() {
        return trabajadores;
    }




    public String mostrarInfoDepartamento() {
        return super.toString() + "GerenteDep [numTrabajadores=" + numTrabajadores + ", numeroTel=" + numeroTel + ", trabajadores="
                + trabajadores + "]";
    }

    
}
