package com.gussoft.sistema28.services;

import com.gussoft.sistema28.models.Cliente;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface ClienteService {

    void agregarCliente(Cliente obj);

    void modificarCliente(String codigo, Cliente obj);

    ArrayList<Cliente> listarCliente();

    ResultSet listarClientePorParametro(String criterio, String busqueda);

}
