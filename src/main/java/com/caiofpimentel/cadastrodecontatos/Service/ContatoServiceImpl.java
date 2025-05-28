package com.caiofpimentel.cadastrodecontatos.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiofpimentel.cadastrodecontatos.model.Contato;
import com.caiofpimentel.cadastrodecontatos.repository.ContatosRepository;

@Service
public class ContatoServiceImpl implements ContatoService {


    @Autowired
    private ContatosRepository contatosRepository;

    @Override
    public void cadastrarContato(String telefone, String nome, String email) {
        Contato contato = new Contato();
        contato.setTelefone(telefone);
        contato.setNome(nome);
        contato.setEmail(email);
        
        contatosRepository.save(contato);
       
    }

    @Override
    public void atualizarContato(String telefone, String nome, String email) {
        Optional<Contato> contatoExistente = contatosRepository.findById(telefone);
        if (contatoExistente.isPresent()) {
            Contato contato = contatoExistente.get();
            contato.setNome(nome);
            contato.setEmail(email);
            contatosRepository.save(contato);
        } else {
            throw new IllegalArgumentException("Contato não encontrado com o telefone: " + telefone);
        }
       
    }

    @Override
    public void excluirContato(String telefone) {
        Optional<Contato> contatoExistente = contatosRepository.findById(telefone);
        if (contatoExistente.isPresent()) {
            contatosRepository.delete(contatoExistente.get());
        } else {
            throw new IllegalArgumentException("Contato não encontrado com o telefone: " + telefone);
        }
        
    }

    @Override
    public void excluirContatoPorNome(String nome) {
        Optional<Contato> contatoExistente = contatosRepository.findById(nome);
        if (contatoExistente.isPresent()) {
            contatosRepository.delete(contatoExistente.get());
        } else {
            throw new IllegalArgumentException("Contato não encontrado com o nome: " + nome);
        }
    }

    @Override
    public Optional<Contato> buscarContato(String telefone) {
        return contatosRepository.findById(telefone);
        
    }

    

    @Override
    public Optional<Contato> buscarContatoPorNome(String nome) {
        return contatosRepository.findById(nome);
    }

    

    

    
}
