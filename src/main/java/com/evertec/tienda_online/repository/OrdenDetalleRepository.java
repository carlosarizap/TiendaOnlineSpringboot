package com.evertec.tienda_online.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evertec.tienda_online.entity.OrdenDetalle;

public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalle, UUID> {
}
