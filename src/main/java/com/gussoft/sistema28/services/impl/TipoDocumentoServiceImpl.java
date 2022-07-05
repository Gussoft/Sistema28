package com.gussoft.sistema28.services.impl;

import com.gussoft.sistema28.config.Conexion;
import com.gussoft.sistema28.models.TipoDocumento;
import com.gussoft.sistema28.services.TipoDocumentoService;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    private Connection connection = null;

    @Override
    public void agregarTipoDocumento(TipoDocumento obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_I_TipoDocumento(?)}");
            statement.setString("pdescripcion", obj.getStrDescripcionTipoDocumento());
            statement.execute();

            JOptionPane.showMessageDialog(null, "¡Tipo de Documento Agregado con éxito!", "Mensaje del Sistema", 1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public void modificarTipoDocumento(String codigo, TipoDocumento obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_U_TipoDocumento(?,?)}");
            statement.setString("pidtipodocumento", codigo);
            statement.setString("pdescripcion", obj.getStrDescripcionTipoDocumento());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        JOptionPane.showMessageDialog(null, "¡Tipo de Documento Actualizado!", "Mensaje del Sistema", 1);
    }

    @Override
    public ArrayList<TipoDocumento> listarTipoDocumento() {
        ArrayList<TipoDocumento> tipodocumentos = new ArrayList<>();
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_TipoDocumento}");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TipoDocumento tipo = new TipoDocumento();
                tipo.setStrIdTipoDocumento(resultSet.getString("IdTipoDocumento"));
                tipo.setStrDescripcionTipoDocumento(resultSet.getString("Descripcion"));
                tipodocumentos.add(tipo);
            }
            return tipodocumentos;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }

    @Override
    public ResultSet listarTipoDocumentoPorParametro(String criterio, String busqueda) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_TipoDocumentoPorParametro(?,?)}");
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
