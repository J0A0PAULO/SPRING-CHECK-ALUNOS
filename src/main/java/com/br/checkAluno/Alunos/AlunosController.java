package com.br.checkAluno.Alunos;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alunos")
public class AlunosController {

    AlunosService alunosService;


    @GetMapping("/listar")
    public List<AlunosModel> listarAlunos() {
        return alunosService.listarAlunos();
    }

    @GetMapping("/listar/{id}")
    public AlunosModel listarPorId(@PathVariable Long id) {
        return alunosService.listarPorId(id);
    }

    @PostMapping("/criar")
    public AlunosModel criarAluno(@RequestBody AlunosModel alunosModel) {
        return alunosService.criarAluno(alunosModel);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarAluno(@PathVariable Long id) {
        alunosService.deletarAluno(id);
    }

    @PutMapping("/atualizar")
    public AlunosModel atualizarAluno(@PathVariable Long id, @RequestBody AlunosModel alunosModel){
        return alunosService.atualizarAluno(id,  alunosModel);
    }



}
