package com.br.checkAluno.Email;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    EmailSerivce emailSerivce;

    public EmailController(EmailSerivce emailSerivce) {
        this.emailSerivce = emailSerivce;
    }

    @GetMapping("/listar")
    public List<EmailModel> listar() {
        return emailSerivce.listar();
    }

    @GetMapping("/listar/{id}")
    public EmailModel listarPorID(@PathVariable Long id) {
        return emailSerivce.listarPorId(id);
    }

    @PostMapping("/criar")
    public EmailModel criarEmail(@RequestBody EmailModel emailModel) {
        return emailSerivce.criar(emailModel);
    }

}
