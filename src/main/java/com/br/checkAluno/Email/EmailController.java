package com.br.checkAluno.Email;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    private EmailSerivce emailSerivce;

    public EmailController(EmailSerivce emailSerivce) {
        this.emailSerivce = emailSerivce;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EmailDTO>> listar() {
         List<EmailDTO> emails = emailSerivce.listar();
         return ResponseEntity.ok(emails);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorID(@PathVariable Long id) {
         EmailDTO email = emailSerivce.listarPorId(id);
         if (email != null) {
             return ResponseEntity.ok(email);
         }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email com " + id + " não encontrado");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        emailSerivce.deletar(id);
        return ResponseEntity.ok("Email com " + id + " deletado com Sucesso");

    }

}
