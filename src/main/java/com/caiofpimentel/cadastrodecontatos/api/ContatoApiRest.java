package com.caiofpimentel.cadastrodecontatos.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.caiofpimentel.cadastrodecontatos.model.Contato;

public interface ContatoApiRest {

    @PostMapping("/novoContato")
    public void cadastrarContato(String telefone, String nome, String email);

    @GetMapping("/buscarContato/{nome}")
    public Contato buscarContato(String nome);


    @PostMapping("/buscarContato/{telefone}")
    public Contato buscarContatoTelefone(String telefone);


    @PostMapping("/atualizarContato/{nome}")
    public void atualizarContato(String telefone, String nome, String email);

    
    @DeleteMapping("/excluirContato/{telefone}")
    public void excluirContato(String telefone);

    @DeleteMapping("/excluirContato/{nome}")
    public void excluirContatoPorNome(String nome);

}
