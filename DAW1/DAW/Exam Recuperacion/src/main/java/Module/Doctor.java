package Module;

import javax.print.Doc;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Module.Tipo;

public class Doctor extends Persona implements Serializable {
    private static final long serialVersionUID = 3L;

    private String ncc;
    private Tipo tipo;
    private LocalDateTime fechaAlta;

    public Doctor(String nombre, String apellido, String dni, String telefono, String direccion, String correo) {
        super(nombre, apellido, dni, telefono, direccion, correo);
    }

    public Doctor(String ncc, Tipo tipo, LocalDateTime fechaAlta) {
        this.ncc = ncc;
        this.tipo = tipo;
        this.fechaAlta = fechaAlta;
    }

    public Doctor() {
        super();
    }

    public Doctor(String ncc, String nombre, String apellido, String telefono, String direccion, String email, LocalDateTime fechaAlta, Tipo tipo) {
        super(nombre, apellido, telefono, direccion, email);
        this.ncc = ncc;
        this.tipo = tipo;
        this.fechaAlta = fechaAlta;
    }


    public String getNcc() {
        return ncc;
    }

    public void setNcc(String ncc) {
        this.ncc = ncc;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }


    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }


    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    public String toString() {
        return ncc + " - " + getNombre() + " " + getApellido();
    }

    public static PersonaBuilder builder() {
        return new PersonaBuilder();
    }



    private static List<Doctor> doctors = new ArrayList<>();

    public static void cargarDoctoresDesdeBD(Connection connection) throws SQLException {
        String sql = "SELECT num_colegiado, Nombre, Apellidos, Telefono, Direccion, fecha_alta, TipoConsulta_id FROM Doctor";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            doctors.clear();
            while (rs.next()) {
                    String ncc = rs.getString("num_colegiado");
                    String nombre = rs.getString("Nombre");
                    String apellido = rs.getString("Apellido");
                    String telefono = rs.getString("Telefono");
                    String direccion = rs.getString("Direccion");
                    String email = rs.getString("Email");
                    LocalDateTime fechaAlta = rs.getTimestamp("fecha_alta").toLocalDateTime();
                    int tipoId = rs.getInt("TipoConsulta_id");

                    Tipo tipo = Tipo.getPorId(tipoId);

                    Doctor doctor = new Doctor(ncc, nombre, apellido, telefono, direccion, email, fechaAlta, tipo);
                    doctors.add(doctor);

            }
        }
    }

    public static List<Doctor> getDoctors() {
        return doctors;
    }


    public static Doctor getPorNcc(String ncc) {
        return doctors.stream().filter(d -> d.getNcc().equals(ncc)).findFirst().orElse(null);
    }


//    public static Doctor obtenerDoctorPorNcc(String ncc) {
//        String sql = "SELECT * FROM Doctor WHERE num_colegiado = ?";
//        try (Connection conn = SqlBdManager.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, ncc);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//
//                    String nombre = rs.getString("Nombre");
//                    String apellido = rs.getString("Apellido");
//                    String telefono = rs.getString("Telefono");
//                    String direccion = rs.getString("Direccion");
//                    String email = rs.getString("Email");
//                    LocalDateTime fechaAlta = rs.getTimestamp("fecha_alta").toLocalDateTime();
//                    int tipoId = rs.getInt("TipoConsulta_id");
//
//                    Tipo tipo = Tipo.getPorId(tipoId);
//                    // Otros campos si existen
//                    return new Doctor(ncc, nombre, apellido, telefono, direccion, email, fechaAlta, tipo);
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Error al obtener paciente por DNI: " + e.getMessage());
//        }
//        return null;
//
//    }





}
