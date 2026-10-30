package br.com.ordensservico.aberturaordensservico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.service.EquipamentoService;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @PostMapping
    public ResponseEntity<Equipamento> cadastrar(@RequestBody Equipamento equipamento) {

        Equipamento equipamentoCadastrado =
                equipamentoService.cadastrarEquipamento(equipamento);

        /*if (equipamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }*/

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(equipamentoCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> listar() {
        return ResponseEntity.ok(
                equipamentoService.listarEquipamentos()
        );
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Equipamento> buscarEquipamentoPorId(
        @PathVariable Integer id){
            Optional<Equipamento> equipamento = equipamentoService.buscarEquipamentoPorId(id);
        
        return ResponseEntity.ok(equipamento.get());
        }

    @PutMapping ("/{id}")
    public ResponseEntity<Equipamento> atualizarEquipamento(@PathVariable Integer id, @RequestBody Equipamento equipamento) {
        Equipamento equipamentoAtualizado = equipamentoService.atualizarEquipamento(id, equipamento);
        return ResponseEntity.ok(equipamentoAtualizado);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletarEquipamento(@PathVariable Integer id) {
        equipamentoService.deletarEquipamento(id);
        return ResponseEntity.noContent().build();
    }

}
