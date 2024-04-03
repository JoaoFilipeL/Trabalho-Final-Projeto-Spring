package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(produto -> ResponseEntity.ok().body(produto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produto criar(@Valid @RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produtoDetalhes) {
        return produtoService.buscarPorId(id)
                .map(produto -> {
                    produto.setNomePro(produtoDetalhes.getNomePro());
                    produto.setDescricaoPro(produtoDetalhes.getDescricaoPro());
                    produto.setPrecoPro(produtoDetalhes.getPrecoPro());
                    produto.setQuantidadeEmEstoquePro(produtoDetalhes.getQuantidadeEmEstoquePro());
                    Produto atualizado = produtoService.salvar(produto);
                    return ResponseEntity.ok().body(atualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(produto -> {
                    produtoService.deletar(produto.getIdPro());
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
