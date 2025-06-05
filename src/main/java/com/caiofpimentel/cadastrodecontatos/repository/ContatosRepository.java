package com.caiofpimentel.cadastrodecontatos.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.caiofpimentel.cadastrodecontatos.entities.Contato;



@Repository
public interface ContatosRepository extends JpaRepository<Contato, Integer> {

    Optional<Contato> findByTelefone(String telefone);

    
    @Transactional
    void deleteByTelefone(String telefone);


}
