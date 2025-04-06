package com.evertec.tienda_online.mapper;

import com.evertec.tienda_online.dto.OrdenDTO;
import com.evertec.tienda_online.dto.OrdenDetalleDTO;
import com.evertec.tienda_online.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrdenMapper {

        public OrdenDTO toDTO(Orden orden) {
                List<OrdenDetalleDTO> detalleDTOs = orden.getDetalles().stream()
                    .map(detalle -> OrdenDetalleDTO.builder()
                        .id(detalle.getId())
                        .productoId(detalle.getProducto().getId())
                        .nombreProducto(detalle.getProducto().getNombre())
                        .cantidad(detalle.getCantidad())
                        .precioUnitario(detalle.getPrecioUnitario())
                        .build()
                    ).collect(Collectors.toList());
            
                return OrdenDTO.builder()
                    .id(orden.getId())
                    .clienteId(orden.getCliente().getId())
                    .nombreCliente(orden.getCliente().getNombre())
                    .correoCliente(orden.getCliente().getCorreo())
                    .tiendaId(orden.getTienda().getId())
                    .nombreTienda(orden.getTienda().getNombre())
                    .total(orden.getTotal())
                    .estado(orden.getEstado())
                    .creadoEn(orden.getCreadoEn())
                    .detalles(detalleDTOs)
                    .build();
            }
            
            

    public Orden toEntity(OrdenDTO dto) {
        Orden orden = new Orden();
        orden.setId(dto.getId());
        orden.setCliente(Cliente.builder().id(dto.getClienteId()).build());
        orden.setTienda(Tienda.builder().id(dto.getTiendaId()).build());
        orden.setEstado(dto.getEstado());
        orden.setTotal(dto.getTotal());

        List<OrdenDetalle> detalles = dto.getDetalles().stream()
                .map(detalleDTO -> OrdenDetalle.builder()
                        .id(detalleDTO.getId())
                        .producto(Producto.builder().id(detalleDTO.getProductoId()).build())
                        .cantidad(detalleDTO.getCantidad())
                        .precioUnitario(detalleDTO.getPrecioUnitario())
                        .orden(orden) // relación inversa
                        .build()
                ).collect(Collectors.toList());

        orden.setDetalles(detalles);
        return orden;
    }
}
