package com.evertec.tienda_online.service.impl;

import com.evertec.tienda_online.entity.Producto;
import com.evertec.tienda_online.repository.ProductoRepository;
import com.evertec.tienda_online.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerPorId(UUID id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    @Override
    public Producto guardar(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del producto es obligatorio");
        }
        if (producto.getPrecio() == null || producto.getPrecio() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El precio del producto debe ser mayor o igual a 0");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El stock del producto debe ser mayor o igual a 0");
        }
        return productoRepository.save(producto);
    }

    @Override
    public void eliminar(UUID id) {
        if (!productoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado para eliminar");
        }
        productoRepository.deleteById(id);
    }
}
