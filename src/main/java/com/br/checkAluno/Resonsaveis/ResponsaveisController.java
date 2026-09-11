package com.br.checkAluno.Resonsaveis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Responsáveis", description = "Endpoints para gerenciamento de responsáveis")
@RestController
@RequestMapping("responsaveis")
public class ResponsaveisController {

    private ResponsaveisService responsaveisService;

    public ResponsaveisController(ResponsaveisService responsaveisService ) {
        this.responsaveisService = responsaveisService;
    }

    @Operation(summary = "Listar todos Responsaveis", description = "Essa rota retorna uma lista com todos os responsaveis dos alunos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listar todos responsaveis"),
    })
    @GetMapping("/listar")
    public ResponseEntity<List<ResponsavelDTO>> listar() {
       List<ResponsavelDTO> responsavelDTOS = responsaveisService.listar();
        return ResponseEntity.ok(responsavelDTOS);
    }

    @Operation(summary = "Listar responsavel por id", description = "Essa rota retorna um Responsavel por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Responsavel encontrado"),
            @ApiResponse(responseCode = "404", description = "Responsavel não encontrado")
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
      ResponsavelDTO responsavel = responsaveisService.listarPorId(id);
        if (responsavel != null) {
            return ResponseEntity.ok(responsavel);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsavel com " +  id + " Responsavle não encontrado");
    }

    @Operation(summary = "Cria um responsavel", description = "Essa rota cria um responsavel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Responsavel criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados do responsavel invalidos")
    })
    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody ResponsavelDTO responsavelDTO) {
       ResponsavelDTO responsavel = responsaveisService.criar(responsavelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsavel);
    }

    @Operation(summary = "Atualizar responsavel", description = "Essa rota atualiza responsavel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Responsavel atualizado com  sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados do responsavel invalidos"),
            @ApiResponse(responseCode = "404", description = "Responsavel não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,@RequestBody ResponsavelDTO responsavelDTO) {
        ResponsavelDTO responsavel =  responsaveisService.atualizar(id  , responsavelDTO);
        if (responsavel != null) {
            return ResponseEntity.ok(responsavel);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responsavel);
    }

    @Operation(summary = "Deletar responsavel", description = "Essa rota deleta responsavel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Responsavel excluido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Responsavel não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
         responsaveisService.deletar(id);
         return ResponseEntity.noContent().build();
    }
}
