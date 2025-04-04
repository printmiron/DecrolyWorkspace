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

        String getChNames = "SELECT * FROM producto";

        try (Connection connection = DataBaseManagerSQL.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(getChNames);) {
            while(dataSet.next()){

                int id = dataSet.getInt(1);
                String referencia = dataSet.getString(2);
                String nombre = dataSet.getString(3);
                String descripcion = dataSet.getString(4);
                int tipo = dataSet.getInt(5);
                int cantidad = dataSet.getInt(6);
                double precio = dataSet.getDouble(7);
                int descuento = dataSet.getInt(8);
                int iva = dataSet.getInt(9);
                boolean aplicarDto =dataSet.getBoolean(10);


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

        String sqlStatement = "SELECT * FROM producto where referencia = ?";

        try (Connection connection = DataBaseManagerSQL.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement);) {

            statement.setString(1, productoReferencia);
            ResultSet dataSet = statement.executeQuery();

            while(dataSet.next()){

                int id = dataSet.getInt(1);
                String referencia = dataSet.getString(2);
                String nombre = dataSet.getString(3);
                String descripcion = dataSet.getString(4);
                int tipo = dataSet.getInt(5);
                int cantidad = dataSet.getInt(6);
                double precio = dataSet.getDouble(7);
                int descuento = dataSet.getInt(8);
                int iva = dataSet.getInt(9);
                boolean aplicarDto =dataSet.getBoolean(10);

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

        String sqlStatement = "SELECT * FROM producto where tipo = ?";

        try (Connection connection = DataBaseManagerSQL.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement);) {

            statement.setString(1, productoTipo);
            ResultSet dataSet = statement.executeQuery();

            while(dataSet.next()){

                int id = dataSet.getInt(1);
                String referencia = dataSet.getString(2);
                String nombre = dataSet.getString(3);
                String descripcion = dataSet.getString(4);
                int tipo = dataSet.getInt(5);
                int cantidad = dataSet.getInt(6);
                double precio = dataSet.getDouble(7);
                int descuento = dataSet.getInt(8);
                int iva = dataSet.getInt(9);
                boolean aplicarDto =dataSet.getBoolean(10);

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

        String sqlStatement = "SELECT * FROM producto where cantidad = ?";

        try (Connection connection = DataBaseManagerSQL.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement);) {

            statement.setString(1, productoCantidad);
            ResultSet dataSet = statement.executeQuery();

            while(dataSet.next()){

                int id = dataSet.getInt(1);
                String referencia = dataSet.getString(2);
                String nombre = dataSet.getString(3);
                String descripcion = dataSet.getString(4);
                int tipo = dataSet.getInt(5);
                int cantidad = dataSet.getInt(6);
                double precio = dataSet.getDouble(7);
                int descuento = dataSet.getInt(8);
                int iva = dataSet.getInt(9);
                boolean aplicarDto =dataSet.getBoolean(10);

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

        String sqlStatement = "INSERT INTO producto (referencia, nombre, descripcion, tipo, cantidad, precio, descuento, iva, aplicarDto)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DataBaseManagerSQL.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement);) {

            statement.setString(1, producto.getReferencia());
            statement.setString(2, producto.getNombre());
            statement.setString(3, producto.getDescripcion());
            statement.setInt(4, producto.getTipo());
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

        String sqlStatement = "DELETE FROM producto where referencia = ?";

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

        String sqlStatement = "UPDATE producto set descripcion = ?, cantidad = ?, precio = ?, descuento = ?, aplicarDto = ? where id = ?";

        try (Connection connection = DataBaseManagerSQL.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement);) {

            statement.setString(1, producto.getDescripcion());
            statement.setInt(2, producto.getCantidad());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getDescuento());
            statement.setBoolean(5, producto.isAplicarDto());
            statement.setInt(6, producto.getId());

            response = statement.executeUpdate();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response;
    }






}
