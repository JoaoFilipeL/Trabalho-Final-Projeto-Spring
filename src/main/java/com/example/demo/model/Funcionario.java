package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {
    @Min(value = 0, message = "O salário deve ser maior ou igual a zero")
    private double salario;

    public Funcionario() {
        super();
    }

    public Funcionario(String nome, String cpf, double salario) {
        super(nome, cpf);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
