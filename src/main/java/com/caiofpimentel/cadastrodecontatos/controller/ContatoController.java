package com.caiofpimentel.cadastrodecontatos.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.caiofpimentel.cadastrodecontatos.Service.ContatoService;
import com.caiofpimentel.cadastrodecontatos.api.ContatoApiRest;
import com.caiofpimentel.cadastrodecontatos.model.Contato;

@RestController
public class ContatoController implements ContatoApiRest {

    // Injeção de dependência do serviço de contato
    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping("/novoContato")
    public void cadastrarContato(String telefone, String nome, String email) {
        
        if(contatoService.buscarContato(telefone).isPresent()) {
            throw new IllegalArgumentException("Contato já cadastrado com o telefone: " + telefone);
        }else contatoService.cadastrarContato(telefone, nome, email);        
        
        contatoService.cadastrarContato(telefone, nome, email);
    }

    @Override
    public Contato buscarContato(String nome) {
        Optional<Contato> contatoEncontrado = contatoService.buscarContatoPorNome(nome);
        if (contatoEncontrado.isPresent()) {
            return contatoEncontrado.get();
        } else {
            throw new IllegalArgumentException("Contato não encontrado com o nome: " + nome);
        }
    }
    @Override
    public Contato buscarContatoTelefone(String telefone) {
        Optional<Contato> contatoEncontrado = contatoService.buscarContato(telefone);
        if (contatoEncontrado.isPresent()) {
            return contatoEncontrado.get();
        } else {
            throw new IllegalArgumentException("Contato não encontrado com o telefone: " + telefone);
        }
    }

    @Override
    public void atualizarContato(String telefone, String nome, String email) {
        Optional<Contato> contatoExistente = contatoService.buscarContato(telefone);
        if (contatoExistente.isPresent()) {
            contatoService.atualizarContato(telefone, nome, email);
        } else {
            throw new IllegalArgumentException("Contato não encontrado com o telefone: " + telefone);
        }
    }

    @Override
    public void excluirContato(String telefone) {
        if(contatoService.buscarContato(telefone).isEmpty()) {
            throw new IllegalArgumentException("Contato não encontrado com o telefone: " + telefone);
        }
        else {
        contatoService.excluirContato(telefone);
        }
    }

    @Override
    public void excluirContatoPorNome(String nome) {
        if (contatoService.buscarContatoPorNome(nome).isEmpty()) {
            throw new IllegalArgumentException("Contato não encontrado com o nome: " + nome);
        }
        else {
            contatoService.excluirContatoPorNome(nome);
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> handleNotFoundException(ResponseStatusException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", ex.getReason());
		body.put("status", ex.getStatusCode());
		return new ResponseEntity<>(body, ex.getStatusCode());
	} 

    



}
