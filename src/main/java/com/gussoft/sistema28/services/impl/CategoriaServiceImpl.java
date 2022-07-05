package com.gussoft.sistema28.services.impl;

import com.gussoft.sistema28.config.Conexion;
import com.gussoft.sistema28.models.Categoria;
import com.gussoft.sistema28.services.CategoriaService;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaServiceImpl implements CategoriaService {

    private Connection connection = null;

    @Override
    public void agregarCategoria(Categoria obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_I_Categoria(?)}");
            statement.setString("pdescripcion", obj.getStrDescripcionCategoria());
            statement.execute();

            JOptionPane.showMessageDialog(null, "¡Categoría Agregada con éxito!", "Mensaje del Sistema", 1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public void modificarCategoria(String codigo, Categoria obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_U_Categoria(?,?)}");
            statement.setString("pidcategoria", codigo);
            statement.setString("pdescripcion", obj.getStrDescripcionCategoria());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        JOptionPane.showMessageDialog(null, "¡Categoría Actualizada!", "Mensaje del Sistema", 1);
    }

    @Override
    public ArrayList<Categoria> listarCategoria() {
        ArrayList<Categoria> categorias = new ArrayList<>();
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_Categoria}");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setStrIdCategoria(resultSet.getString("IdCategoria"));
                categoria.setStrDescripcionCategoria(resultSet.getString("Descripcion"));
                categorias.add(categoria);
            }
            return categorias;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public ResultSet listarCategoriaPorParametro(String criterio, String busqueda) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_CategoriaPorParametro(?,?)}");
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
