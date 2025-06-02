package com.caiofpimentel.cadastrodecontatos.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.caiofpimentel.cadastrodecontatos.Service.ContatoService;
import com.caiofpimentel.cadastrodecontatos.api.ContatoApiRest;
import com.caiofpimentel.cadastrodecontatos.model.Contato;

@RestController
public class ContatoController implements ContatoApiRest {

    // Injeção de dependência do serviço de contato
    private ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping("/contatos")
    public ResponseEntity<List<Contato>> listarContatos() {
        List<Contato> contatos = new ArrayList<>();
        contatoService.listarContatos().forEach(contatos::add);
        return ResponseEntity.ok(contatos);
    
    }

    @GetMapping("/contato/{id}")
    public ResponseEntity<Contato> buscarContatoPorId(@PathVariable String id) {
        Contato contato = contatoService.buscarContatoPorId(id);
        if (contato == null) {
            throw new ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, "Contato não encontrado"
            );
        }
        return ResponseEntity.ok(contato);
    }

    

    @ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> handleNotFoundException(ResponseStatusException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", ex.getReason());
		body.put("status", ex.getStatusCode());
		return new ResponseEntity<>(body, ex.getStatusCode());
	} 

    



}
