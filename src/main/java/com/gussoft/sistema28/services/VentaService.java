package com.gussoft.sistema28.services;

import com.gussoft.sistema28.models.Venta;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public interface VentaService {

    void agregarVenta(Venta obj);

    void modificarVenta(String codigo, Venta obj);

    ArrayList<Venta> listarVenta();

    ResultSet listarVentaPorParametro(String criterio, String busqueda);

    ResultSet obtenerUltimoIdVenta();

    ResultSet listarVentaPorFecha(String criterio, Date fechaini, Date fechafin, String doc);

    void actualizarVentaEstado(String codigo, Venta obj);

    ResultSet listarVentaPorDetalle(String criterio, Date fechaini, Date fechafin);

    ResultSet listarVentaMensual(String criterio, String fecha_ini, String fecha_fin);

}
