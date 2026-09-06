package com.br.checkAluno.Presencas;


import com.br.checkAluno.Resonsaveis.ResponsavelDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("presenca")
public class PresencaController {

    PresencaService presencaService;

    public PresencaController(PresencaService presencaService) {
        this.presencaService = presencaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PresencaDTO>> listar(){
        List<PresencaDTO> Presencas = presencaService.listarPresenca();
        return ResponseEntity.ok(Presencas);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> listarPorID(@PathVariable Long id) {
        PresencaDTO presencaDTO = presencaService.listarPorId(id);
        if (presencaDTO != null) {
            return ResponseEntity.ok(presencaDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario com " + id + " Não encontrado");
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody PresencaDTO presencaDTO) {
        presencaService.criar(presencaDTO);
        return ResponseEntity.ok("Criado com sucesso");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody PresencaDTO presencaDTO) {
        PresencaDTO presenca =  presencaService.atualizar(id, presencaDTO);
        if (presenca != null) {
            return ResponseEntity.ok(presenca);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Presença com id "+ id + " Não encontrado");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        presencaService.deletar(id);
        return ResponseEntity.ok("Presença com " + id + " Deletado com sucesso!");
    }

}
