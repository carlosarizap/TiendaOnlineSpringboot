package com.evertec.tienda_online.controller;

import com.evertec.tienda_online.dto.ClienteDTO;
import com.evertec.tienda_online.dto.ClienteRegistroDTO;
import com.evertec.tienda_online.dto.LoginRequestDTO;
import com.evertec.tienda_online.entity.Cliente;
import com.evertec.tienda_online.mapper.ClienteMapper;
import com.evertec.tienda_online.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @PostMapping("/registro")
    public ResponseEntity<ClienteDTO> registrar(@RequestBody ClienteRegistroDTO dto) {
        Cliente cliente = Cliente.builder()
                .nombre(dto.getNombre())
                .correo(dto.getCorreo())
                .telefono(dto.getTelefono())
                .password(dto.getPassword())
                .build();
        Cliente registrado = clienteService.registrar(cliente);
        return ResponseEntity.ok(clienteMapper.toDTO(registrado));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        Cliente cliente = clienteService.login(loginRequest.getCorreo(), loginRequest.getPassword());
        if (cliente != null) {
            return ResponseEntity.ok(clienteMapper.toDTO(cliente));
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    @GetMapping
    public List<ClienteDTO> listarTodos() {
        return clienteService.obtenerTodos().stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable UUID id) {
        Cliente cliente = clienteService.obtenerPorId(id);
        return cliente != null
                ? ResponseEntity.ok(clienteMapper.toDTO(cliente))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
