package com.caiofpimentel.cadastrodecontatos.repository;
import com.caiofpimentel.cadastrodecontatos.model.Contato;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatosRepository extends CrudRepository<Contato, String> {

    


}
