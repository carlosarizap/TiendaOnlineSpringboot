package com.evertec.tienda_online.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue
    private UUID id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;

    @Column(name = "creado_en", updatable = false, insertable = false)
    private java.sql.Timestamp creadoEn;
}
