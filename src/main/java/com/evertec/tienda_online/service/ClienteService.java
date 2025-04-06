package com.evertec.tienda_online.service;

import com.evertec.tienda_online.entity.Cliente;
import java.util.List;
import java.util.UUID;

public interface ClienteService {
    Cliente registrar(Cliente cliente);

    Cliente login(String correo, String password);

    List<Cliente> obtenerTodos();

    Cliente obtenerPorId(UUID id);

    void eliminar(UUID id);
}
