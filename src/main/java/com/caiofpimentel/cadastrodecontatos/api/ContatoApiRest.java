package com.caiofpimentel.cadastrodecontatos.api;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;


import com.caiofpimentel.cadastrodecontatos.model.Contato;

public interface ContatoApiRest {

    @GetMapping("/contatos")
    public ResponseEntity<List<Contato>> listarContatos();
}
