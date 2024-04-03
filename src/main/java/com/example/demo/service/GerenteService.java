package com.example.demo.service;

import com.example.demo.model.Gerente;
import com.example.demo.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    public List<Gerente> listarTodos() {
        return gerenteRepository.findAll();
    }

    public Gerente criar(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }

    public Gerente atualizar(Long id, Gerente gerenteDetalhes) {
        return gerenteRepository.findById(id).map(gerente -> {
            gerente.setNome(gerenteDetalhes.getNome());
            gerente.setCpf(gerenteDetalhes.getCpf());
            gerente.setSalario(gerenteDetalhes.getSalario());
            gerente.setSenha(gerenteDetalhes.getSenha());
            gerente.setNumFuncGerenciados(gerenteDetalhes.getNumFuncGerenciados());
            return gerenteRepository.save(gerente);
        }).orElse(null);
    }

    public boolean deletar(Long id) {
        if(gerenteRepository.existsById(id)) {
            gerenteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Gerente> buscarPorId(Long id) {
        return gerenteRepository.findById(id);
    }
}
