package com.caiofpimentel.cadastrodecontatos.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.caiofpimentel.cadastrodecontatos.Service.ContatoService;
import com.caiofpimentel.cadastrodecontatos.api.ContatoApiRest;
import com.caiofpimentel.cadastrodecontatos.entities.Contato;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contato")
public class ContatoController implements ContatoApiRest {   
    
    private final ContatoService contatoService;    
    @GetMapping("/listar")
    public ResponseEntity<List<Contato>> listarContatos() {
        List<Contato> contatos = new ArrayList<>();
        contatoService.listarContatos().forEach(contatos::add);
        return ResponseEntity.ok(contatos);
    
    }
    
    // Faz requisições passando as variaveis na URL, por exemplo: /contato/listar/1
    @GetMapping("/listar/{id}")
    public ResponseEntity<Contato> buscarContatoPorId(@PathVariable Integer id) {
        Contato contato = contatoService.buscarContatoPorId(id);
        if (contato == null) {
            throw new ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, "Contato não encontrado"
            );
        }
        return ResponseEntity.ok(contato);
    }
    // Faz requisições passando as variaveis como parâmetros, por exemplo: /contato/telefone?telefone=123456789
    @GetMapping("/telefone")
    public ResponseEntity<Contato> buscarContatoPorTelefone(@RequestParam String telefone) {

        return ResponseEntity.ok(contatoService.buscarContatoPorTelefone(telefone));


    }

    @PostMapping("/novocontato")
    public ResponseEntity<Void> save(@RequestBody Contato contato) {
        
        contatoService.save(contato);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Void> atualizarContato(@RequestParam Integer id, @RequestBody Contato contato) {
        contatoService.atualizarContato(id, contato);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/deletar/id")
    public ResponseEntity<Void> deleteContatoPorId(@RequestParam Integer id) {
        contatoService.deleteContatoPorId(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/telefone")
    public ResponseEntity<Void> deleteContatoByTelefone(@RequestParam String telefone) {
        contatoService.deleteContatoPorTelefone(telefone);
        return ResponseEntity.ok().build();
    }

    




    

    @ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> handleNotFoundException(ResponseStatusException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", ex.getReason());
		body.put("status", ex.getStatusCode());
		return new ResponseEntity<>(body, ex.getStatusCode());
	} 

    



}
