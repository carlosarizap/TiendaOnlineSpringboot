package com.evertec.tienda_online.repository;

import com.evertec.tienda_online.entity.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TiendaRepository extends JpaRepository<Tienda, UUID> {
}
