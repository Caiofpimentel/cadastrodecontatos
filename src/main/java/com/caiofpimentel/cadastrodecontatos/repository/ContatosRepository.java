package com.caiofpimentel.cadastrodecontatos.repository;
import com.caiofpimentel.cadastrodecontatos.model.Contato;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ContatosRepository extends JpaRepository<Contato, String> {

    Iterable<Contato> findByNomeContainingIgnoreCase(String nome);

    


}
