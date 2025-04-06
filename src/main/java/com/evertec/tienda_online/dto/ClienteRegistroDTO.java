package com.evertec.tienda_online.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClienteRegistroDTO {
    private String nombre;
    private String correo;
    private String telefono;
    private String password;
}
