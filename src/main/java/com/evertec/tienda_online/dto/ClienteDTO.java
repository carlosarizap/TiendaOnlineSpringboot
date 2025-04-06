package com.evertec.tienda_online.dto;

import lombok.*;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClienteDTO {
    private UUID id;
    private String nombre;
    private String correo;
    private String telefono;
}
