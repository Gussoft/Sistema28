package com.gussoft.sistema28.services;

import com.gussoft.sistema28.models.Proveedor;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface ProveedorService {

    void agregarProveedor(Proveedor obj);

    void modificarProveedor(String codigo, Proveedor obj);

    ArrayList<Proveedor> listarProveedor();

    ResultSet listarProveedorPorParametro(String criterio, String busqueda);
}
