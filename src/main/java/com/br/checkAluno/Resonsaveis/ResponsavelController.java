package com.br.checkAluno.Resonsaveis;

import com.br.checkAluno.Alunos.AlunosModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("responsaveis")
public class ResponsavelController {

    ResponsaveisService responsaveisService;
    AlunosModel alunosModel;

    public ResponsavelController(ResponsaveisService responsaveisService) {
        this.responsaveisService = responsaveisService;
    }

    @GetMapping("/listar")
    public List<ResponsaveisModel> listarResponsaveis() {
        return responsaveisService.listarResponsaveis();
    }

    @GetMapping("/listar/{id}")
    public ResponsaveisModel listarPorId(@PathVariable Long id) {
        return responsaveisService.listarPorId(id);
    }

    @PostMapping("/criar")
    public ResponsaveisModel criarResponsavel(@RequestBody ResponsaveisModel responsaveisModel) {
        return responsaveisService.criarResponsavel(responsaveisModel);
    }

    @PutMapping("/atualizar/{id}")
    public ResponsaveisModel atualizarResponsavel(@PathVariable Long id, ResponsaveisModel responsaveisModel) {
        return responsaveisService.alterarPorID(1L, responsaveisModel);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
         responsaveisService.deletar(id);
    }

}
