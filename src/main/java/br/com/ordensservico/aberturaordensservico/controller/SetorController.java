package br.com.ordensservico.aberturaordensservico.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.service.SetorService;

@RestController 
@RequestMapping ("/setores")
public class SetorController { 

    private final SetorService setorService;

    public SetorController (SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping 
    public ResponseEntity<Setor> cadastrarSetor(@RequestBody Setor setor) {
        Setor novoSetor = setorService.cadastrarSetor(setor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSetor);
    }

    @GetMapping 
    public ResponseEntity<java.util.List<Setor>> listarSetores() {
        java.util.List<Setor> setores = setorService.listarSetores();
        return ResponseEntity.ok(setores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setor> buscarSetorPorId(@PathVariable Integer id) {
        Setor setor = setorService.buscarSetorPorId(id);
        return ResponseEntity.ok(setor);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Setor> atualizarSetor(@PathVariable Integer id, @RequestBody Setor setor) {
        Setor setorAtualizado = setorService.atualizarSetor(id, setor);
        return ResponseEntity.ok(setorAtualizado);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletarSetor(@PathVariable Integer id) {
        setorService.deletarSetor(id);
        return ResponseEntity.noContent().build();
    }

}


