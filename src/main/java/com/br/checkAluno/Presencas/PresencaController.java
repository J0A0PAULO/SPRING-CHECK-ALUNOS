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
    public List<PresencaModel> listar(){
        return presencaService.listarPresenca();
    }

    @GetMapping("/list/{id}")
    public PresencaModel listarPorID(@PathVariable Long id) {
        return presencaService.listarPorId(id);
    }

    @PostMapping("/criar")
    public PresencaModel criar(@RequestBody PresencaModel presencaModel) {
        return presencaService.criar(presencaModel);
    }

    @PutMapping("/atualizar/{id}")
    public PresencaModel atualizar(@PathVariable Long id, @RequestBody PresencaModel presencaModel) {
        return presencaService.atualizar(id, presencaModel);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
        presencaService.deletar(id);
    }

}
