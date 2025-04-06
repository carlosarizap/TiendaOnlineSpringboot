package com.evertec.tienda_online.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orden")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Orden {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;

    private BigDecimal total;

    private String estado;

    @Column(name = "creado_en", updatable = false, insertable = false)
    private Timestamp creadoEn;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdenDetalle> detalles;
}
