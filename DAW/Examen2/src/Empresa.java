
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Empresa {

    private String nombre;
    private String cif;
    private LocalDate fechaFundacion;

    List<Trabajador> trabajadoresRegistrados = new LinkedList<>();
    List<GerenteDep> gerentesRegistrados = new LinkedList<>();
    List<Director> directoresRegistrados = new LinkedList<>();

    public Empresa(String nombre, String cif, LocalDate fechaFundacion, List<Trabajador> trabajadoresRegistrados,
            List<GerenteDep> gerentesRegistrados, List<Director> directoresRegistrados) {
        this.nombre = nombre;
        this.cif = cif;
        this.fechaFundacion = fechaFundacion;
        this.trabajadoresRegistrados = trabajadoresRegistrados;
        this.gerentesRegistrados = gerentesRegistrados;
        this.directoresRegistrados = directoresRegistrados;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCif() {
        return cif;
    }

    public LocalDate getFechaFundacion() {
        return fechaFundacion;
    }

    public List<Trabajador> getTrabajadoresRegistrados() {
        return trabajadoresRegistrados;
    }

    public List<GerenteDep> getGerentesRegistrados() {
        return gerentesRegistrados;
    }

    public List<Director> getDirectoresRegistrados() {
        return directoresRegistrados;
    }

    //mi ideia es que un Gerente y un Director tmabien son trabajadores por eso si se elemina el trabajador que es un gerente o director, se elemina como trabajador y tambien como gerente o director.
    public boolean registrarTrabajador(Trabajador t) throws PersonaExisteExeption {
        for (Trabajador trabajadores : trabajadoresRegistrados) {
            if (trabajadores.getDni().equals(t.getDni())) {
                throw new PersonaExisteExeption("Persona con dni " + t.getDni() + " ya existe!");
            }
        }
        return trabajadoresRegistrados.add(t);
    }

    //comprobamos si hay un trabajador con dni igual lanzamos una exeption y si no lo regitramos como gerente
    public boolean registrarGerente(GerenteDep g) throws PersonaExisteExeption {
        for (Trabajador trabajadores : trabajadoresRegistrados) {
            if (trabajadores.getDni().equals(g.getDni())) {
                throw new PersonaExisteExeption("Persona con dni " + g.getDni() + " ya existe!");
            }
        }
        return gerentesRegistrados.add(g);
    }

    public boolean registrarDirector(Director d) throws PersonaExisteExeption {
        for (Trabajador trabajadores : trabajadoresRegistrados) {
            if (trabajadores.getDni().equals(d.getDni())) {
                throw new PersonaExisteExeption("Persona con dni " + d.getDni() + " ya existe!");
            }
        }
        return directoresRegistrados.add(d);
    }

    public boolean eliminarTrabajador(Trabajador t) {
        return trabajadoresRegistrados.remove(t);
    }

    @Override
    public String toString() {
        return "Empresa [nombre=" + nombre + ", cif=" + cif + ", fechaFundacion=" + fechaFundacion + "]";
    }


    public String mostrarInformacionTrabajadores(){
        return String.format("Infromacion de los trabajadores registrados: " + trabajadoresRegistrados.toString());
    }


    public String reunirEmpresa(){
        
        return String.format("");
    }


}
