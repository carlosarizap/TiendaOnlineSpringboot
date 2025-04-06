package com.evertec.tienda_online.mapper;

import com.evertec.tienda_online.dto.ClienteDTO;
import com.evertec.tienda_online.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) return null;

        return ClienteDTO.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .correo(cliente.getCorreo())
                .telefono(cliente.getTelefono())
                .build();
    }

    public Cliente toEntity(ClienteDTO dto) {
        if (dto == null) return null;

        return Cliente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .correo(dto.getCorreo())
                .telefono(dto.getTelefono())
                .build();
    }
}
