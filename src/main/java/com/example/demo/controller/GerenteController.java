package com.example.demo.controller;

import com.example.demo.model.Gerente;
import com.example.demo.service.GerenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;

    @GetMapping
    public List<Gerente> listarTodos() {
        return gerenteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gerente> buscarPorId(@PathVariable Long id) {
        return gerenteService.buscarPorId(id)
                .map(gerente -> ResponseEntity.ok().body(gerente))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Gerente criar(@Valid @RequestBody Gerente gerente) {
        return gerenteService.salvar(gerente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gerente> atualizar(@PathVariable Long id, @Valid @RequestBody Gerente gerenteDetalhes) {
        return gerenteService.buscarPorId(id)
                .map(gerente -> {
                    gerente.setNome(gerenteDetalhes.getNome());
                    gerente.setCpf(gerenteDetalhes.getCpf());
                    gerente.setSalario(gerenteDetalhes.getSalario());
                    gerente.setSenha(gerenteDetalhes.getSenha());
                    gerente.setNumFuncGerenciados(gerenteDetalhes.getNumFuncGerenciados());
                    Gerente atualizado = gerenteService.salvar(gerente);
                    return ResponseEntity.ok().body(atualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return gerenteService.buscarPorId(id)
                .map(gerente -> {
                    gerenteService.deletar(gerente.getId());
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
