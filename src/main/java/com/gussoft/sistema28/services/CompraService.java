package com.gussoft.sistema28.services;

import com.gussoft.sistema28.models.Cliente;
import com.gussoft.sistema28.models.Compra;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public interface CompraService {

    void agregarCompra(Compra obj);

    void modificarCompra(String codigo, Compra obj);

    ArrayList<Compra> listarCompra();

    ResultSet listarCompraPorParametro(String criterio, String busqueda);

    ResultSet obtenerUltimoIdCompra();

    ResultSet listarCompraPorFecha(String criterio, Date fechaini, Date fechafin, String doc);

    void actualizarCompraEstado(String codigo, Compra obj);

    ResultSet listarCompraPorDetalle(String criterio, Date fechaini, Date fechafin);
}
