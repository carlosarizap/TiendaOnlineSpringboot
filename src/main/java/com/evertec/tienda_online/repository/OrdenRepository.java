package com.evertec.tienda_online.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evertec.tienda_online.entity.Orden;

public interface OrdenRepository extends JpaRepository<Orden, UUID> {
    List<Orden> findByClienteId(UUID clienteId);
}

