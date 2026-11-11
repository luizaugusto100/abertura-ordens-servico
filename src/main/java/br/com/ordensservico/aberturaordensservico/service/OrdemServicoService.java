package br.com.ordensservico.aberturaordensservico.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ordensservico.aberturaordensservico.dto.EncerramentoOrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.OrdemServicoRepository;

@Service 
public class OrdemServicoService {
    
    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, EquipamentoRepository equipamentoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public Optional<OrdemServico> cadastrar(OrdemServicoRequest dadosOrdemServico) {
        Optional<Equipamento> equipamento = equipamentoRepository.findById(dadosOrdemServico.getEquipamentoId());
        if (equipamento.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setDescricao(dadosOrdemServico.getDescricao());
        ordemServico.setEquipamento(equipamento.get());
        ordemServico.setDataAbertura(LocalDateTime.now());
        return Optional.of(ordemServicoRepository.save(ordemServico));
    }

    public List<OrdemServico> listarOrdensServico() {
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> buscarOrdemServicoPorId(Integer id) {
        return ordemServicoRepository.findById(id);
    }

    public Optional<OrdemServico> encerrar(Integer id, EncerramentoOrdemServicoRequest solucao) {
        Optional<OrdemServico> ordemServicoOptional = ordemServicoRepository.findById(id);

        boolean ordemServicoEncerrada = ordemServicoRepository
        .existsByIdAndDataEncerramentoIsNotNull(ordemServicoOptional.get().getId());

        if (ordemServicoEncerrada) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O registro da ordem de serviço já foi encerrado.");
        }

        OrdemServico ordemServico = ordemServicoOptional.get();
        ordemServico.setSolucao(solucao.getSolucao());
        ordemServico.setDataEncerramento(LocalDateTime.now());
        return Optional.of(ordemServicoRepository.save(ordemServico));
    }

    public List<OrdemServico> listarAbertas() {
        return ordemServicoRepository.findByDataEncerramentoIsNull();
    }

}
