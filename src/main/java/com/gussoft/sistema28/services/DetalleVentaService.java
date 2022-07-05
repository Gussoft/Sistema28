package com.gussoft.sistema28.services;

import com.gussoft.sistema28.models.DetalleVenta;

import java.sql.ResultSet;

public interface DetalleVentaService {

    void agregarDetalleVenta(DetalleVenta obj);

    void modificarDetalleVenta(String codigo, DetalleVenta obj);

    ResultSet listarDetalleVentaPorParametro(String criterio, String busqueda);
}
