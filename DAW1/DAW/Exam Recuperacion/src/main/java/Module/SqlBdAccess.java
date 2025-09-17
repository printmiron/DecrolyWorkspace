package Module;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class SqlBdAccess {



    public List<Paciente> getPacientes() {
        List<Paciente> pacientes = new LinkedList<>();

        String prod = "SELECT * FROM Paciente";

        try (Connection connection = SqlBdManager.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(prod);) {
            while(dataSet.next()){

                String nombre = dataSet.getString(1);
                String apellido = dataSet.getString(2);
                String dni = dataSet.getString(3);
                String telefono = dataSet.getString(4);
                String direccion = dataSet.getString(5);
                String correo = dataSet.getString(6);

                Paciente paciente = new Paciente(nombre, apellido, dni, telefono, direccion, correo);
                pacientes.add(paciente);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pacientes;
    }

    public static boolean existeDniPaciente(String dni) {
        String sql = "SELECT COUNT(*) FROM Paciente WHERE dni = ?";
        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, dni);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar dni: " + e.getMessage());
        }
        return false;
    }



    public static void registrarPaciente(Paciente paciente) {

        String sql = "INSERT INTO Paciente (dni, Nombre, Apellidos, Telefono, Direccion, Email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, paciente.getDni());
            statement.setString(2, paciente.getNombre());
            statement.setString(3, paciente.getApellido());
            statement.setString(4, paciente.getTelefono());
            statement.setString(5, paciente.getDireccion());
            statement.setString(6, paciente.getCorreo());
            statement.execute();



        } catch (SQLException e) {
            System.out.println("Error al registrar paciente: " + e.getMessage());
        }
    }

    public static boolean existeNccDoctor(String ncc) {
        String sql = "SELECT COUNT(*) FROM Doctor WHERE num_colegiado = ?";
        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, ncc);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar ncc: " + e.getMessage());
        }
        return false;
    }

    public static void registrarDoctor(Doctor doctor) {

        String sql = "INSERT INTO Doctor (dni, Nombre, Apellidos, Telefono, Direccion, Emai, fecha_alta, TipoConsulta_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, doctor.getNcc());
            statement.setString(2, doctor.getNombre());
            statement.setString(3, doctor.getApellido());
            statement.setString(4, doctor.getTelefono());
            statement.setString(5, doctor.getDireccion());
            statement.setString(6, doctor.getCorreo());
            statement.setTimestamp(7, java.sql.Timestamp.valueOf(doctor.getFechaAlta()));
            statement.setInt(8, doctor.getTipo().getId());
            statement.execute();



        } catch (SQLException e) {
            System.out.println("Error al registrar doctor: " + e.getMessage());
        }
    }


    public static void registrarConsulta(Consulta consulta) {

        String sql = "INSERT INTO Consulta (id, Observaciones, fecha, TipoConsulta_id, Paciente_dni, Doctor_num_colegiado, Doctor_TipoConsulta_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, consulta.getId());
            statement.setString(2, consulta.getObservaciones());
            statement.setString(3, consulta.getFecha().toString());
            statement.setInt(4, consulta.getTipo().getId());
            statement.setString(5, consulta.getPaciente().getDni());
            statement.setString(6, consulta.getDoctor().getNcc());
            statement.setInt(7, consulta.getDoctor().getTipo().getId());
            statement.execute();



        } catch (SQLException e) {
            System.out.println("Error al registrar paciente: " + e.getMessage());
        }
    }








}
