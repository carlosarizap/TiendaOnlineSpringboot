package com.evertec.tienda_online.service;

import com.evertec.tienda_online.entity.Tienda;

import java.util.List;
import java.util.UUID;

public interface TiendaService {
    List<Tienda> obtenerTodas();
    Tienda obtenerPorId(UUID id);
    Tienda guardar(Tienda tienda);
    void eliminar(UUID id);
}
