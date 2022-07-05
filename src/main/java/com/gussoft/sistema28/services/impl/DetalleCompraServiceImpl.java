package com.gussoft.sistema28.services.impl;

import com.gussoft.sistema28.config.Conexion;
import com.gussoft.sistema28.models.DetalleCompra;
import com.gussoft.sistema28.services.DetalleCompraService;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetalleCompraServiceImpl implements DetalleCompraService {

    private Connection connection = null;

    @Override
    public void agregarDetalleCompra(DetalleCompra obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_I_DetalleCompra(?,?,?,?,?)}");
            statement.setString("pidcompra", obj.getStrIdCompra());
            statement.setString("pidproducto", obj.getStrIdProducto());
            statement.setString("pcantidad", obj.getStrCantidadDet());
            statement.setString("pprecio", obj.getStrPrecioDet());
            statement.setString("ptotal", obj.getStrTotalDet());
            statement.execute();

            //JOptionPane.showMessageDialog(null,"¡Compra Realizada con éxito!","Mensaje del Sistema",1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public void modificarDetalleCompra(String codigo, DetalleCompra obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_U_DetalleCompra(?,?,?,?,?)}");
            statement.setString("pidcompra", codigo);
            statement.setString("pidproducto", obj.getStrIdProducto());
            statement.setString("pcantidad", obj.getStrCantidadDet());
            statement.setString("pprecio", obj.getStrPrecioDet());
            statement.setString("ptotal", obj.getStrTotalDet());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        JOptionPane.showMessageDialog(null, "¡Compra Actualizada!", "Mensaje del Sistema", 1);
    }

    @Override
    public ResultSet listarDetalleCompraPorParametro(String criterio, String busqueda) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_DetalleCompraPorParametro(?,?)}");
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
}
