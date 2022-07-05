package com.gussoft.sistema28.services.impl;

import com.gussoft.sistema28.config.Conexion;
import com.gussoft.sistema28.models.TipoUsuario;
import com.gussoft.sistema28.services.TipoUsuarioService;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipoUsuarioServiceImpl implements TipoUsuarioService {

    private Connection connection = null;

    @Override
    public void agregarTipoUsuario(TipoUsuario obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_I_TipoUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pdescripcion", obj.getStrDescripcionTipoUsuario());
            statement.setString("pp_venta", obj.getStrP_Venta());
            statement.setString("pp_compra", obj.getStrP_Compra());
            statement.setString("pp_producto", obj.getStrP_Producto());
            statement.setString("pp_proveedor", obj.getStrP_Proveedor());
            statement.setString("pp_empleado", obj.getStrP_Empleado());
            statement.setString("pp_cliente", obj.getStrP_Cliente());
            statement.setString("pp_categoria", obj.getStrP_Categoria());
            statement.setString("pp_tipodoc", obj.getStrP_Tipodoc());
            statement.setString("pp_tipouser", obj.getStrP_Tipouser());
            statement.setString("pp_anularv", obj.getStrP_Anularv());
            statement.setString("pp_anularc", obj.getStrP_Anularc());
            statement.setString("pp_estadoprod", obj.getStrP_Estadoprod());
            statement.setString("pp_ventare", obj.getStrP_Ventare());
            statement.setString("pp_ventade", obj.getStrP_Ventade());
            statement.setString("pp_estadistica", obj.getStrP_Estadistica());
            statement.setString("pp_comprare", obj.getStrP_Comprare());
            statement.setString("pp_comprade", obj.getStrP_Comprade());
            statement.setString("pp_pass", obj.getStrP_Pass());
            statement.setString("pp_respaldar", obj.getStrP_Respaldar());
            statement.setString("pp_restaurar", obj.getStrP_Restaurar());
            statement.setString("pp_caja", obj.getStrP_Caja());

            statement.execute();
            JOptionPane.showMessageDialog(null, "¡Tipo de Usuario Agregado con éxito!", "Mensaje del Sistema", 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public void modificarTipoUsuario(String codigo, TipoUsuario obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_U_TipoUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pidtipousuario", codigo);
            statement.setString("pdescripcion", obj.getStrDescripcionTipoUsuario());
            statement.setString("pp_venta", obj.getStrP_Venta());
            statement.setString("pp_compra", obj.getStrP_Compra());
            statement.setString("pp_producto", obj.getStrP_Producto());
            statement.setString("pp_proveedor", obj.getStrP_Proveedor());
            statement.setString("pp_empleado", obj.getStrP_Empleado());
            statement.setString("pp_cliente", obj.getStrP_Cliente());
            statement.setString("pp_categoria", obj.getStrP_Categoria());
            statement.setString("pp_tipodoc", obj.getStrP_Tipodoc());
            statement.setString("pp_tipouser", obj.getStrP_Tipouser());
            statement.setString("pp_anularv", obj.getStrP_Anularv());
            statement.setString("pp_anularc", obj.getStrP_Anularc());
            statement.setString("pp_estadoprod", obj.getStrP_Estadoprod());
            statement.setString("pp_ventare", obj.getStrP_Ventare());
            statement.setString("pp_ventade", obj.getStrP_Ventade());
            statement.setString("pp_estadistica", obj.getStrP_Estadistica());
            statement.setString("pp_comprare", obj.getStrP_Comprare());
            statement.setString("pp_comprade", obj.getStrP_Comprade());
            statement.setString("pp_pass", obj.getStrP_Pass());
            statement.setString("pp_respaldar", obj.getStrP_Respaldar());
            statement.setString("pp_restaurar", obj.getStrP_Restaurar());
            statement.setString("pp_caja", obj.getStrP_Caja());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        JOptionPane.showMessageDialog(null, "¡Tipo de Usuario Actualizado!", "Mensaje del Sistema", 1);
    }

    @Override
    public ArrayList<TipoUsuario> listarTipoUsuario() {
        ArrayList<TipoUsuario> tipousuariousuarios = new ArrayList<>();
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_TipoUsuario}");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TipoUsuario tipousuario = new TipoUsuario();
                tipousuario.setStrIdTipoUsuario(resultSet.getString("IdTipoUsuario"));
                tipousuario.setStrDescripcionTipoUsuario(resultSet.getString("Descripcion"));
                tipousuario.setStrP_Venta(resultSet.getString("p_venta"));
                tipousuario.setStrP_Compra(resultSet.getString("p_compra"));
                tipousuario.setStrP_Producto(resultSet.getString("p_producto"));
                tipousuario.setStrP_Proveedor(resultSet.getString("p_proveedor"));
                tipousuario.setStrP_Empleado(resultSet.getString("p_empleado"));
                tipousuario.setStrP_Cliente(resultSet.getString("p_cliente"));
                tipousuario.setStrP_Categoria(resultSet.getString("p_categoria"));
                tipousuario.setStrP_Tipodoc(resultSet.getString("p_tipodoc"));
                tipousuario.setStrP_Tipouser(resultSet.getString("p_tipouser"));
                tipousuario.setStrP_Anularv(resultSet.getString("p_anularv"));
                tipousuario.setStrP_Anularc(resultSet.getString("p_anularc"));
                tipousuario.setStrP_Estadoprod(resultSet.getString("p_estadoprod"));
                tipousuario.setStrP_Ventare(resultSet.getString("p_ventare"));
                tipousuario.setStrP_Ventade(resultSet.getString("p_ventade"));
                tipousuario.setStrP_Estadistica(resultSet.getString("p_estadistica"));
                tipousuario.setStrP_Comprare(resultSet.getString("p_comprare"));
                tipousuario.setStrP_Comprade(resultSet.getString("p_comprade"));
                tipousuario.setStrP_Pass(resultSet.getString("p_pass"));
                tipousuario.setStrP_Respaldar(resultSet.getString("p_respaldar"));
                tipousuario.setStrP_Restaurar(resultSet.getString("p_restaurar"));
                tipousuario.setStrP_Caja(resultSet.getString("p_caja"));

                tipousuariousuarios.add(tipousuario);
            }
            return tipousuariousuarios;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }

    @Override
    public ResultSet listarTipoUsuarioPorParametro(String criterio, String busqueda) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_TipoUsuarioPorParametro(?,?)}");
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
    public ResultSet consultarLoginPermisos(String nomuser, String tipouser) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_LoginPermisos(?,?)}");
            statement.setString("pnombre_usuario", nomuser);
            statement.setString("pdescripcion_tipousuario", tipouser);
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
