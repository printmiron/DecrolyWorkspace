package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class AccessDCuniverseSQL {

    //acceder a todos los articulos
    public List<Articulo> getArticulos() {
        List<Articulo> productos = new LinkedList<>();

        String prod = "SELECT * FROM articulo";

        try (Connection connection = DataBaseManagerSQL.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(prod);) {
            while(dataSet.next()){

                String cod = dataSet.getString(1);
                String titulo = dataSet.getString(2);
                java.sql.Date sqlDate = dataSet.getDate(3);

                //convertir DATA de sql en LocalDate java
                LocalDate fecha_reg = sqlDate.toLocalDate();

                Articulo a =  new Articulo(cod, titulo, fecha_reg);
                productos.add(a);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }


        // 1) Crear y registrar VideoClub en la franquicia
        public int registrarVideoclub(VideoDaw vd) {
            int response = -1;

            String sql = "INSERT INTO videoclub (cif, direccion)" + " VALUES (?, ?)";

            try (Connection connection = DataBaseManagerSQL.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, vd.getCif());
                statement.setString(2, vd.getDireccion());
                statement.executeUpdate();

                response = statement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return response;
        }

        // 2) Registrar articulo (película o videojuego) en videoclub
        public static void registrarArticulo(Articulo articulo) {

            String sql = "INSERT INTO articulo (cod, titulo, fecha_registro) VALUES (?, ?, NOW())";

            try (Connection connection = DataBaseManagerSQL.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, articulo.getCod());
                statement.setString(2, articulo.getTitulo());

                statement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error al registrar artículo base: " + e.getMessage());
            }
        }

    public static void registrarPelicula(Pelicula peli) {

        String sql = "INSERT INTO pelicula (cod, genero, is_alquilada, fecha_alquiler) VALUES (?, ?, ?, ?)";

        try (Connection connection = DataBaseManagerSQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, peli.getCod());
            statement.setString(2, peli.getGenero().name());
            statement.setBoolean(3, false);//por defecto
            statement.setTimestamp(4, null);//por defecto

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al registrar película: " + e.getMessage());
        }
    }

    public static void registrarVideojuego(Videojuego juego) {

        String sql = "INSERT INTO videojuego (cod, genero, is_alquilada, fecha_alquiler) VALUES (?, ?, ?, ?)";

        try (Connection connection = DataBaseManagerSQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, juego.getCod());
            statement.setString(2, juego.getGeneroJuego().name());
            statement.setBoolean(3, false); //por defecto
            statement.setTimestamp(4, null); //por defecto

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al registrar videojuego: " + e.getMessage());
        }
    }


    // 3) Crear y registrar cliente en videoclub
        public int registrarCliente(Cliente c) {
            int response = -1;

            String sqlPersona = "INSERT INTO persona (dni, nombre, direccion, fecha_nacimiento) VALUES (?, ?, ?, ?)";
            String sqlCliente = "INSERT INTO cliente (dni, num_socio) VALUES (?, ?)";

            try (Connection connection = DataBaseManagerSQL.getConnection()) {

                PreparedStatement psPersona = connection.prepareStatement(sqlPersona);
                psPersona.setString(1, c.getDni());
                psPersona.setString(2, c.getNombre());
                psPersona.setString(3, c.getDireccion());
                psPersona.setDate(4, Date.valueOf(c.getFechaNacimiento()));
                psPersona.executeUpdate();

                PreparedStatement psCliente = connection.prepareStatement(sqlCliente);
                psCliente.setString(1, c.getDni());
                psCliente.setString(2, c.getNumSocio());
                psCliente.executeUpdate();

                response = psPersona.executeUpdate();
                response = psCliente.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return response;
        }


        //---------------------------------------------


    public int alquilar(String dniCliente, String codArticulo, boolean esPelicula) {
        int response = -1;

        String sql = "INSERT INTO alquileres (dni_cliente, cod_articulo, fecha_alquiler) VALUES (?, ?, NOW())";

        try (Connection connection = DataBaseManagerSQL.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {

            insertStatement.setString(1, dniCliente);
            insertStatement.setString(2, codArticulo);
            response = insertStatement.executeUpdate();

            // Aquí actualizamos la tabla correspondiente
            String tabla = esPelicula ? "pelicula" : "videojuego";
            String sqlUpdate = "UPDATE " + tabla + " SET is_alquilada = 1, fecha_alquiler = NOW() WHERE cod = ?";

            try (PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate)) {
                updateStatement.setString(1, codArticulo);
                updateStatement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error al alquilar: " + e.getMessage());
        }

        return response;
    }


    // 5) Devolver
        public int devolver(String dniCliente, String codArticulo, boolean esPelicula) {
            int response = -1;

            //verificar si el articulo esta alquilado
            String sqlVerficar = "SELECT is_alquilada FROM " + (esPelicula ? "pelicula" : "videojuego") + " WHERE cod = ?";

            try (Connection connection = DataBaseManagerSQL.getConnection();
                 PreparedStatement statementVerificar = connection.prepareStatement(sqlVerficar)) {

                statementVerificar.setInt(1, codArticulo);

                ResultSet rs = statementVerificar.executeQuery();

                if (rs.next()) {
                    boolean verificar = rs.getBoolean(1);

                    if (!verificar) {
                        System.out.println("Este articulo no esta alquilado");
                        return response;
                    }

                }









                // marcar artículo como disponible
                String sqlUpdate = "UPDATE articulo SET is_alquilada = 0 WHERE cod = ?";
                try (PreparedStatement ps2 = connection.prepareStatement(sqlUpdate)) {
                    ps2.setInt(1, codArticulo);
                    ps2.executeUpdate();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 6) Dar de baja cliente
        public void darDeBajaCliente(String dni) {
            String sql = "UPDATE cliente SET fecha_baja = NOW() WHERE dni = ?";

            try (Connection connection = DataBaseManagerSQL.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, dni);
                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 7) Dar de baja artículo
        public void darDeBajaArticulo(int cod) {
            String sql = "UPDATE articulo SET is_disponible = 0 WHERE cod = ?";

            try (Connection connection = DataBaseManagerSQL.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, cod);
                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

















































}
