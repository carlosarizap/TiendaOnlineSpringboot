package com.evertec.tienda_online.service.impl;

import com.evertec.tienda_online.entity.Tienda;
import com.evertec.tienda_online.repository.TiendaRepository;
import com.evertec.tienda_online.service.TiendaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class TiendaServiceImpl implements TiendaService {

    private final TiendaRepository tiendaRepository;

    public TiendaServiceImpl(TiendaRepository tiendaRepository) {
        this.tiendaRepository = tiendaRepository;
    }

    @Override
    public List<Tienda> obtenerTodas() {
        return tiendaRepository.findAll();
    }

    @Override
    public Tienda obtenerPorId(UUID id) {
        return tiendaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tienda no encontrada"));
    }

    @Override
    public Tienda guardar(Tienda tienda) {
        if (tienda.getNombre() == null || tienda.getNombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la tienda es obligatorio");
        }
        return tiendaRepository.save(tienda);
    }

    @Override
    public void eliminar(UUID id) {
        if (!tiendaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tienda no encontrada para eliminar");
        }
        tiendaRepository.deleteById(id);
    }
}
