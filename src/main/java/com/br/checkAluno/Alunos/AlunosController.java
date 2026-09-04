package com.br.checkAluno.Alunos;


import com.br.checkAluno.Resonsaveis.ResponsaveisModel;
import com.br.checkAluno.Resonsaveis.ResponsaveisRepository;
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
    public List<AlunosDTO> listarAlunos() {
        return alunosService.listar();
    }

    @GetMapping("/listar/{id}")
    public AlunosDTO listarPorId(@PathVariable Long id) {
        return alunosService.listarPorId(id);
    }

    @PostMapping("/criar")
    public AlunosDTO criarAluno(@RequestBody AlunosDTO alunosDTO) {
        return alunosService.criar(alunosDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarAluno(@PathVariable Long id) {
        alunosService.deletar(id);
    }

    @PutMapping("/atualizar")
    public AlunosDTO atualizarAluno(@PathVariable Long id, @RequestBody AlunosDTO alunosDTO){
        return alunosService.atualizar(id,  alunosDTO);
    }
}
