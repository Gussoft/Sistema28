package com.gussoft.sistema28.services;

import com.gussoft.sistema28.models.Categoria;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface CategoriaService {

    void agregarCategoria(Categoria obj);

    void modificarCategoria(String codigo, Categoria obj);

    ArrayList<Categoria> listarCategoria();

    ResultSet listarCategoriaPorParametro(String criterio, String busqueda);

}
