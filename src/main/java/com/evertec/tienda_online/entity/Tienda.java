package com.evertec.tienda_online.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "tienda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tienda {

    @Id
    @GeneratedValue
    private UUID id;

    private String nombre;
    private String direccion;
    private String telefono;

    @Column(name = "creado_en", updatable = false, insertable = false)
    private Timestamp creadoEn;
}
