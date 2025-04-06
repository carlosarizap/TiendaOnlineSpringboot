package com.evertec.tienda_online.controller;

import com.evertec.tienda_online.dto.OrdenDTO;
import com.evertec.tienda_online.entity.Orden;
import com.evertec.tienda_online.mapper.OrdenMapper;
import com.evertec.tienda_online.service.OrdenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    private final OrdenService ordenService;
    private final OrdenMapper ordenMapper;

    public OrdenController(OrdenService ordenService, OrdenMapper ordenMapper) {
        this.ordenService = ordenService;
        this.ordenMapper = ordenMapper;
    }

    @PostMapping
    public ResponseEntity<OrdenDTO> crearOrden(@RequestBody OrdenDTO ordenDTO) {
        Orden ordenGuardada = ordenService.crearOrden(ordenDTO); 
        return ResponseEntity.ok(ordenMapper.toDTO(ordenGuardada));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDTO> obtenerOrden(@PathVariable UUID id) {
        Orden orden = ordenService.obtenerPorId(id);
        return orden != null ? ResponseEntity.ok(ordenMapper.toDTO(orden)) : ResponseEntity.notFound().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public List<OrdenDTO> listarPorCliente(@PathVariable UUID clienteId) {
        return ordenService.listarPorCliente(clienteId).stream()
                .map(ordenMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<OrdenDTO> actualizarEstado(@PathVariable UUID id, @RequestParam String estado) {
        Orden actualizada = ordenService.actualizarEstado(id, estado);
        return ResponseEntity.ok(ordenMapper.toDTO(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable UUID id) {
        ordenService.cancelarOrden(id);
        return ResponseEntity.noContent().build();
    }
}
