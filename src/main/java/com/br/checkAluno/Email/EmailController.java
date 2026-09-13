package com.br.checkAluno.Email;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Emails", description = "EndPoints para gerenciamento de emails")
@RestController
@RequestMapping("/email")
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @Operation(summary = "Listar todos logs", description = "Essa rota fica responsavel por listar todos logs de emails")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<EmailDTO>> listar() {
         List<EmailDTO> emails = emailService.listar();
         return ResponseEntity.ok(emails);
    }

    @Operation(summary = "Listar email por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email encontrado"),
            @ApiResponse(responseCode = "404", description = "Email não encontrado")
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<EmailDTO> listarPorID(@PathVariable Long id) {
         EmailDTO email = emailService.listarPorId(id);
         if (email != null) {
             return ResponseEntity.ok(email);
         }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Deletar email por id",  description = "Email deletado com  sucesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deltado sucesso"),
            @ApiResponse(responseCode = "404", description = "Email não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        emailService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
