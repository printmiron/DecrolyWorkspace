package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AccessDCuniverseSQL {

    //acceder a todos los productos
    public List<Producto> getProductos() {
        List<Producto> productos = new LinkedList<>();

        String prod = "SELECT * FROM Producto";

        try (Connection connection = DataBaseManagerSQL.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(prod);) {
            while(dataSet.next()){

                int id = dataSet.getInt(1);
                String referencia = dataSet.getString(2);
                String nombre = dataSet.getString(3);
                String descripcion = dataSet.getString(4);
                int tipoId = dataSet.getInt(5);
                int cantidad = dataSet.getInt(6);
                double precio = dataSet.getDouble(7);
                int descuento = dataSet.getInt(8);
                int iva = dataSet.getInt(9);
                boolean aplicarDto =dataSet.getBoolean(10);

                Tipo tipo = getTipoPorId(tipoId);
                Producto p =  new Producto(id, referencia, nombre, descripcion, tipo, cantidad, precio, descuento, iva, aplicarDto);
                productos.add(p);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }



    //encontrar producto por referencia
    public List<Producto> getProductoPorRef(String productoReferencia){
        List<Producto> productos = new LinkedList<>();

        String sqlStatement = "SELECT * FROM Producto where referencia = ?";

        try (Connection connection = DataBaseManagerSQL.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement);) {

            statement.setString(1, productoReferencia);
            ResultSet dataSet = statement.executeQuery();

            while(dataSet.next()){

                int id = dataSet.getInt(1);
                String referencia = dataSet.getString(2);
                String nombre = dataSet.getString(3);
                String descripcion = dataSet.getString(4);
                int tipoId = dataSet.getInt(5);
                int cantidad = dataSet.getInt(6);
                double precio = dataSet.getDouble(7);
                int descuento = dataSet.getInt(8);
                int iva = dataSet.getInt(9);
                boolean aplicarDto =dataSet.getBoolean(10);

                Tipo tipo = getTipoPorId(tipoId);
                Producto p =  new Producto(id, referencia, nombre, descripcion, tipo, cantidad, precio, descuento, iva, aplicarDto);
                productos.add(p);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }

    //encontrar producto por tipo
    public List<Producto> getProductoPorTipo(String productoTipo){
        List<Producto> productos = new LinkedList<>();

        String sqlStatement = "SELECT * FROM Producto where id_tipo = ?";

        try (Connection connection = DataBaseManagerSQL.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement);) {

            statement.setString(1, productoTipo);
            ResultSet dataSet = statement.executeQuery();

            while(dataSet.next()){

                int id = dataSet.getInt(1);
                String referencia = dataSet.getString(2);
                String nombre = dataSet.getString(3);
                String descripcion = dataSet.getString(4);
                int tipoId = dataSet.getInt(5);
                int cantidad = dataSet.getInt(6);
                double precio = dataSet.getDouble(7);
                int descuento = dataSet.getInt(8);
                int iva = dataSet.getInt(9);
                boolean aplicarDto =dataSet.getBoolean(10);

                Tipo tipo = getTipoPorId(tipoId);
                Producto p =  new Producto(id, referencia, nombre, descripcion, tipo, cantidad, precio, descuento, iva, aplicarDto);
                productos.add(p);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }

    //encontrar producto por cantidad
    public List<Producto> getProductoPorCant(String productoCantidad){
        List<Producto> productos = new LinkedList<>();

        String sqlStatement = "SELECT * FROM Producto where cantidad = ?";

        try (Connection connection = DataBaseManagerSQL.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement);) {

            statement.setString(1, productoCantidad);
            ResultSet dataSet = statement.executeQuery();

            while(dataSet.next()){

                int id = dataSet.getInt(1);
                String referencia = dataSet.getString(2);
                String nombre = dataSet.getString(3);
                String descripcion = dataSet.getString(4);
                int tipoId = dataSet.getInt(5);
                int cantidad = dataSet.getInt(6);
                double precio = dataSet.getDouble(7);
                int descuento = dataSet.getInt(8);
                int iva = dataSet.getInt(9);
                boolean aplicarDto =dataSet.getBoolean(10);

                Tipo tipo = getTipoPorId(tipoId);
                Producto p =  new Producto(id, referencia, nombre, descripcion, tipo, cantidad, precio, descuento, iva, aplicarDto);
                productos.add(p);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }


    //insertar producto
    public int insertarProducto(Producto producto) {
        int response = -1;

        String sqlStatement = "INSERT INTO Producto (referencia, nombre, descripcion, id_tipo, cantidad, precio, descuento, iva, aplicarDto)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DataBaseManagerSQL.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement);) {

            statement.setString(1, producto.getReferencia());
            statement.setString(2, producto.getNombre());
            statement.setString(3, producto.getDescripcion());
            statement.setInt(4, producto.getTipo().getId_tipo());
            statement.setInt(5, producto.getCantidad());
            statement.setDouble(6, producto.getPrecio());
            statement.setInt(7, producto.getDescuento());
            statement.setInt(8, producto.getIva());
            statement.setBoolean(9, producto.isAplicarDto());

            response = statement.executeUpdate();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response;
    }


    //eliminar producto
    public int eliminarProducto(String referencia) {
        int response = -1;

        String sqlStatement = "DELETE FROM Producto where referencia = ?";

        try (Connection connection = DataBaseManagerSQL.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement);) {

            statement.setString(1, referencia);

            response = statement.executeUpdate();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response;
    }


    //actualizar producto
    public int actualizarProducto(Producto producto) {
        int response = -1;

        String sqlStatement = "UPDATE Producto set descripcion = ?, cantidad = ?, precio = ?, descuento = ?, aplicarDto = ? where id = ?";

        try (Connection connection = DataBaseManagerSQL.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement);) {

            statement.setString(1, producto.getDescripcion());
            statement.setInt(2, producto.getCantidad());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getDescuento());
            statement.setBoolean(5, producto.isAplicarDto());
            statement.setInt(6, producto.getId());

            statement.executeUpdate();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response;
    }



















    // obtener todos los tipos
    public List<Tipo> getTipos() {
        List<Tipo> tipos = new LinkedList<>();
        String sqlStatement = "SELECT * FROM Tipo";

        try (Connection connection = DataBaseManagerSQL.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(sqlStatement);) {
            while(dataSet.next()){

                int id_tipo = dataSet.getInt(1);
                String nombre = dataSet.getString(2);

                Tipo t = new Tipo(id_tipo, nombre);
                tipos.add(t);
            }

        } catch (Exception e) {
            System.out.println("Error al obtener tipos: " + e.getMessage());
        }

        return tipos;
    }

    // obtener tipo por id
    public Tipo getTipoPorId(int idTipo) {
        Tipo tipo = null;
        String sqlStatement = "SELECT * FROM Tipo WHERE id_tipo = ?";

        try (Connection connection = DataBaseManagerSQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {

            statement.setInt(1, idTipo);
            ResultSet dataSet = statement.executeQuery();

            if (dataSet.next()) {
                tipo = new Tipo(dataSet.getInt(1), dataSet.getString(2));
            }

        } catch (Exception e) {
            System.out.println("Error al obtener tipo por ID: " + e.getMessage());
        }

        return tipo;
    }

    // insertar tipo
    public int insertarTipo(Tipo tipo) {
        int response = -1;
        String sqlStatement = "INSERT INTO Tipo (nombre) " + "VALUES (?)";

        try (Connection connection = DataBaseManagerSQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {

            statement.setString(1, tipo.getNombre());
            response = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al insertar tipo: " + e.getMessage());
        }

        return response;
    }




}
