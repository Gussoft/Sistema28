package com.gussoft.sistema28.services;

import com.gussoft.sistema28.models.Producto;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface ProductoService {

    void agregarProducto(Producto obj);

    void modificarProducto(String codigo, Producto obj);

    void actualizarProductoStock(String codigo, Producto obj);

    ArrayList<Producto> listarProducto();

    ArrayList<Producto> listarProductoActivo();

    ResultSet listarProductoPorParametro(String criterio, String busqueda);

    ResultSet listarProductoActivoPorParametro(String criterio, String busqueda);

    ResultSet consultaStock();

    ResultSet kardexValorizado();

    ResultSet verificarCodigoBar(String busqueda);
}
