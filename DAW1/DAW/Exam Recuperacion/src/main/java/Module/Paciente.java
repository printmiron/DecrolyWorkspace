package Module;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Paciente extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Consulta> consultas;
    private String nss;
    private LocalDateTime fechaAlta;

    public Paciente(String nombre, String apellido, String dni, String telefono, String direccion, String correo) {
        super(nombre, apellido, dni, telefono, direccion, correo);
    }

    public Paciente(String nombre, String apellido, String dni, String telefono, String direccion, String correo, List<Consulta> consultas, String nss, LocalDateTime fechaAlta) {
        super(nombre, apellido, dni, telefono, direccion, correo);
        this.consultas = consultas;
        this.nss = nss;
        this.fechaAlta = fechaAlta;
    }

    public Paciente() {
        super();
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        return nss + " - " + getNombre() + " " + getApellido();
    }


    public static PersonaBuilder builder() {
        return new PersonaBuilder();
    }



    private static List<Paciente> pacientes = new ArrayList<>();

    public static void cargarPacientesDesdeBD(Connection connection) throws SQLException {
        String sql = "SELECT dni, Nombre, Apellidos, Telefono, Direccion, Email FROM Paciente";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            pacientes.clear();
            while (rs.next()) {
                Paciente p = new Paciente(
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("dni"),
                        rs.getString("Telefono"),
                        rs.getString("Direcion"),
                        rs.getString("Email")
                );
                pacientes.add(p);
            }
        }
    }

    public static List<Paciente> getPacientes() {
        return pacientes;
    }


    public static Paciente getPorDni(String dni) {
        return pacientes.stream().filter(p -> p.getDni().equals(dni)).findFirst().orElse(null);
    }


//    public static Paciente obtenerPacientePorDni(String dni) {
//        String sql = "SELECT * FROM Paciente WHERE dni = ?";
//        try (Connection conn = SqlBdManager.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, dni);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    String nombre = rs.getString("nombre");
//                    String apellido = rs.getString("apellido");
//                    String telefono = rs.getString("telefono");
//                    String direccion = rs.getString("direccion");
//                    String email = rs.getString("email");
//
//                    // Otros campos si existen
//                    return new Paciente( nombre, apellido, dni, telefono, direccion, email);
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Error al obtener paciente por DNI: " + e.getMessage());
//        }
//        return null;
//    }

}
