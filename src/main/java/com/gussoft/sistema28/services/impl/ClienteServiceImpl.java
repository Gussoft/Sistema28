package com.gussoft.sistema28.services.impl;

import com.gussoft.sistema28.config.Conexion;
import com.gussoft.sistema28.models.Cliente;
import com.gussoft.sistema28.services.ClienteService;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteServiceImpl implements ClienteService {

    private Connection connection = null;

    @Override
    public void agregarCliente(Cliente obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_I_Cliente(?,?,?,?,?,?)}");
            statement.setString("pnombre", obj.getStrNombreCliente());
            statement.setString("prol", obj.getStrRolCliente());
            statement.setString("prut", obj.getStrRutCliente());
            statement.setString("pdireccion", obj.getStrDireccionCliente());
            statement.setString("ptelefono", obj.getStrTelefonoCliente());
            statement.setString("pobsv", obj.getStrObsvCliente());
            statement.execute();

            JOptionPane.showMessageDialog(null, "¡Cliente Agregado con éxito!", "Mensaje del Sistema", 1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public void modificarCliente(String codigo, Cliente obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_U_Cliente(?,?,?,?,?,?,?)}");
            statement.setString("pidcliente", codigo);
            statement.setString("pnombre", obj.getStrNombreCliente());
            statement.setString("prol", obj.getStrRolCliente());
            statement.setString("prut", obj.getStrRutCliente());
            statement.setString("pdireccion", obj.getStrDireccionCliente());
            statement.setString("ptelefono", obj.getStrTelefonoCliente());
            statement.setString("pobsv", obj.getStrObsvCliente());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        JOptionPane.showMessageDialog(null, "¡Cliente Actualizado!", "Mensaje del Sistema", 1);
    }

    @Override
    public ArrayList<Cliente> listarCliente() {
        ArrayList<Cliente> clienteusuarios = new ArrayList<>();
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_Cliente}");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setStrIdCliente(resultSet.getString("IdCliente"));
                cliente.setStrNombreCliente(resultSet.getString("nombre"));
                cliente.setStrRolCliente(resultSet.getString("rol"));
                cliente.setStrRutCliente(resultSet.getString("rut"));
                cliente.setStrDireccionCliente(resultSet.getString("direccion"));
                cliente.setStrTelefonoCliente(resultSet.getString("telefono"));
                cliente.setStrObsvCliente(resultSet.getString("obsv"));
                clienteusuarios.add(cliente);
            }
            return clienteusuarios;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public ResultSet listarClientePorParametro(String criterio, String busqueda) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_ClientePorParametro(?,?)}");
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
