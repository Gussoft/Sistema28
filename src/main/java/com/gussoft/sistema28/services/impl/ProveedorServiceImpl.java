package com.gussoft.sistema28.services.impl;

import com.gussoft.sistema28.config.Conexion;
import com.gussoft.sistema28.models.Proveedor;
import com.gussoft.sistema28.services.ProveedorService;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProveedorServiceImpl implements ProveedorService {

    private Connection connection = null;

    @Override
    public void agregarProveedor(Proveedor obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_I_Proveedor(?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pnombre", obj.getStrNombreProveedor());
            statement.setString("prol", obj.getStrRolProveedor());
            statement.setString("prut", obj.getStrRutProveedor());
            statement.setString("pdireccion", obj.getStrDireccionProveedor());
            statement.setString("ptelefono", obj.getStrTelefonoProveedor());
            statement.setString("pcelular", obj.getStrCelularProveedor());
            statement.setString("pemail", obj.getStrEmailProveedor());
            statement.setString("pcuenta1", obj.getStrCuenta1Proveedor());
            statement.setString("pcuenta2", obj.getStrCuenta2Proveedor());
            statement.setString("pestado", obj.getStrEstadoProveedor());
            statement.setString("pobsv", obj.getStrObsvProveedor());
            statement.execute();

            JOptionPane.showMessageDialog(null, "¡Proveedor Agregado con éxito!", "Mensaje del Sistema", 1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public void modificarProveedor(String codigo, Proveedor obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_U_Proveedor(?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pidproveedor", codigo);
            statement.setString("pnombre", obj.getStrNombreProveedor());
            statement.setString("prol", obj.getStrRolProveedor());
            statement.setString("prut", obj.getStrRutProveedor());
            statement.setString("pdireccion", obj.getStrDireccionProveedor());
            statement.setString("ptelefono", obj.getStrTelefonoProveedor());
            statement.setString("pcelular", obj.getStrCelularProveedor());
            statement.setString("pemail", obj.getStrEmailProveedor());
            statement.setString("pcuenta1", obj.getStrCuenta1Proveedor());
            statement.setString("pcuenta2", obj.getStrCuenta2Proveedor());
            statement.setString("pestado", obj.getStrEstadoProveedor());
            statement.setString("pobsv", obj.getStrObsvProveedor());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        JOptionPane.showMessageDialog(null, "¡Proveedor Actualizado!", "Mensaje del Sistema", 1);
    }

    @Override
    public ArrayList<Proveedor> listarProveedor() {
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_Proveedor}");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setStrIdProveedor(resultSet.getString("IdProveedor"));
                proveedor.setStrNombreProveedor(resultSet.getString("nombre"));
                proveedor.setStrRolProveedor(resultSet.getString("rol"));
                proveedor.setStrRutProveedor(resultSet.getString("rut"));
                proveedor.setStrDireccionProveedor(resultSet.getString("direccion"));
                proveedor.setStrTelefonoProveedor(resultSet.getString("telefono"));
                proveedor.setStrCelularProveedor(resultSet.getString("celular"));
                proveedor.setStrEmailProveedor(resultSet.getString("email"));
                proveedor.setStrCuenta1Proveedor(resultSet.getString("cuenta1"));
                proveedor.setStrCuenta2Proveedor(resultSet.getString("cuenta2"));
                proveedor.setStrEstadoProveedor(resultSet.getString("estado"));
                proveedor.setStrObsvProveedor(resultSet.getString("obsv"));
                proveedores.add(proveedor);
            }
            return proveedores;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }

    @Override
    public ResultSet listarProveedorPorParametro(String criterio, String busqueda) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_ProveedorPorParametro(?,?)}");
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
