package com.example.demo.controller;

import com.example.demo.model.Gerente;
import com.example.demo.service.GerenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;

    @GetMapping
    public List<Gerente> listarTodos() {
        return gerenteService.listarTodos();
    }

    @PostMapping
    public Gerente criar(@Valid @RequestBody Gerente gerente) {
        return gerenteService.criar(gerente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Gerente gerente) {
        Gerente gerenteAtualizado = gerenteService.atualizar(id, gerente);
        if (gerenteAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ID informado não existe.");
        }
        return ResponseEntity.ok(gerenteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        boolean sucesso = gerenteService.deletar(id);
        if (sucesso) {
            return ResponseEntity.ok("Gerente com ID " + id + " foi removido com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ID informado não existe.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Gerente> gerente = gerenteService.buscarPorId(id);
        if (gerente.isPresent()) {
            return ResponseEntity.ok(gerente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
