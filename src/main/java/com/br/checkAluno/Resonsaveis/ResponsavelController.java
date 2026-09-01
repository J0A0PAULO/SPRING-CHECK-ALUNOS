package com.br.checkAluno.Resonsaveis;

import com.br.checkAluno.Alunos.AlunosModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("responsaveis")
public class ResponsavelController {

    ResponsaveisService responsaveisService;

    public ResponsavelController(ResponsaveisService responsaveisService ) {
        this.responsaveisService = responsaveisService;
    }

    @GetMapping("/listar")
    public List<ResponsaveisModel> listar() {
        return responsaveisService.listar();
    }

    @GetMapping("/listar/{id}")
    public ResponsaveisModel listarPorId(@PathVariable Long id) {
        return responsaveisService.listarPorId(id);
    }

    @PostMapping("/criar")
    public ResponsaveisModel criar(@RequestBody ResponsaveisModel responsaveisModel) {
        return responsaveisService.criar(responsaveisModel);
    }

    @PutMapping("/atualizar/{id}")
    public ResponsaveisModel atualizar(@PathVariable Long id, ResponsaveisModel responsaveisModel) {
        return responsaveisService.atualizar(1L, responsaveisModel);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
         responsaveisService.deletar(id);
    }

}
