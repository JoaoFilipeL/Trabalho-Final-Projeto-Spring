package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(cliente -> ResponseEntity.ok().body(cliente))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente criar(@Valid @RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente clienteDetalhes) {
        return clienteService.buscarPorId(id)
                .map(cliente -> {
                    cliente.setNome(clienteDetalhes.getNome());
                    cliente.setCpf(clienteDetalhes.getCpf());
                    cliente.setEmail(clienteDetalhes.getEmail());
                    cliente.setTelefone(clienteDetalhes.getTelefone());
                    Cliente atualizado = clienteService.salvar(cliente);
                    return ResponseEntity.ok().body(atualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(cliente -> {
                    clienteService.deletar(cliente.getId());
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
