package com.evertec.tienda_online.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDTO {
    private UUID id;
    private UUID clienteId;
    private String nombreCliente;
    private String correoCliente;
    private UUID tiendaId;
    private String nombreTienda; 
    private BigDecimal total;
    private String estado;
    private List<OrdenDetalleDTO> detalles;
    private Timestamp creadoEn;
}

