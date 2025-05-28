package com.caiofpimentel.cadastrodecontatos.Service;

import java.util.Optional;

import com.caiofpimentel.cadastrodecontatos.model.Contato;

public interface ContatoService {

    public void cadastrarContato(String telefone, String nome, String email);
    public void atualizarContato(String telefone, String nome, String email);
    public void excluirContato(String telefone);
    public void excluirContatoPorNome(String nome);
    public Optional<Contato> buscarContato(String telefone);
    public Optional<Contato> buscarContatoPorNome(String nome);

}
