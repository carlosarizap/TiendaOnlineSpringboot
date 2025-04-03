package com.evertec.tienda_online.dto;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {
    private UUID id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private UUID tiendaId;
}
