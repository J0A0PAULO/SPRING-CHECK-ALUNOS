package com.br.checkAluno.Resonsaveis;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("responsaveis")
public class ResponsaveisController {

    ResponsaveisService responsaveisService;

    public ResponsaveisController(ResponsaveisService responsaveisService ) {
        this.responsaveisService = responsaveisService;
    }

    @GetMapping("/listar")
    public List<ResponsavelDTO> listar() {
        return responsaveisService.listar();
    }

    @GetMapping("/listar/{id}")
    public ResponsavelDTO listarPorId(@PathVariable Long id) {
        return responsaveisService.listarPorId(id);
    }

    @PostMapping("/criar")
    public ResponsavelDTO criar(@RequestBody ResponsavelDTO responsaveisModel) {
        return responsaveisService.criar(responsaveisModel);
    }

    @PutMapping("/atualizar/{id}")
    public ResponsavelDTO atualizar(@PathVariable Long id,@RequestBody ResponsavelDTO responsaveisModel) {
        return responsaveisService.atualizar(1L, responsaveisModel);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
         responsaveisService.deletar(id);
    }

}
