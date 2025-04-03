package com.evertec.tienda_online.service;

import com.evertec.tienda_online.entity.Producto;
import java.util.List;
import java.util.UUID;

public interface ProductoService {
    List<Producto> obtenerTodos();
    Producto obtenerPorId(UUID id);
    Producto guardar(Producto producto);
    void eliminar(UUID id);
}
