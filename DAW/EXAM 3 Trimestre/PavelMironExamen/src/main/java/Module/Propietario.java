package Module;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Propietario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;

    public Propietario(String nombre, String apellido, String dni, String telefono, String direccion, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }

    public Propietario() {

    }

    public Propietario(ProprietarioBuilder proprietarioBuilder) {
        this.nombre = proprietarioBuilder.getNombre();
        this.apellido = proprietarioBuilder.getApellido();
        this.dni = proprietarioBuilder.getDni();
        this.telefono = proprietarioBuilder.getTelefono();
        this.direccion = proprietarioBuilder.getDireccion();
        this.email = proprietarioBuilder.getEmail();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return dni + " - " + nombre + " " + apellido;
    }


    public static ProprietarioBuilder builder() {
        return new ProprietarioBuilder();
    }

    private static List<Propietario> propietarios = new ArrayList<>();

    public static void cargarPropietariosDesdeBD(Connection connection) throws SQLException {
        String sql = "SELECT dni, Nombre, Apellido, Telefono, Direcion, Email FROM Propietario";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            propietarios.clear();
            while (rs.next()) {
                Propietario p = new Propietario(
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("dni"),
                        rs.getString("Telefono"),
                        rs.getString("Direcion"),
                        rs.getString("Email")
                );
                propietarios.add(p);
            }
        }
    }

    public static List<Propietario> getPropietarios() {
        return propietarios;
    }

    public static Propietario getPorDni(String dni) {
        return propietarios.stream().filter(p -> p.getDni().equals(dni)).findFirst().orElse(null);
    }




    public static Propietario obtenerPropietarioPorDni(String dni) {
        String sql = "SELECT * FROM Propietario WHERE dni = ?";
        try (Connection conn = SqlBdManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String telefono = rs.getString("telefono");
                    String direccion = rs.getString("direccion");
                    String email = rs.getString("email");

                    // Otros campos si existen
                    return new Propietario( nombre, apellido, dni, telefono, direccion, email);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al obtener propietario por DNI: " + e.getMessage());
        }
        return null;
    }





}
