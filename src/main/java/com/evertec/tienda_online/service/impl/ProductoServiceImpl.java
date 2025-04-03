package com.evertec.tienda_online.service.impl;

import com.evertec.tienda_online.entity.Producto;
import com.evertec.tienda_online.repository.ProductoRepository;
import com.evertec.tienda_online.service.ProductoService;
import org.springframework.stereotype.Service;

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
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminar(UUID id) {
        productoRepository.deleteById(id);
    }
}
