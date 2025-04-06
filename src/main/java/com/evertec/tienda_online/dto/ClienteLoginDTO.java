package com.evertec.tienda_online.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClienteLoginDTO {
    private String correo;
    private String password;
}
