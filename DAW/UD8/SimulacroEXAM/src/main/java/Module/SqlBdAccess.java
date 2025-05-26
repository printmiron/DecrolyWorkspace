package Module;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class SqlBdAccess {

    private String dniOriginal;

    //acceder a todas las personas
    public List<Persona> getPersonas() {
        List<Persona> personas = new LinkedList<>();

        String prod = "SELECT * FROM articulo";

        try (Connection connection = SqlBdManager.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(prod);) {
            while(dataSet.next()){

                String nombre = dataSet.getString(1);
                String apellido = dataSet.getString(2);
                String dni = dataSet.getString(3);
                int edad = dataSet.getInt(4);
                String sexo = dataSet.getString(5);
                LocalDate fechaNacimiento = LocalDate.parse(dataSet.getString(6));
                String telefono = dataSet.getString(7);
                String correo = dataSet.getString(8);
                String direccion = dataSet.getString(9);


                Persona a = new Persona(nombre, apellido, dni, edad, sexo, fechaNacimiento, telefono, correo, direccion);
                personas.add(a);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return personas;
    }

    //insertar una persona
    public static void registrarPersona(Persona persona) {

        String sql = "INSERT INTO persona (nombre, apellido, dni, edad, sexo, fechaNacimiento, telefono, correo, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellido());
            statement.setString(3, persona.getDni());
            statement.setInt(4, persona.getEdad());
            statement.setString(5, persona.getSexo());
            statement.setDate(6, Date.valueOf(persona.getFechaNacimiento())) ;
            statement.setString(7, persona.getTelefono());
            statement.setString(8, persona.getCorreo());
            statement.setString(9, persona.getDireccion());
            statement.execute();



        } catch (SQLException e) {
            System.out.println("Error al registrar persona: " + e.getMessage());
        }
    }


    //editar persona
    public static void editarPersona(Persona persona, String dniOriginal) {

        String sql = "UPDATE persona SET nombre = ?, apellido = ?, dni = ?, edad = ?, sexo = ?, fechaNacimiento = ?, telefono = ?, correo = ?, direccion = ? WHERE dni = ?";

        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellido());
            statement.setString(3, persona.getDni());
            statement.setInt(4, persona.getEdad());
            statement.setString(5, persona.getSexo());
            statement.setDate(6, Date.valueOf(persona.getFechaNacimiento())) ;
            statement.setString(7, persona.getTelefono());
            statement.setString(8, persona.getCorreo());
            statement.setString(9, persona.getDireccion());
            statement.setString(10, dniOriginal);
            statement.executeUpdate();



        } catch (SQLException e) {
            System.out.println("Error al editar persona: " + e.getMessage());
        }
    }


    //editar persona
    public static void eliminarPersona(String dni) {

        String sql = "DELETE FROM persona WHERE dni = ?";

        try (Connection connection = SqlBdManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, dni);
            statement.executeUpdate();



        } catch (SQLException e) {
            System.out.println("Error al eliminar persona: " + e.getMessage());
        }
    }




}
