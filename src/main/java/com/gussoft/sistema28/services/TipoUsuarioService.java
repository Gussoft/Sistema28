package com.gussoft.sistema28.services;

import com.gussoft.sistema28.models.TipoUsuario;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface TipoUsuarioService {

    void agregarTipoUsuario(TipoUsuario obj);

    void modificarTipoUsuario(String codigo, TipoUsuario obj);

    ArrayList<TipoUsuario> listarTipoUsuario();

    ResultSet listarTipoUsuarioPorParametro(String criterio, String busqueda);

    ResultSet consultarLoginPermisos(String nomuser, String tipouser);

}
