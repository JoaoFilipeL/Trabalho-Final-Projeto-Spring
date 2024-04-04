package com.example.demo.controller;

import com.example.demo.model.Pessoa;
import com.example.demo.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listarTodas() {
        return pessoaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return pessoaService.buscarPorId(id)
                .map(pessoa -> ResponseEntity.ok().body(pessoa))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pessoa criar(@Valid @RequestBody Pessoa pessoa) {
        return pessoaService.salvar(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @Valid @RequestBody Pessoa pessoaDetalhes) {
        return pessoaService.buscarPorId(id)
                .map(pessoa -> {
                    pessoa.setNome(pessoaDetalhes.getNome());
                    pessoa.setCpf(pessoaDetalhes.getCpf());
                    Pessoa atualizada = pessoaService.salvar(pessoa);
                    return ResponseEntity.ok().body(atualizada);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return pessoaService.buscarPorId(id)
                .map(pessoa -> {
                    pessoaService.deletar(pessoa.getId());
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
