package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class AccessDCuniverseSQL {



        // 1) Crear y registrar VideoClub en la franquicia
        public void registrarVideoclub(String nombre, String direccion) {
            String sql = "INSERT INTO videoclub (nombre, direccion) VALUES (?, ?)";

            try (Connection connection = DataBaseManagerSQL.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, nombre);
                ps.setString(2, direccion);
                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 2) Registrar artículo (película o videojuego) en videoclub
        public void registrarArticulo(String tipo, String titulo, int cod, int año, int precio, int edadMinima) {
            String sql = "INSERT INTO articulo (tipo, titulo, cod, año, precio, edad_minima) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection connection = DataBaseManagerSQL.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, tipo);
                ps.setString(2, titulo);
                ps.setInt(3, cod);
                ps.setInt(4, año);
                ps.setInt(5, precio);
                ps.setInt(6, edadMinima);
                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 3) Crear y registrar cliente en videoclub
        public void registrarCliente(String dni, String nombre, String direccion, LocalDate fechaNacimiento, String numSocio) {
            String sqlPersona = "INSERT INTO persona (dni, nombre, direccion, fecha_nacimiento) VALUES (?, ?, ?, ?)";
            String sqlCliente = "INSERT INTO cliente (dni, num_socio) VALUES (?, ?)";

            try (Connection connection = DataBaseManagerSQL.getConnection()) {

                PreparedStatement psPersona = connection.prepareStatement(sqlPersona);
                psPersona.setString(1, dni);
                psPersona.setString(2, nombre);
                psPersona.setString(3, direccion);
                psPersona.setDate(4, Date.valueOf(fechaNacimiento));
                psPersona.executeUpdate();

                PreparedStatement psCliente = connection.prepareStatement(sqlCliente);
                psCliente.setString(1, dni);
                psCliente.setString(2, numSocio);
                psCliente.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 4) Alquilar
        public void alquilar(String dniCliente, int codArticulo) {
            String sql = "INSERT INTO alquiler (dni_cliente, cod_articulo, fecha_alquiler) VALUES (?, ?, NOW())";

            try (Connection connection = DataBaseManagerSQL.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, dniCliente);
                ps.setInt(2, codArticulo);
                ps.executeUpdate();

                // marcar artículo como alquilado (opcional)
                String sqlUpdate = "UPDATE articulo SET is_alquilada = 1 WHERE cod = ?";
                try (PreparedStatement ps2 = connection.prepareStatement(sqlUpdate)) {
                    ps2.setInt(1, codArticulo);
                    ps2.executeUpdate();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
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
