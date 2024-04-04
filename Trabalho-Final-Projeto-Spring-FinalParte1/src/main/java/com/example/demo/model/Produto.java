package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPro;

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nomePro;

    private String descricaoPro;

    @NotNull(message = "O preço do produto é obrigatório.")
    @Min(value = 0, message = "O preço do produto deve ser maior ou igual a zero.")
    private Double precoPro;

    @NotNull(message = "A quantidade em estoque é obrigatória.")
    @Min(value = 0, message = "A quantidade em estoque deve ser maior ou igual a zero.")
    private Integer quantidadeEmEstoquePro;


    public Long getIdPro() {
        return idPro;
    }

    public String getNomePro() {
        return nomePro;
    }

    public String getDescricaoPro() {
        return descricaoPro;
    }

    public Double getPrecoPro() {
        return precoPro;
    }

    public Integer getQuantidadeEmEstoquePro() {
        return quantidadeEmEstoquePro;
    }


    public void setIdPro(Long idPro) { this.idPro = idPro; }

    public void setNomePro(String nomePro) {
        this.nomePro = nomePro;
    }

    public void setDescricaoPro(String descricaoPro) {
        this.descricaoPro = descricaoPro;
    }

    public void setPrecoPro(Double precoPro) {
        this.precoPro = precoPro;
    }

    public void setQuantidadeEmEstoquePro(Integer quantidadeEmEstoquePro) {
        this.quantidadeEmEstoquePro = quantidadeEmEstoquePro;
    }
}

