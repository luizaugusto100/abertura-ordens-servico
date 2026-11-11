package br.com.ordensservico.aberturaordensservico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ordensservico.aberturaordensservico.dto.EquipamentoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.service.EquipamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @PostMapping
    public ResponseEntity<Equipamento> cadastrar(
            @Valid @RequestBody EquipamentoRequest equipamento) {

        Optional<Equipamento> equipamentoCadastrado = equipamentoService.cadastrar(equipamento);

        if (equipamentoCadastrado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(equipamentoCadastrado.get());
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> listar() {
        return ResponseEntity.ok(
                equipamentoService.listarEquipamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarEquipamentoPorId(
            @PathVariable Integer id) {
        Optional<Equipamento> equipamento = equipamentoService.buscarEquipamentoPorId(id);
            if (equipamento.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        return ResponseEntity.ok(equipamento.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Equipamento>> atualizarEquipamento(@PathVariable Integer id,
            @Valid @RequestBody EquipamentoRequest equipamento) {

        Optional<Equipamento> equipamentoAtualizado = equipamentoService.atualizarEquipamento(id, equipamento);

        if (equipamentoAtualizado.isPresent()) {

            return ResponseEntity.ok(equipamentoAtualizado);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEquipamento(@PathVariable Integer id) {
        equipamentoService.deletarEquipamento(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class SetorNaoEncontradoException
            extends RuntimeException {

        public SetorNaoEncontradoException() {
            super("Setor não encontrado!");
        }
    }

}
