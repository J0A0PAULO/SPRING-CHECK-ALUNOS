package com.br.checkAluno.Resonsaveis;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("responsaveis")
public class ResponsaveisController {

    private ResponsaveisService responsaveisService;

    public ResponsaveisController(ResponsaveisService responsaveisService ) {
        this.responsaveisService = responsaveisService;
    }

    @Operation(summary = "Listar todos Responsaveis", description = "Essa rota retorna uma lista com todos os responsaveis dos alunos")
    @GetMapping("/listar")
    public ResponseEntity<List<ResponsavelDTO>> listar() {
       List<ResponsavelDTO> responsavelDTOS = responsaveisService.listar();
        return ResponseEntity.ok(responsavelDTOS);
    }

    @Operation(summary = "Listar responsavel por id", description = "Essa rota retorna um Responsavel por id")
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
      ResponsavelDTO responsavel = responsaveisService.listarPorId(id);
        if (responsavel != null) {
            return ResponseEntity.ok(responsavel);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsavel com " +  id + " Responsavle não encontrado");
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody ResponsavelDTO responsavelDTO) {
        responsaveisService.criar(responsavelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Responsavel Criado");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,@RequestBody ResponsavelDTO responsavelDTO) {
        ResponsavelDTO responsavel =  responsaveisService.atualizar(id  , responsavelDTO);
        if (responsavel != null) {
            return ResponseEntity.ok(responsavel);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsavel com " + id + " Não encontrado");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
         responsaveisService.deletar(id);
         return ResponseEntity.ok("Responsavel Deletado");
    }
}
