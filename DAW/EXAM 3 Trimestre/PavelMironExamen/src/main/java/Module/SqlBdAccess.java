package Module;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class SqlBdAccess {


    //acceder a todas las mascotas
    public List<Mascota> getMascotas() {
        List<Mascota> mascotas = new LinkedList<>();

        String prod = "SELECT * FROM Mascota";

        try (Connection connection = SqlBdManager.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(prod);) {
            while(dataSet.next()){

                String pasaporte = dataSet.getString(1);
                String nombre = dataSet.getString(2);
                LocalDateTime fechaNacimiento = LocalDateTime.parse(dataSet.getString(3));
                Double peso = dataSet.getDouble(4);

                String dniProrietario = dataSet.getString(5);
                Propietario propietario = Propietario.obtenerPropietarioPorDni(dniProrietario);


                int idTipo = dataSet.getInt(6);
                Tipo tipo = Tipo.getPorId(idTipo);


                Mascota mascota = new Mascota(pasaporte, nombre, fechaNacimiento, peso, tipo, propietario);
                mascotas.add(mascota);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mascotas;
    }


    //insertar una mascota
    public static void registrarMascota(Mascota mascota) {

        String sql = "INSERT INTO Mascota (Pasaporte, Nombre, Peso, FechaNacimiento, Propietario_dni, Tipo_idTipo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, mascota.getPasaporte());
            statement.setString(2, mascota.getNombre());
            statement.setDouble(3, mascota.getPeso());
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(mascota.getFechaNacimiento()));
            statement.setString(5, mascota.getPropietario().getDni());
            statement.setInt(6, mascota.getTipo().getIdTipo());
            statement.execute();



        } catch (SQLException e) {
            System.out.println("Error al registrar mascota: " + e.getMessage());
        }
    }



    //editar mascota
    public static void editarMascota(Mascota mascota, String pasaporte) {

        String sql = "UPDATE Mascota SET Nombre = ?, Peso = ? WHERE Pasaporte = ?";

        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

           statement.setString(1, mascota.getNombre());
           statement.setDouble(2, mascota.getPeso());
           statement.setString(3, pasaporte);
           statement.executeUpdate();



        } catch (SQLException e) {
            System.out.println("Error al editar persona: " + e.getMessage());
        }
    }




    //borrar una mascota
    public static void borrarMascota(String pasaporte) {

        String sql = "DELETE FROM Mascota WHERE Pasaporte = ?";

        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pasaporte);
            statement.execute();



        } catch (SQLException e) {
            System.out.println("Error al eliminar mascota: " + e.getMessage());
        }
    }























    //acceder a todas las proprietarios
    public List<Propietario> getPropietarios() {
        List<Propietario> propietarios = new LinkedList<>();

        String prod = "SELECT * FROM Propietario";

        try (Connection connection = SqlBdManager.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(prod);) {
            while(dataSet.next()){

                String dni = dataSet.getString(1);
                String nombre = dataSet.getString(2);
                String apellido = dataSet.getString(3);
                String telefono = dataSet.getString(4);
                String direccion = dataSet.getString(5);
                String email = dataSet.getString(6);


                Propietario propietario = new Propietario(dni, nombre, apellido, telefono, direccion, email);
                propietarios.add(propietario);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return propietarios;
    }







    //insertar un proprietario
    public static void registrarProprietario(Propietario propietario) {

        String sql = "INSERT INTO Propietario (dni, Nombre, Apellido, Telefono, Direcion, Email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, propietario.getDni());
            statement.setString(2, propietario.getNombre());
            statement.setString(3, propietario.getApellido());
            statement.setString(4, propietario.getTelefono());
            statement.setString(5, propietario.getDireccion());
            statement.setString(6, propietario.getEmail());
            statement.execute();



        } catch (SQLException e) {
            System.out.println("Error al registrar peroprietario: " + e.getMessage());
        }
    }


    //borrar un proprietario
    public static void borrarProprietario(String dni) {

        String sql = "DELETE FROM Propietario WHERE dni = ?";

        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, dni);
            statement.execute();



        } catch (SQLException e) {
            System.out.println("Error al eliminar proprietario: " + e.getMessage());
        }
    }















    //insertar una consulta
    public static void registrarConsulta(Consulta consulta) {

        String sql = "INSERT INTO Consulta (idConsulta, Fecha, Duracion, Observaciones, Mascota_Pasaporte, Mascota_Propietario_dni) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, consulta.getId());
            statement.setString(2, consulta.getFechaConsulta().toString());
            statement.setDouble(3, consulta.getDuracion());
            statement.setString(4, consulta.getObservaciones());
            statement.setString(5, consulta.getPasaporteConsulta().getPasaporte());
            statement.setString(6, consulta.getDniConsulta().getDni());

            statement.execute();



        } catch (SQLException e) {
            System.out.println("Error al registrar peroprietario: " + e.getMessage());
        }
    }



    //editar proprietarios
    public static void editarProrietarios(Propietario propietario, String dniOriginal) {

        String sql = "UPDATE Propietario SET dni = ?, Nombre = ?, Apellido = ?, Telefono = ?, Direcion = ?, Email = ? WHERE dni = ?";

        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

           statement.setString(1, propietario.getDni());
           statement.setString(2, propietario.getNombre());
           statement.setString(3, propietario.getApellido());
           statement.setString(4, propietario.getTelefono());
           statement.setString(5, propietario.getDireccion());
           statement.setString(6, propietario.getEmail());
           statement.setString(7, dniOriginal);
            statement.executeUpdate();



        } catch (SQLException e) {
            System.out.println("Error al editar proprietarios: " + e.getMessage());
        }
    }







}
