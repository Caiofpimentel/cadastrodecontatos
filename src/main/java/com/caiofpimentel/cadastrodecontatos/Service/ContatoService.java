package com.caiofpimentel.cadastrodecontatos.Service;

import java.util.List;

import com.caiofpimentel.cadastrodecontatos.entities.Contato;

public interface ContatoService {

    public List<Contato> listarContatos();

    public Contato buscarContatoPorId(Integer id);

    public Contato buscarContatoPorTelefone(String telefone);   

    void save(Contato contato);
     

    public void atualizarContato(Integer id, Contato contato);

    public void deleteContatoPorId(Integer id);

    public void deleteContatoPorTelefone(String telefone);

}
