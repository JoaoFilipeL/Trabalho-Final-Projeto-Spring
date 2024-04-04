package com.example.demo.service;

import com.example.demo.model.Pedido;
import com.example.demo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.example.demo.model.Produto;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido salvar(Pedido pedido) {
        // Calcula o total baseado nos produtos do pedido
        double total = Optional.ofNullable(pedido.getProdutos())
                .orElseGet(Collections::emptyList) // Retorna uma lista vazia se os produtos forem null
                .stream()
                .filter(Objects::nonNull) // Filtra qualquer produto que seja null
                .mapToDouble(produto -> Optional.ofNullable(produto.getPrecoPro()).orElse(0.0)) // Usa 0.0 para preço se for null
                .sum();
        pedido.setTotal(total);

        return pedidoRepository.save(pedido);
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }
}
