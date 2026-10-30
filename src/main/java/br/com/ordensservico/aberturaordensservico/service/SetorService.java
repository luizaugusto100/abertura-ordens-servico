package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;

@Service 
public class SetorService {

    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public Setor cadastrarSetor(Setor setor) {
        return setorRepository.save(setor);
    }

    public List<Setor> listarSetores() {
        return setorRepository.findAll();
    }

    public Setor buscarSetorPorId(Integer id) {
        return setorRepository.findById(id).orElse(null);
    }

    public Setor atualizarSetor(Integer id, Setor setornovoSetor) {
        java.util.Optional<Setor> setorEncontrado = setorRepository.findById(id);
        if (!setorEncontrado.isPresent()) {
            return null;
        }

        Setor setor2 = setorEncontrado.get();
        setor2.setNome(setornovoSetor.getNome());
        return setorRepository.save(setor2);
    }

    public void deletarSetor(Integer id) {
        setorRepository.deleteById(id);
    }

}
