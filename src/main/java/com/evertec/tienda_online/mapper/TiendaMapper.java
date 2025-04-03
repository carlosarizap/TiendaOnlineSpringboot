package com.evertec.tienda_online.mapper;

import com.evertec.tienda_online.dto.TiendaDTO;
import com.evertec.tienda_online.entity.Tienda;
import org.springframework.stereotype.Component;

@Component
public class TiendaMapper {

    public TiendaDTO toDTO(Tienda tienda) {
        if (tienda == null) return null;
        return TiendaDTO.builder()
                .id(tienda.getId())
                .nombre(tienda.getNombre())
                .direccion(tienda.getDireccion())
                .telefono(tienda.getTelefono())
                .build();
    }

    public Tienda toEntity(TiendaDTO dto) {
        if (dto == null) return null;
        return Tienda.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .build();
    }
}
