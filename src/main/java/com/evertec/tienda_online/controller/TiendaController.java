package com.evertec.tienda_online.controller;

import com.evertec.tienda_online.dto.TiendaDTO;
import com.evertec.tienda_online.entity.Tienda;
import com.evertec.tienda_online.mapper.TiendaMapper;
import com.evertec.tienda_online.service.TiendaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tiendas")
public class TiendaController {

    private final TiendaService tiendaService;
    private final TiendaMapper tiendaMapper;

    public TiendaController(TiendaService tiendaService, TiendaMapper tiendaMapper) {
        this.tiendaService = tiendaService;
        this.tiendaMapper = tiendaMapper;
    }

    @GetMapping
    public List<TiendaDTO> listar() {
        return tiendaService.obtenerTodas()
                .stream()
                .map(tiendaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TiendaDTO obtener(@PathVariable UUID id) {
        Tienda tienda = tiendaService.obtenerPorId(id);
        return tiendaMapper.toDTO(tienda);
    }

    @PostMapping
    public TiendaDTO crear(@RequestBody TiendaDTO tiendaDTO) {
        Tienda tienda = tiendaMapper.toEntity(tiendaDTO);
        Tienda guardada = tiendaService.guardar(tienda);
        return tiendaMapper.toDTO(guardada);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable UUID id) {
        tiendaService.eliminar(id);
    }
}
