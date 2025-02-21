
//Modificar Aquiler peli devolver y reg cliente y baja cliente
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VideoDAW {

    private static int contador = 0;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private String nombre;
    private String cif;
    private String direccionCV;
    private LocalDate fechaAlta;

    private Pelicula[] peliculas;
    private Cliente[] clientes;
    private int PeliculasActuales = 0;
    private int ClientesActuales = 0;

    public VideoDAW(String cif, String direccionVC) {
        this.nombre = nombre;
        this.cif = cif;
        this.direccionCV = direccionCV;
        this.fechaAlta = LocalDate.now();
        this.peliculas = new Pelicula[100];
        this.clientes = new Cliente[100];
        this.PeliculasActuales = 0;
        this.ClientesActuales = 0;
    }

    public String getCif() {
        return this.cif;
    }

    public String getDireccionCV() {
        return this.direccionCV;
    }

    public LocalDate getFechaAlta() {
        return this.fechaAlta;
    }

    public Pelicula[] getPeliculas() {
        return this.peliculas;
    }

    public Cliente[] getClientes() {
        return this.clientes;
    }

    public String mostrarInfoVideoClub() {
        return String.format("CIF: %s, Direccion: %s, Fecha Alta: %s",
                this.cif, this.direccionCV, this.fechaAlta);
    }

    public String mostrarPeliculasRegistradas() {
        return String.format("Peliculas Registrados: %s /n", this.peliculas);
    }

    public String mostrarClientesRegistrados() {

        return String.format("Clientes Registrados: %s /n", this.clientes);
    }

    public boolean alquilarPelicula(Pelicula p, Cliente c) {
        if (p != null && c != null && !p.getAlquilada()) {
            p.setAlquilada(true);
            System.out.println("Pelicula alquilada a " + c.getNombre());
            return true;
        }
        return false;
    }

    public boolean devolverPelicula(Pelicula p, Cliente c) {
        if (p != null && c != null && p.getAlquilada()) {
            p.setAlquilada(false);
            System.out.println("Pelicula devuelta por " + c.getNombre());
            return true;
        }
        return false;
    }

    public boolean darBajaCliente(Cliente c) {
        for (int i = 0; i < ClientesActuales; i++) {
            if (clientes[i].equals(c)) {
                clientes[i] = null;
                ClientesActuales--;
                System.out.println("Cliente " + c.getNombre() + " ha sido dado de baja");
                return true;
            }
        }
        System.out.println("Cliente no encontrado");
        return false;
    }

    public boolean darBajaPelicula(Pelicula p) {
        for (int i = 0; i < PeliculasActuales; i++) {
            if (peliculas[i].equals(p)) {
                peliculas[i] = null;
                PeliculasActuales--;
                System.out.println("Pelicula " + p.getTitulo() + " ha sido dado de baja");
                return true;
            }
        }
        System.out.println("pelicula no encontrado");
        return false;
    }

    public boolean registrarCliente(Cliente c) {
        if (ClientesActuales < clientes.length) {
            clientes[ClientesActuales] = c;
            ClientesActuales++;
            System.out.println("Cliente " + c.getNombre() + " ha sido registrado");
            return true;
        }
        return false;
    }

    public boolean registrarPelicula(Pelicula p) {
        if (PeliculasActuales < peliculas.length) {
            peliculas[PeliculasActuales] = p;
            PeliculasActuales++;
            System.out.println("Pelicula " + p.getTitulo() + " ha sido registrada");
            return true;
        }
        return false;
    }

}
