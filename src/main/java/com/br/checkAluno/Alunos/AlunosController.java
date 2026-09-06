package com.br.checkAluno.Alunos;


import com.br.checkAluno.Presencas.PresencaDTO;
import com.br.checkAluno.Resonsaveis.ResponsaveisModel;
import com.br.checkAluno.Resonsaveis.ResponsaveisRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("alunos")
public class AlunosController {

    AlunosService alunosService;

    public AlunosController(AlunosService alunosService) {
        this.alunosService = alunosService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AlunosDTO>> listarAlunos() {
        List<AlunosDTO> alunos = alunosService.listar();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        AlunosDTO alunos = alunosService.listarPorId(id);
        if (alunos != null) {
            return ResponseEntity.ok(alunos);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno com " + id + " não encontrado");
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarAluno(@RequestBody AlunosDTO alunosDTO) {
        alunosService.criar(alunosDTO);
        return ResponseEntity.ok("criado com sucesso");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarAluno(@PathVariable Long id) {
        alunosService.deletar(id);
        return ResponseEntity.ok("aluno com " + id + " deletado com sucesso");
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarAluno(@PathVariable Long id, @RequestBody AlunosDTO alunosDTO){
        AlunosDTO aluno = alunosService.atualizar(id,  alunosDTO);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("aluno com " + id + " não encontrado");
    }
}
