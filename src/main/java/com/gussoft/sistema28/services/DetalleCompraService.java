package com.gussoft.sistema28.services;

import com.gussoft.sistema28.models.DetalleCompra;

import java.sql.ResultSet;

public interface DetalleCompraService {

    void agregarDetalleCompra(DetalleCompra obj);

    void modificarDetalleCompra(String codigo, DetalleCompra obj);

    ResultSet listarDetalleCompraPorParametro(String criterio, String busqueda);

}
