package com.example.agendacontatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agendacontatos.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    Contato findById(long id);
}
