package com.br.checkAluno.Presencas;


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
    public List<PresencaDTO> listar(){
        return presencaService.listarPresenca();
    }

    @GetMapping("/list/{id}")
    public PresencaDTO listarPorID(@PathVariable Long id) {
        return presencaService.listarPorId(id);
    }

    @PostMapping("/criar")
    public PresencaDTO criar(@RequestBody PresencaDTO presencaModel) {
        return presencaService.criar(presencaModel);
    }

    @PutMapping("/atualizar/{id}")
    public PresencaDTO atualizar(@PathVariable Long id, @RequestBody PresencaDTO presencaModel) {
        return presencaService.atualizar(id, presencaModel);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
        presencaService.deletar(id);
    }

}
