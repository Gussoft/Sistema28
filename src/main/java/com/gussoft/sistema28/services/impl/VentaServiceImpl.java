package com.gussoft.sistema28.services.impl;

import com.gussoft.sistema28.config.Conexion;
import com.gussoft.sistema28.models.Venta;
import com.gussoft.sistema28.services.VentaService;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class VentaServiceImpl implements VentaService {

    private Connection connection = null;

    @Override
    public void agregarVenta(Venta obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_I_Venta(?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pidtipodocumento", obj.getStrIdTipoDocumento());
            statement.setString("pidcliente", obj.getStrIdCliente());
            statement.setString("pidempleado", obj.getStrIdEmpleado());
            statement.setString("pserie", obj.getStrSerieVenta());
            statement.setString("pnumero", obj.getStrNumeroVenta());
            statement.setDate("pfecha", new java.sql.Date(obj.getStrFechaVenta().getTime()));
            statement.setString("ptotalventa", obj.getStrTotalVenta());
            statement.setString("pdescuento", obj.getStrDescuentoVenta());
            statement.setString("psubtotal", obj.getStrSubTotalVenta());
            statement.setString("piva", obj.getStrIvaVenta());
            statement.setString("ptotalpagar", obj.getStrTotalPagarVenta());
            statement.setString("pestado", obj.getStrEstadoVenta());
            statement.execute();

            JOptionPane.showMessageDialog(null, "¡Venta Realizada con éxito!", "Mensaje del Sistema", 1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public void modificarVenta(String codigo, Venta obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_U_Venta(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pidventa", codigo);
            statement.setString("pidtipodocumento", obj.getStrIdTipoDocumento());
            statement.setString("pidcliente", obj.getStrIdCliente());
            statement.setString("pidempleado", obj.getStrIdEmpleado());
            statement.setString("pserie", obj.getStrSerieVenta());
            statement.setString("pnumero", obj.getStrNumeroVenta());
            statement.setDate("pfecha", new java.sql.Date(obj.getStrFechaVenta().getTime()));
            statement.setString("ptotalventa", obj.getStrTotalVenta());
            statement.setString("pdescuento", obj.getStrDescuentoVenta());
            statement.setString("psubtotal", obj.getStrSubTotalVenta());
            statement.setString("piva", obj.getStrIvaVenta());
            statement.setString("ptotalpagar", obj.getStrTotalPagarVenta());
            statement.setString("pestado", obj.getStrEstadoVenta());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        JOptionPane.showMessageDialog(null, "¡Venta Actualizada!", "Mensaje del Sistema", 1);
    }

    @Override
    public ArrayList<Venta> listarVenta() {
        ArrayList<Venta> ventas = new ArrayList<>();
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_Venta}");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Venta venta = new Venta();
                venta.setStrIdVenta(resultSet.getString("IdVenta"));
                venta.setStrTipoDocumento(resultSet.getString("TipoDocumento"));
                venta.setStrCliente(resultSet.getString("Cliente"));
                venta.setStrEmpleado(resultSet.getString("Empleado"));
                venta.setStrSerieVenta(resultSet.getString("Serie"));
                venta.setStrNumeroVenta(resultSet.getString("Numero"));
                venta.setStrFechaVenta(resultSet.getDate("Fecha"));
                venta.setStrTotalVenta(resultSet.getString("TotalVenta"));
                venta.setStrDescuentoVenta(resultSet.getString("Descuento"));
                venta.setStrSubTotalVenta(resultSet.getString("SubTotal"));
                venta.setStrIvaVenta(resultSet.getString("Iva"));
                venta.setStrTotalPagarVenta(resultSet.getString("TotalPagar"));
                venta.setStrEstadoVenta(resultSet.getString("Estado"));

                ventas.add(venta);
            }
            return ventas;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }

    @Override
    public ResultSet listarVentaPorParametro(String criterio, String busqueda) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_VentaPorParametro(?,?)}");
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
    public ResultSet obtenerUltimoIdVenta() {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_UltimoIdVenta()}");
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
    public ResultSet listarVentaPorFecha(String criterio, Date fechaini, Date fechafin, String doc) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_VentaPorFecha(?,?,?,?)}");
            statement.setString("pcriterio", criterio);
            statement.setDate("pfechaini", new java.sql.Date(fechaini.getTime()));
            statement.setDate("pfechafin", new java.sql.Date(fechafin.getTime()));
            statement.setString("pdocumento", doc);
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
    public void actualizarVentaEstado(String codigo, Venta obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_U_ActualizarVentaEstado(?,?)}");
            statement.setString("pidventa", codigo);
            statement.setString("pestado", obj.getStrEstadoVenta());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        JOptionPane.showMessageDialog(null, "¡Venta Anulada!", "Mensaje del Sistema", 1);
    }

    @Override
    public ResultSet listarVentaPorDetalle(String criterio, Date fechaini, Date fechafin) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_VentaPorDetalle(?,?,?)}");
            statement.setString("pcriterio", criterio);
            statement.setDate("pfechaini", new java.sql.Date(fechaini.getTime()));
            statement.setDate("pfechafin", new java.sql.Date(fechafin.getTime()));
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }

    @Override
    public ResultSet listarVentaMensual(String criterio, String fecha_ini, String fecha_fin) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_VentaMensual(?,?,?)}");
            statement.setString("pcriterio", criterio);
            statement.setString("pfecha_ini", fecha_ini);
            statement.setString("pfecha_fin", fecha_fin);
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }
}
