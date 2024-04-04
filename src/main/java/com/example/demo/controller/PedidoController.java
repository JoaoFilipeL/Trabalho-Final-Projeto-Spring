package com.example.demo.controller;

import com.example.demo.model.Pedido;
import com.example.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@Valid @RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.salvar(pedido);
        return ResponseEntity.ok(novoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @Valid @RequestBody Pedido pedidoDetalhes) {
        return pedidoService.buscarPorId(id)
                .map(pedidoExistente -> {
                    // Atualiza os detalhes do pedido
                    // Aqui você pode redefinir o cliente ou os produtos, se necessário
                    pedidoExistente.setProdutos(pedidoDetalhes.getProdutos());
                    pedidoExistente.setCliente(pedidoDetalhes.getCliente());
                    // Recalcula o total do pedido
                    pedidoService.salvar(pedidoExistente);
                    return ResponseEntity.ok(pedidoExistente);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return pedidoService.buscarPorId(id)
                .map(pedido -> {
                    pedidoService.deletar(pedido.getId());
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
