package br.com.ordensservico.aberturaordensservico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ordensservico.aberturaordensservico.model.setor;

public interface SetorRepository

    extends JpaRepository<setor, Integer> {
    
}
