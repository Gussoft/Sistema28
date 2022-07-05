package com.gussoft.sistema28.services.impl;

import com.gussoft.sistema28.config.Conexion;
import com.gussoft.sistema28.models.Producto;
import com.gussoft.sistema28.services.ProductoService;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoServiceImpl implements ProductoService {

    private Connection connection = null;

    @Override
    public void agregarProducto(Producto obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_I_Producto(?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pcodigo", obj.getStrCodigoProducto());
            statement.setString("pnombre", obj.getStrNombreProducto());
            statement.setString("pdescripcion", obj.getStrDescripcionProducto());
            statement.setString("pstock", obj.getStrStockProducto());
            statement.setString("pstockmin", obj.getStrStockMinProducto());
            statement.setString("ppreciocosto", obj.getStrPrecioCostoProducto());
            statement.setString("pprecioventa", obj.getStrPrecioVentaProducto());
            statement.setString("putilidad", obj.getStrUtilidadProducto());
            statement.setString("pestado", obj.getStrEstadoProducto());
            statement.setString("pidcategoria", obj.getStrIdCategoria());
            //statement.setString("pimagen",Producto.getStrImagen());

            statement.execute();

            JOptionPane.showMessageDialog(null, "¡Producto Agregado con éxito!", "Mensaje del Sistema", 1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public void modificarProducto(String codigo, Producto obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_U_Producto(?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pidproducto", codigo);
            statement.setString("pcodigo", obj.getStrCodigoProducto());
            statement.setString("pnombre", obj.getStrNombreProducto());
            statement.setString("pdescripcion", obj.getStrDescripcionProducto());
            statement.setString("pstock", obj.getStrStockProducto());
            statement.setString("pstockmin", obj.getStrStockMinProducto());
            statement.setString("ppreciocosto", obj.getStrPrecioCostoProducto());
            statement.setString("pprecioventa", obj.getStrPrecioVentaProducto());
            statement.setString("putilidad", obj.getStrUtilidadProducto());
            statement.setString("pestado", obj.getStrEstadoProducto());
            statement.setString("pidcategoria", obj.getStrIdCategoria());
            //statement.setString("pimagen",Producto.getStrImagen());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        JOptionPane.showMessageDialog(null, "¡Producto Actualizado!", "Mensaje del Sistema", 1);
    }

    @Override
    public void actualizarProductoStock(String codigo, Producto obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_U_ActualizarProductoStock(?,?)}");
            statement.setString("pidproducto", codigo);
            statement.setString("pstock", obj.getStrStockProducto());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public ArrayList<Producto> listarProducto() {
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_Producto}");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setStrIdProducto(resultSet.getString("IdProducto"));
                producto.setStrCodigoProducto(resultSet.getString("Codigo"));
                producto.setStrNombreProducto(resultSet.getString("Nombre"));
                producto.setStrDescripcionProducto(resultSet.getString("Descripcion"));
                producto.setStrStockProducto(resultSet.getString("Stock"));
                producto.setStrStockMinProducto(resultSet.getString("StockMin"));
                producto.setStrPrecioCostoProducto(resultSet.getString("PrecioCosto"));
                producto.setStrPrecioVentaProducto(resultSet.getString("PrecioVenta"));
                producto.setStrUtilidadProducto(resultSet.getString("Utilidad"));
                producto.setStrEstadoProducto(resultSet.getString("Estado"));
                producto.setStrDescripcionCategoria(resultSet.getString("categoria"));
//                producto.setStrImagen(resultSet.getString("imagen"));
                productos.add(producto);
            }
            return productos;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }

    @Override
    public ArrayList<Producto> listarProductoActivo() {
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_ProductoActivo}");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setStrIdProducto(resultSet.getString("IdProducto"));
                producto.setStrCodigoProducto(resultSet.getString("Codigo"));
                producto.setStrNombreProducto(resultSet.getString("Nombre"));
                producto.setStrDescripcionProducto(resultSet.getString("Descripcion"));
                producto.setStrStockProducto(resultSet.getString("Stock"));
                producto.setStrStockMinProducto(resultSet.getString("StockMin"));
                producto.setStrPrecioCostoProducto(resultSet.getString("PrecioCosto"));
                producto.setStrPrecioVentaProducto(resultSet.getString("PrecioVenta"));
                producto.setStrUtilidadProducto(resultSet.getString("Utilidad"));
                producto.setStrEstadoProducto(resultSet.getString("Estado"));
                producto.setStrDescripcionCategoria(resultSet.getString("categoria"));
                //producto.setStrImagen(resultSet.getString("imagen"));

                productos.add(producto);
            }
            return productos;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }

    @Override
    public ResultSet listarProductoPorParametro(String criterio, String busqueda) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_ProductoPorParametro(?,?)}");
            statement.setString("pcriterio", criterio);
            statement.setString("pbusqueda", busqueda);
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }

    @Override
    public ResultSet listarProductoActivoPorParametro(String criterio, String busqueda) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_ProductoActivoPorParametro(?,?)}");
            statement.setString("pcriterio", criterio);
            statement.setString("pbusqueda", busqueda);
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }

    @Override
    public ResultSet consultaStock() {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_ConsultaStock()}");
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }

    @Override
    public ResultSet kardexValorizado() {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_KardexValorizado()}");
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }

    @Override
    public ResultSet verificarCodigoBar(String busqueda) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_ProductoVerificarCodigoBar(?)}");
            statement.setString("pbusqueda", busqueda);
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }
}
