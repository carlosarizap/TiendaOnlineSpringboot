package com.evertec.tienda_online.mapper;

import com.evertec.tienda_online.dto.ProductoDTO;
import com.evertec.tienda_online.entity.Producto;
import com.evertec.tienda_online.entity.Tienda;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto producto) {
        if (producto == null) return null;

        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .tiendaId(producto.getTienda() != null ? producto.getTienda().getId() : null)
                .build();
    }

    public Producto toEntity(ProductoDTO dto) {
        if (dto == null) return null;

        return Producto.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .tienda(Tienda.builder().id(dto.getTiendaId()).build()) // solo ID
                .build();
    }
}
