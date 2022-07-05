package com.gussoft.sistema28.services.impl;

import com.gussoft.sistema28.config.Conexion;
import com.gussoft.sistema28.models.DetalleCompra;
import com.gussoft.sistema28.models.DetalleVenta;
import com.gussoft.sistema28.services.DetalleCompraService;
import com.gussoft.sistema28.services.DetalleVentaService;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetalleVentaServiceImpl implements DetalleVentaService {

    private Connection connection = null;

    @Override
    public void agregarDetalleVenta(DetalleVenta obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_I_DetalleVenta(?,?,?,?,?,?)}");
            statement.setString("pidventa", obj.getStrIdVenta());
            statement.setString("pidproducto", obj.getStrIdProducto());
            statement.setString("pcantidad", obj.getStrCantidadDet());
            statement.setString("pcosto", obj.getStrCostoDet());
            statement.setString("pprecio", obj.getStrPrecioDet());
            statement.setString("ptotal", obj.getStrTotalDet());
            statement.execute();

            //JOptionPane.showMessageDialog(null,"¡Venta Realizada con éxito!","Mensaje del Sistema",1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public void modificarDetalleVenta(String codigo, DetalleVenta obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_U_DetalleVenta(?,?,?,?,?,?)}");
            statement.setString("pidventa", codigo);
            statement.setString("pidproducto", obj.getStrIdProducto());
            statement.setString("pcantidad", obj.getStrCantidadDet());
            statement.setString("pcosto", obj.getStrCostoDet());
            statement.setString("pprecio", obj.getStrPrecioDet());
            statement.setString("ptotal", obj.getStrTotalDet());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public ResultSet listarDetalleVentaPorParametro(String criterio, String busqueda) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_DetalleVentaPorParametro(?,?)}");
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
