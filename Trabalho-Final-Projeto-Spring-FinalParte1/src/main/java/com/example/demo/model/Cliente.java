package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa {
    private String email;
    private String telefone;


    public Cliente() {
        super();
    }

    public Cliente(String nome, String cpf, String email, String telefone) {
        super(nome, cpf);
        this.email = email;
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
