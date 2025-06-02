package com.caiofpimentel.cadastrodecontatos.Service;

import java.util.List;

import com.caiofpimentel.cadastrodecontatos.model.Contato;

public interface ContatoService {

    public List<Contato> listarContatos();

    public Contato buscarContatoPorId(String id);

    

}
