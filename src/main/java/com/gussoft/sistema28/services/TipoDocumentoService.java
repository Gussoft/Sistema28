package com.gussoft.sistema28.services;

import com.gussoft.sistema28.models.TipoDocumento;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface TipoDocumentoService {

    void agregarTipoDocumento(TipoDocumento obj);

    void modificarTipoDocumento(String codigo, TipoDocumento obj);

    ArrayList<TipoDocumento> listarTipoDocumento();

    ResultSet listarTipoDocumentoPorParametro(String criterio, String busqueda);
}
