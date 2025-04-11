package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class AccessDCuniverseSQL {



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
        public int registrarArticulo(Articulo a) {
            int response = -1;

            String sql = "INSERT INTO articulo (cod, titulo) VALUES (?, ?)";

            try (Connection connection = DataBaseManagerSQL.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, a.getCod());
                statement.setString(2, a.getTitulo());

                response = statement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return response;
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


        // 4) Alquilar
        public int alquilar(String dniCliente, int codArticulo) {
            int response = -1;

            String sql = "INSERT INTO alquiler (dni_cliente, cod_articulo, fecha_alquiler) VALUES (?, ?, NOW())";

            try (Connection connection = DataBaseManagerSQL.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, dniCliente);
                statement.setInt(2, codArticulo);

                response = statement.executeUpdate();

                // marcar artículo como alquilado
                String sqlUpdate = "UPDATE articulo SET is_alquilada = 1 WHERE cod = ?";

                try (PreparedStatement statement2 = connection.prepareStatement(sqlUpdate)) {
                    statement2.setInt(1, codArticulo);
                    statement2.executeUpdate();

                    response = statement2.executeUpdate();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return response;
        }

        // 5) Devolver
        public void devolver(int codArticulo) {
            String sql = "UPDATE alquiler SET fecha_devolucion = NOW() WHERE cod_articulo = ? AND fecha_devolucion IS NULL";

            try (Connection connection = DataBaseManagerSQL.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, codArticulo);
                ps.executeUpdate();

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
