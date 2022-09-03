package com.example.agendacontatos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agendacontatos.model.Contato;
import com.example.agendacontatos.repository.ContatoRepository;

@RestController
class TeskControllerAPI {
    @Autowired 
    ContatoRepository repository;


    @GetMapping("/detalheContato")
    public List<Contato> detalheContato(){
        return repository.findAll();
    }
}
