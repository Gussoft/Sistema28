package com.gussoft.sistema28.services;

import com.gussoft.sistema28.models.Empleado;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface EmpleadoService {

    void agregarEmpleado(Empleado obj);

    void modificarEmpleado(String codigo, Empleado obj);

    ResultSet LoginEmpleados(String usu, String contra, String desc);

    ArrayList<Empleado> listarEmpleado();

    ResultSet listarEmpleadoPorParametro(String criterio, String busqueda);

    void cambiarContrasena(String codigo, Empleado obj);
}
