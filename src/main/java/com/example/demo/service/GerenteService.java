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

    public Optional<Gerente> buscarPorId(Long id) {
        return gerenteRepository.findById(id);
    }

    public Gerente salvar(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }

    public void deletar(Long id) {
        gerenteRepository.deleteById(id);
    }
}

