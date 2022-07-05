package com.gussoft.sistema28.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static Conexion instancia;

    private Conexion() {
    }

    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConexion() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ventas", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cn;
    }

    public void closeConexion(Connection cn) {
        try {
            if (cn != null) {
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
