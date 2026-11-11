package br.com.ordensservico.aberturaordensservico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordensservico.aberturaordensservico.dto.EncerramentoOrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.service.OrdemServicoService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;

    }

    @PostMapping 
    public ResponseEntity<Optional<OrdemServico>> cadastrarOrdemServico(@Valid @RequestBody OrdemServicoRequest ordemServico) {
        Optional<OrdemServico> ordemServicoCadastrada = ordemServicoService.cadastrar(ordemServico);

        if (ordemServicoCadastrada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(ordemServicoCadastrada);
    }

    @GetMapping 
    public ResponseEntity<List<OrdemServico>> listarOrdensServico() {
        return ResponseEntity.ok(ordemServicoService.listarOrdensServico());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<OrdemServico>> buscarOrdemServicoPorId(@PathVariable Integer id) {
        Optional<OrdemServico> ordemServico = ordemServicoService.buscarOrdemServicoPorId(id);

        if (ordemServico.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(ordemServico);
    }

    @PatchMapping ("/{id}/encerramento")
    public ResponseEntity<Optional<OrdemServico>> encerrarOrdemServico(@PathVariable Integer id, @Valid @RequestBody EncerramentoOrdemServicoRequest solucao) {
        Optional<OrdemServico> ordemServicoEncerrada = ordemServicoService.encerrar(id, solucao);

        if (ordemServicoEncerrada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(ordemServicoEncerrada);
    }

    @GetMapping("/abertas")
    public ResponseEntity<List<OrdemServico>> ListarOrdensServicoAbertas() {
        List<OrdemServico> ordensServicoAbertas = ordemServicoService.listarAbertas();
        return ResponseEntity.ok(ordensServicoAbertas);
    }
    
}
