package com.evertec.tienda_online.controller;

import com.evertec.tienda_online.dto.ProductoDTO;
import com.evertec.tienda_online.entity.Producto;
import com.evertec.tienda_online.mapper.ProductoMapper;
import com.evertec.tienda_online.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoMapper productoMapper;

    public ProductoController(ProductoService productoService, ProductoMapper productoMapper) {
        this.productoService = productoService;
        this.productoMapper = productoMapper;
    }

    @GetMapping
    public List<ProductoDTO> listar() {
        return productoService.obtenerTodos()
                .stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductoDTO obtener(@PathVariable UUID id) {
        Producto producto = productoService.obtenerPorId(id);
        return productoMapper.toDTO(producto);
    }

    @PostMapping
    public ProductoDTO crear(@RequestBody ProductoDTO productoDTO) {
        Producto producto = productoMapper.toEntity(productoDTO);
        Producto productoGuardado = productoService.guardar(producto);
        return productoMapper.toDTO(productoGuardado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable UUID id) {
        productoService.eliminar(id);
    }
}
