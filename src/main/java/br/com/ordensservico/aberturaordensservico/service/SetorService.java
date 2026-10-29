package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.model.setor;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;

@Service 
public class SetorService {

    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public setor cadastrarSetor(setor setor) {
        return setorRepository.save(setor);
    }

    public List<setor> listarSetores() {
        return setorRepository.findAll();
    }

    public setor buscarSetorPorId(Integer id) {
        return setorRepository.findById(id).orElse(null);
    }

    public setor atualizarSetor(setor setor) {
        return setorRepository.save(setor);
    }

    public void deletarSetor(Integer id) {
        setorRepository.deleteById(id);
    }

}
