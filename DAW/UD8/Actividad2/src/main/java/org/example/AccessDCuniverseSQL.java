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

    //acceder a todos los clientes
    public List<Cliente> getClientes() {
        List<Cliente> clientes = new LinkedList<>();

        String prod = "SELECT * FROM cliente";

        try (Connection connection = DataBaseManagerSQL.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(prod);) {
            while(dataSet.next()){

                String dni = dataSet.getString(1);
                String num_socio = dataSet.getString(2);


                Cliente c = new Cliente(dni, num_socio);
                clientes.add(c);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return clientes;
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
                System.out.println("Error al registrar articulo base: " + e.getMessage());
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
            System.out.println("Error al registrar pelicula: " + e.getMessage());
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

    // 4) alquilar articulo
    public int alquilar(String dniCliente, String codArticulo, boolean esPelicula) {
        int response = -1;

        // verificar si el cliente esta dado de baja
        String verificarClienteSQL = "SELECT fecha_baja FROM cliente WHERE dni = ?";
        try (Connection connection = DataBaseManagerSQL.getConnection();
             PreparedStatement psCliente = connection.prepareStatement(verificarClienteSQL)) {

            psCliente.setString(1, dniCliente);
            ResultSet rsCliente = psCliente.executeQuery();

            if (rsCliente.next() && rsCliente.getDate("fecha_baja") != null) {
                System.out.println("El cliente esta dado de baja y no puede alquilar articulos.");
                return response;
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar cliente: " + e.getMessage());
        }

        // verificar si el articulo está dado de baja
        String verificarArticuloSQL = "SELECT fecha_baja, is_disponible FROM articulo WHERE cod = ?";
        try (Connection connection = DataBaseManagerSQL.getConnection();
             PreparedStatement psArticulo = connection.prepareStatement(verificarArticuloSQL)) {

            psArticulo.setString(1, codArticulo);
            ResultSet rsArticulo = psArticulo.executeQuery();

            if (rsArticulo.next() && rsArticulo.getDate("fecha_baja") != null || rsArticulo.getBoolean("is_disponible") == false) {
                System.out.println("el articulo esta dado de baja o no esta disponible para alquiler.");
                return response;
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar articulo: " + e.getMessage());
        }


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


    // 5) devolver
    public int devolver(String dniCliente, String codArticulo, boolean esPelicula) {
        int response = -1;

        // Verifica si el artículo está alquilado
        String sqlVerificar = "SELECT is_alquilada FROM " + (esPelicula ? "pelicula" : "videojuego") + " WHERE cod = ?";

        try (Connection connection = DataBaseManagerSQL.getConnection();
             PreparedStatement verificarStatement = connection.prepareStatement(sqlVerificar)) {

            verificarStatement.setString(1, codArticulo);
            ResultSet rs = verificarStatement.executeQuery();

            if (rs.next()) {
                boolean isAlquilada = rs.getBoolean(1);

                if (!isAlquilada) {
                    System.out.println("Este articulo no esta alquilado.");
                    return response; // no es posible devolver un artículo que no esta alquilado
                }


                String sqlActualizar = "UPDATE " + (esPelicula ? "pelicula" : "videojuego") +
                        " SET is_alquilada = 0, fecha_alquiler = NULL WHERE cod = ?";

                try (PreparedStatement actualizarStatement = connection.prepareStatement(sqlActualizar)) {
                    actualizarStatement.setString(1, codArticulo);
                    response = actualizarStatement.executeUpdate();

                    // También puedes registrar la devolución en la tabla "alquileres" si es necesario
                    String sqlDevolucion = "UPDATE alquileres SET fecha_devolucion = NOW() WHERE dni_cliente = ? AND cod_articulo = ? AND fecha_devolucion IS NULL";

                    try (PreparedStatement devolucionStatement = connection.prepareStatement(sqlDevolucion)) {
                        devolucionStatement.setString(1, dniCliente);
                        devolucionStatement.setString(2, codArticulo);
                        devolucionStatement.executeUpdate();
                    }

                } catch (SQLException e) {
                    System.out.println("Error al devolver el articulo: " + e.getMessage());
                }
            } else {
                System.out.println("Error: No se encontro el articulo.");
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar el artículo: " + e.getMessage());
        }

        return response;
    }


    // 6) Dar de baja articulo
        public void darDeBajaArticulo(String codArticulo) {

            String verificarAlquilerSql = "SELECT COUNT(*) FROM alquileres WHERE cod_articulo = ? AND fecha_devolucion IS NULL";

            try (Connection connection = DataBaseManagerSQL.getConnection();
                 PreparedStatement verificarStatement = connection.prepareStatement(verificarAlquilerSql)) {

                verificarStatement.setString(1, codArticulo);
                ResultSet rs = verificarStatement.executeQuery();

                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("El articulo esta alquilado y no puede ser dado de baja hasta ser devuelto.");
                    return;
                }

                // si el articulo no esta alquilado, proceder a dar de baja
                String sql = "UPDATE articulo SET fecha_baja = NOW() WHERE cod = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, codArticulo);
                    statement.executeUpdate();

                    //  si es una pelicula o videojuego, se actualiza la tabla correspondiente
                    String sqlActualizar = "UPDATE pelicula SET is_alquilada = 0 WHERE cod = ?";
                    try (PreparedStatement actualizarStatement = connection.prepareStatement(sqlActualizar)) {
                        actualizarStatement.setString(1, codArticulo);
                        actualizarStatement.executeUpdate();
                    }

                    String sqlActualizarVideojuego = "UPDATE videojuego SET is_alquilada = 0 WHERE cod = ?";
                    try (PreparedStatement actualizarStatement = connection.prepareStatement(sqlActualizarVideojuego)) {
                        actualizarStatement.setString(1, codArticulo);
                        actualizarStatement.executeUpdate();
                    }

                    System.out.println("Articulo dado de baja correctamente.");
                }

            } catch (SQLException e) {
                System.out.println("Error al dar de baja el articulo: " + e.getMessage());
            }
        }

        // 7) Dar de baja artículo
        public void darDeBajaCliente(String dni) {
            // verificar si el cliente tiene alquileres activos antes de dar de baja
            String verificarAlquileresSql = "SELECT COUNT(*) FROM alquileres WHERE dni_cliente = ? AND fecha_devolucion IS NULL";

            try (Connection connection = DataBaseManagerSQL.getConnection();
                 PreparedStatement verificarStatement = connection.prepareStatement(verificarAlquileresSql)) {

                verificarStatement.setString(1, dni);
                ResultSet rs = verificarStatement.executeQuery();

                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("El cliente tiene alquileres activos y no puede ser dado de baja hasta devolver los articulos.");
                    return;
                }

                // no tiene alquileres activos, proceder a dar de baja
                String sql = "UPDATE cliente SET fecha_baja = NOW() WHERE dni = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, dni);
                    statement.executeUpdate();
                    System.out.println("Cliente dado de baja correctamente.");
                }

            } catch (SQLException e) {
                System.out.println("Error al dar de baja el cliente: " + e.getMessage());
            }
        }


















































}
