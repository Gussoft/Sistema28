package com.gussoft.sistema28.services.impl;

import com.gussoft.sistema28.config.Conexion;
import com.gussoft.sistema28.models.Empleado;
import com.gussoft.sistema28.services.EmpleadoService;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpleadoServiceImpl implements EmpleadoService {

    private Connection connection = null;

    @Override
    public void agregarEmpleado(Empleado obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_I_Empleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pnombre", obj.getStrNombreEmpleado());
            statement.setString("papellido", obj.getStrApellidoEmpleado());
            statement.setString("psexo", obj.getStrSexoEmpleado());
            statement.setDate("pfechanac", new java.sql.Date(obj.getStrFechaNacEmpleado().getTime()));
            statement.setString("pdireccion", obj.getStrDireccionEmpleado());
            statement.setString("ptelefono", obj.getStrTelefonoEmpleado());
            statement.setString("pcelular", obj.getStrCelularEmpleado());
            statement.setString("pemail", obj.getStrEmailEmpleado());
            statement.setString("prut", obj.getStrRutEmpleado());
            statement.setDate("pfechaing", new java.sql.Date(obj.getStrFechaIngEmpleado().getTime()));
            statement.setString("psueldo", obj.getStrSueldoEmpleado());
            statement.setString("pestado", obj.getStrEstadoEmpleado());
            statement.setString("pusuario", obj.getStrUsuarioEmpleado());
            statement.setString("pcontraseña", obj.getStrContrasenaEmpleado());
            statement.setString("pidtipousuario", obj.getStrIdTipoUsuario());
            statement.execute();
            JOptionPane.showMessageDialog(null, "¡Empleado Agregado con éxito!", "Mensaje del Sistema", 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public void modificarEmpleado(String codigo, Empleado obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_U_Empleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pnombre", obj.getStrNombreEmpleado());
            statement.setString("papellido", obj.getStrApellidoEmpleado());
            statement.setString("psexo", obj.getStrSexoEmpleado());
            statement.setDate("pfechanac", new java.sql.Date(obj.getStrFechaNacEmpleado().getTime()));
            statement.setString("pdireccion", obj.getStrDireccionEmpleado());
            statement.setString("ptelefono", obj.getStrTelefonoEmpleado());
            statement.setString("pcelular", obj.getStrCelularEmpleado());
            statement.setString("pemail", obj.getStrEmailEmpleado());
            statement.setString("prut", obj.getStrRutEmpleado());
            statement.setDate("pfechaing", new java.sql.Date(obj.getStrFechaIngEmpleado().getTime()));
            statement.setString("psueldo", obj.getStrSueldoEmpleado());
            statement.setString("pestado", obj.getStrEstadoEmpleado());
            statement.setString("pusuario", obj.getStrUsuarioEmpleado());
            statement.setString("pcontraseña", obj.getStrContrasenaEmpleado());
            statement.setString("pidtipousuario", obj.getStrIdTipoUsuario());
            statement.setString("pidempleado", codigo);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        JOptionPane.showMessageDialog(null, "¡Empleado Actualizado!", "Mensaje del Sistema", 1);
    }

    @Override
    public ResultSet LoginEmpleados(String usu, String contra, String desc) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_Login(?,?,?)}");
            statement.setString("pusuario", usu);
            statement.setString("pcontraseña", contra);
            statement.setString("pdescripcion", desc);
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException SQLex) {
            JOptionPane.showMessageDialog(null, "¡Algo Salio Mal!", "Mensaje del Sistema", 1);
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        return null;
    }

    @Override
    public ArrayList<Empleado> listarEmpleado() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_Empleado}");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setStrIdEmpleado(resultSet.getString("IdEmpleado"));
                empleado.setStrNombreEmpleado(resultSet.getString("Nombre"));
                empleado.setStrApellidoEmpleado(resultSet.getString("Apellido"));
                empleado.setStrSexoEmpleado(resultSet.getString("Sexo"));
                empleado.setStrFechaNacEmpleado(resultSet.getDate("FechaNac"));
                empleado.setStrDireccionEmpleado(resultSet.getString("Direccion"));
                empleado.setStrTelefonoEmpleado(resultSet.getString("Telefono"));
                empleado.setStrCelularEmpleado(resultSet.getString("Celular"));
                empleado.setStrEmailEmpleado(resultSet.getString("Email"));
                empleado.setStrRutEmpleado(resultSet.getString("Rut"));
                empleado.setStrFechaIngEmpleado(resultSet.getDate("FechaIng"));
                empleado.setStrSueldoEmpleado(resultSet.getString("Sueldo"));
                empleado.setStrEstadoEmpleado(resultSet.getString("Estado"));
                empleado.setStrUsuarioEmpleado(resultSet.getString("Usuario"));
                empleado.setStrContrasenaEmpleado(resultSet.getString("Contraseña"));
                empleado.setStrTipoUsuario(resultSet.getString("TipoUsuario"));


                empleados.add(empleado);
            }
            return empleados;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
    }

    @Override
    public ResultSet listarEmpleadoPorParametro(String criterio, String busqueda) {
        ResultSet rs = null;
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_S_EmpleadoPorParametro(?,?)}");
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
    public void cambiarContrasena(String codigo, Empleado obj) {
        try {
            connection = Conexion.getInstance().getConexion();
            CallableStatement statement = connection.prepareCall("{call SP_U_CambiarPass(?,?)}");
            statement.setString("pidempleado", codigo);
            statement.setString("pcontraseña", obj.getStrContrasenaEmpleado());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.getInstance().closeConexion(connection);
        }
        JOptionPane.showMessageDialog(null, "¡Se cambio contraseña satisfactoriamente!", "Mensaje del Sistema", 1);

    }
}
