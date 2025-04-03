package com.evertec.tienda_online.repository;

import com.evertec.tienda_online.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductoRepository extends JpaRepository<Producto, UUID> {
}
