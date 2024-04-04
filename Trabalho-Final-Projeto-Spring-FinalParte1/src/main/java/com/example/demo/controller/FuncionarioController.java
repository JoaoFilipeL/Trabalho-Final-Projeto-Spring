package com.example.demo.controller;

import com.example.demo.model.Funcionario;
import com.example.demo.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> listarTodos() {
        return funcionarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        return funcionarioService.buscarPorId(id)
                .map(funcionario -> ResponseEntity.ok().body(funcionario))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Funcionario criar(@Valid @RequestBody Funcionario funcionario) {
        return funcionarioService.salvar(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @Valid @RequestBody Funcionario funcionarioDetalhes) {
        return funcionarioService.buscarPorId(id)
                .map(funcionario -> {
                    funcionario.setNome(funcionarioDetalhes.getNome());
                    funcionario.setCpf(funcionarioDetalhes.getCpf());
                    funcionario.setSalario(funcionarioDetalhes.getSalario());
                    Funcionario atualizado = funcionarioService.salvar(funcionario);
                    return ResponseEntity.ok().body(atualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return funcionarioService.buscarPorId(id)
                .map(funcionario -> {
                    funcionarioService.deletar(funcionario.getId());
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

