package com.example.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos = new ArrayList<>();

    private Double total;


    public Pedido() {
    }

    public Pedido(Cliente cliente, List<Produto> produtos, Double total) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.total = total;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }



    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        total += produto.getPrecoPro(); // Supondo que você tem um getter chamado getPrecoPro em Produto
    }


    public void removerProduto(Produto produto) {
        produtos.remove(produto);
        total -= produto.getPrecoPro(); // Supondo que você tem um getter chamado getPrecoPro em Produto
    }


    public void calcularTotal() {
        total = produtos.stream().mapToDouble(Produto::getPrecoPro).sum();
    }
}

