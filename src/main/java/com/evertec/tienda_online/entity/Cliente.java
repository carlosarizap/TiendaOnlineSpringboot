package com.evertec.tienda_online.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "cliente")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Cliente {

    @Id
    @GeneratedValue
    private UUID id;

    private String nombre;

    @Column(unique = true)
    private String correo;

    private String telefono;

    private String password;

    @Column(name = "creado_en", updatable = false, insertable = false)
    private java.sql.Timestamp creadoEn;
}
