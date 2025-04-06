package com.evertec.tienda_online.service;

import java.util.List;
import java.util.UUID;

import com.evertec.tienda_online.dto.OrdenDTO;
import com.evertec.tienda_online.entity.Orden;

public interface OrdenService {
    Orden crearOrden(OrdenDTO dto);
    Orden obtenerPorId(UUID id);
    List<Orden> listarPorCliente(UUID clienteId);
    Orden actualizarEstado(UUID id, String nuevoEstado);
    void cancelarOrden(UUID id);
}
