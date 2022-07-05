package com.gussoft.sistema28.services.impl;

import com.gussoft.sistema28.config.Conexion;
import com.gussoft.sistema28.models.Compra;
import com.gussoft.sistema28.services.CompraService;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CompraServiceImpl implements CompraService {

    private Connection connection = null;

    @Override
    public void agregarCompra(Compra obj) {
        try{
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement=connection.prepareCall("{call SP_I_Compra(?,?,?,?,?,?,?,?,?)}");
            statement.setString("pidtipodocumento",obj.getStrIdTipoDocumento());
            statement.setString("pidproveedor",obj.getStrIdProveedor());
            statement.setString("pidempleado",obj.getStrIdEmpleado());
            statement.setString("pnumero",obj.getStrNumeroCompra());
            statement.setDate ("pfecha", new java.sql.Date(obj.getStrFechaCompra().getTime()));
            statement.setString("psubtotal",obj.getStrSubTotalCompra());
            statement.setString("piva",obj.getStrIvaCompra());
            statement.setString("ptotal",obj.getStrTotalCompra());
            statement.setString("pestado",obj.getStrEstadoCompra());
            statement.execute();

            JOptionPane.showMessageDialog(null,"¡Compra Realizada con éxito!","Mensaje del Sistema",1);

        }catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public void modificarCompra(String codigo, Compra obj) {
        try{
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement=connection.prepareCall("{call SP_U_Compra(?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pidcompra",codigo);
            statement.setString("pidtipodocumento",obj.getStrIdTipoDocumento());
            statement.setString("pidproveedor",obj.getStrIdProveedor());
            statement.setString("pidempleado",obj.getStrIdEmpleado());
            statement.setString("pnumero",obj.getStrNumeroCompra());
            statement.setDate ("pfecha", new java.sql.Date(obj.getStrFechaCompra().getTime()));
            statement.setString("psubtotal",obj.getStrSubTotalCompra());
            statement.setString("piva",obj.getStrIvaCompra());
            statement.setString("ptotal",obj.getStrTotalCompra());
            statement.setString("pestado",obj.getStrEstadoCompra());
            statement.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        JOptionPane.showMessageDialog(null,"¡Compra Actualizada!","Mensaje del Sistema",1);
    }

    @Override
    public ArrayList<Compra> listarCompra() {
        ArrayList<Compra> compras=new ArrayList<>();
        try{
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement=connection.prepareCall("{call SP_S_Compra}");
            ResultSet resultSet=statement.executeQuery();

            while (resultSet.next()){
                Compra compra=new Compra();
                compra.setStrIdCompra(resultSet.getString("IdCompra"));
                compra.setStrTipoDocumento(resultSet.getString("TipoDocumento"));
                compra.setStrProveedor(resultSet.getString("Proveedor"));
                compra.setStrEmpleado(resultSet.getString("Empleado"));
                compra.setStrNumeroCompra(resultSet.getString("Numero"));
                compra.setStrFechaCompra(resultSet.getDate("Fecha"));
                compra.setStrSubTotalCompra(resultSet.getString("SubTotal"));
                compra.setStrIvaCompra(resultSet.getString("Iva"));
                compra.setStrTotalCompra(resultSet.getString("Total"));
                compra.setStrEstadoCompra(resultSet.getString("Estado"));

                compras.add(compra);
            }
            return compras;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public ResultSet listarCompraPorParametro(String criterio, String busqueda) {
        ResultSet rs = null;
        try{
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_CompraPorParametro(?,?)}");
            statement.setString("pcriterio", criterio);
            statement.setString("pbusqueda", busqueda);
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }

        return null;
    }

    @Override
    public ResultSet obtenerUltimoIdCompra() {
        ResultSet rs = null;
        try{
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_UltimoIdCompra()}");
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }

        return null;
    }

    @Override
    public ResultSet listarCompraPorFecha(String criterio, Date fechaini, Date fechafin, String doc) {
        ResultSet rs = null;
        try{
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_CompraPorFecha(?,?,?,?)}");
            statement.setString ("pcriterio", criterio);
            statement.setDate ("pfechaini", new java.sql.Date(fechaini.getTime()));
            statement.setDate ("pfechafin", new java.sql.Date(fechafin.getTime()));
            statement.setString("pdocumento", doc);
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }

    @Override
    public void actualizarCompraEstado(String codigo, Compra obj) {
        try{
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement=connection.prepareCall("{call SP_U_ActualizarCompraEstado(?,?)}");
            statement.setString("pidcompra",codigo);
            statement.setString("pestado",obj.getStrEstadoCompra());
            statement.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        JOptionPane.showMessageDialog(null,"¡Compra Anulada!","Mensaje del Sistema",1);
    }

    @Override
    public ResultSet listarCompraPorDetalle(String criterio, Date fechaini, Date fechafin) {
        ResultSet rs = null;
        try{
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_CompraPorDetalle(?,?,?)}");
            statement.setString ("pcriterio", criterio);
            statement.setDate ("pfechaini", new java.sql.Date(fechaini.getTime()));
            statement.setDate ("pfechafin", new java.sql.Date(fechafin.getTime()));
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }
}
