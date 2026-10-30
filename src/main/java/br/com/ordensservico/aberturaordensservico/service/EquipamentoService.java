package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

    public Equipamento cadastrarEquipamento (Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }

    public List<Equipamento> listarEquipamentos() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarEquipamentoPorId(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public Equipamento atualizarEquipamento(Integer id, Equipamento equipamentonovoequipamento) {
        
        Optional<Equipamento> EquipamentoEncontrado = equipamentoRepository.findById(id);
        
        if (!EquipamentoEncontrado.isPresent()) {
            return null;
        }

        Equipamento equipamento2 = EquipamentoEncontrado.get();
        equipamento2.setNome(equipamentonovoequipamento.getNome());
        equipamento2.setNumeroPatrimonio(equipamentonovoequipamento.getNumeroPatrimonio());
        equipamento2.setSetor(equipamentonovoequipamento.getSetor());
        return equipamentoRepository.save(equipamento2);
    }

    public void deletarEquipamento(Integer id) {
        equipamentoRepository.deleteById(id);
    }


}