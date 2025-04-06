package com.evertec.tienda_online.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.evertec.tienda_online.dto.OrdenDTO;
import com.evertec.tienda_online.entity.Cliente;
import com.evertec.tienda_online.entity.Orden;
import com.evertec.tienda_online.entity.OrdenDetalle;
import com.evertec.tienda_online.entity.Producto;
import com.evertec.tienda_online.entity.Tienda;
import com.evertec.tienda_online.repository.ClienteRepository;
import com.evertec.tienda_online.repository.OrdenRepository;
import com.evertec.tienda_online.repository.ProductoRepository;
import com.evertec.tienda_online.repository.TiendaRepository;
import com.evertec.tienda_online.service.OrdenService;

import jakarta.transaction.Transactional;

@Service
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepo;
    private final ClienteRepository clienteRepo;
    private final TiendaRepository tiendaRepo;
    private final ProductoRepository productoRepo;

    public OrdenServiceImpl(OrdenRepository ordenRepo, ClienteRepository clienteRepo,
                            TiendaRepository tiendaRepo, ProductoRepository productoRepo) {
        this.ordenRepo = ordenRepo;
        this.clienteRepo = clienteRepo;
        this.tiendaRepo = tiendaRepo;
        this.productoRepo = productoRepo;
    }

    @Override
    @Transactional
    public Orden crearOrden(OrdenDTO dto) {
        Cliente cliente = clienteRepo.findById(dto.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no existe"));
        Tienda tienda = tiendaRepo.findById(dto.getTiendaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tienda no existe"));

        Orden orden = Orden.builder()
                .cliente(cliente)
                .tienda(tienda)
                .total(dto.getTotal())
                .estado("pendiente")
                .build();

        List<OrdenDetalle> detalles = dto.getDetalles().stream().map(d -> {
            Producto producto = productoRepo.findById(d.getProductoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no existe"));

            if (producto.getStock() < d.getCantidad()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - d.getCantidad());
            productoRepo.save(producto);

            return OrdenDetalle.builder()
                    .orden(orden)
                    .producto(producto)
                    .cantidad(d.getCantidad())
                    .precioUnitario(d.getPrecioUnitario())
                    .build();
        }).toList();

        orden.setDetalles(detalles);
        return ordenRepo.save(orden);
    }

    @Override
    public Orden obtenerPorId(UUID id) {
        return ordenRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orden no encontrada"));
    }

    @Override
    public List<Orden> listarPorCliente(UUID clienteId) {
        boolean clienteExiste = clienteRepo.existsById(clienteId);
        
        if (!clienteExiste) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }
    
        return ordenRepo.findByClienteId(clienteId);
    }
    @Override
    public Orden actualizarEstado(UUID id, String nuevoEstado) {
        Orden orden = ordenRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orden no encontrada"));
        orden.setEstado(nuevoEstado);
        return ordenRepo.save(orden);
    }

    @Override
    @Transactional
    public void cancelarOrden(UUID id) {
        Orden orden = ordenRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orden no encontrada"));

        for (OrdenDetalle detalle : orden.getDetalles()) {
            Producto producto = detalle.getProducto();
            producto.setStock(producto.getStock() + detalle.getCantidad());
            productoRepo.save(producto);
        }

        ordenRepo.delete(orden);
    }
}
