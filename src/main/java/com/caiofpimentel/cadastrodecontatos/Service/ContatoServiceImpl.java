package com.caiofpimentel.cadastrodecontatos.Service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiofpimentel.cadastrodecontatos.entities.Contato;
import com.caiofpimentel.cadastrodecontatos.repository.ContatosRepository;

@Service
public class ContatoServiceImpl implements ContatoService {


    @Autowired
    private ContatosRepository contatosRepository;

    @Override
    public List<Contato> listarContatos(){
        List<Contato> contatos = new ArrayList<>();
        contatosRepository.findAll().forEach(contatos::add);
        return contatos;

    }

    @Override
    public Contato buscarContatoPorId(Integer id) {
        return contatosRepository.findById(id).orElseThrow(() -> new RuntimeException("Contato não encontrado com o ID: " + id));
        
    }
     @Override
    public Contato buscarContatoPorTelefone(String telefone) {
        return contatosRepository.findByTelefone(telefone)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado com o telefone: " + telefone));
    }
   

    @Override
    public void save(Contato contato) {
        contatosRepository.saveAndFlush(contato);
    }

     @Override
    public void deleteContatoPorId(Integer id) {
       contatosRepository.deleteById(id);
    }

    @Override
    public void deleteContatoPorTelefone(String telefone) {
       contatosRepository.deleteByTelefone(telefone);
    }

    @Override
    public void atualizarContato(Integer id, Contato contato) {
        Contato contatoPassado = contatosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado com o ID: " + id));

        Contato contatoAtualizado = Contato.builder()
            .email(contato.getEmail() != null ? contato.getEmail() : contatoPassado.getEmail())
            .nome(contato.getNome() != null ? contato.getNome() : contatoPassado.getNome())
            .telefone(contato.getTelefone() != null ? contato.getTelefone() : contatoPassado.getTelefone())
            .id(id)
            .build();      
        
        contatosRepository.saveAndFlush(contatoAtualizado);
        
        
    }

    

    

    

    

    
}
