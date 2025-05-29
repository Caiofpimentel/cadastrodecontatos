package com.caiofpimentel.cadastrodecontatos.Service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.caiofpimentel.cadastrodecontatos.model.Contato;
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

    

    

    
}
