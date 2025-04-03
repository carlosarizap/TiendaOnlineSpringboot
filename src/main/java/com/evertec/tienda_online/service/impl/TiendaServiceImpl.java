package com.evertec.tienda_online.service.impl;

import com.evertec.tienda_online.entity.Tienda;
import com.evertec.tienda_online.repository.TiendaRepository;
import com.evertec.tienda_online.service.TiendaService;
import org.springframework.stereotype.Service;

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
        return tiendaRepository.findById(id).orElse(null);
    }

    @Override
    public Tienda guardar(Tienda tienda) {
        return tiendaRepository.save(tienda);
    }

    @Override
    public void eliminar(UUID id) {
        tiendaRepository.deleteById(id);
    }
}
