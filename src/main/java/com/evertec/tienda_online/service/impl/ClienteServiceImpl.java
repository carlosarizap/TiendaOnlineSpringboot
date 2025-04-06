package com.evertec.tienda_online.service.impl;

import com.evertec.tienda_online.entity.Cliente;
import com.evertec.tienda_online.repository.ClienteRepository;
import com.evertec.tienda_online.service.ClienteService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public ClienteServiceImpl(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Cliente registrar(Cliente cliente) {
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente login(String correo, String password) {
        Cliente cliente = clienteRepository.findByCorreo(correo).orElse(null);
        if (cliente != null && passwordEncoder.matches(password, cliente.getPassword())) {
            return cliente;
        }
        return null;
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente obtenerPorId(UUID id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(UUID id) {
        clienteRepository.deleteById(id);
    }
}
