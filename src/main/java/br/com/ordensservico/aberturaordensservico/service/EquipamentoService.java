package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.dto.EquipamentoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;


@Service 
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    private final SetorRepository setorRepository;

    public EquipamentoService (EquipamentoRepository equipamentoRepository, SetorRepository setorRepository){
        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }

    public Optional<Equipamento> cadastrar( EquipamentoRequest equipamento) {

    Optional<Setor> setorEncontrado =
            setorRepository.findById(equipamento.getSetorId());

    if (setorEncontrado.isEmpty()) {
        return Optional.empty();
    }
    Equipamento equipamento2 = new Equipamento();
    equipamento2.setSetor(setorEncontrado.get());
    equipamento2.setNome(equipamento.getNome());
    equipamento2.setNumeroPatrimonio(equipamento.getNumeroPatrimonio());

    return Optional.of(equipamentoRepository.save(equipamento2));
    }


    public List<Equipamento> listarEquipamentos() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarEquipamentoPorId(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public Optional<Equipamento> atualizarEquipamento(Integer id, EquipamentoRequest equipamentonovoequipamento) {
        
        Optional<Equipamento> EquipamentoEncontrado = equipamentoRepository.findById(id);
        
        if (!EquipamentoEncontrado.isPresent()) {
            return Optional.empty();
        }

        Optional<Setor> setorEncontrado = setorRepository.findById(equipamentonovoequipamento.getSetorId());
        if (setorEncontrado.isEmpty()) {
            return Optional.empty();
        }
            
        

        Equipamento equipamento2 = EquipamentoEncontrado.get();

        equipamento2.setNome(equipamentonovoequipamento.getNome());
        equipamento2.setNumeroPatrimonio(equipamentonovoequipamento.getNumeroPatrimonio());
        equipamento2.setSetor(setorEncontrado.get());
            
        return Optional.of(equipamentoRepository.save(equipamento2));
    }

    public void deletarEquipamento(Integer id) {
        equipamentoRepository.deleteById(id);
    }


}