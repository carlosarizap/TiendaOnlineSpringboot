package com.evertec.tienda_online.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TiendaDTO {
    private UUID id;
    private String nombre;
    private String direccion;
    private String telefono;
}
