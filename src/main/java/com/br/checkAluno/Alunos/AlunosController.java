package com.br.checkAluno.Alunos;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Alunos", description = "EndPoints para gerenciamento de alunos")
@RestController
@RequestMapping("alunos")
public class AlunosController {

    private AlunosService alunosService;

    public AlunosController(AlunosService alunosService) {
        this.alunosService = alunosService;
    }

    @Operation(summary = "Listar todos alunos", description = "Essa rota lista todos os alunos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<AlunosDTO>> listarAlunos() {
        List<AlunosDTO> alunos = alunosService.listar();
        return ResponseEntity.ok(alunos);
    }

    @Operation(summary = "Listar aluno por id", description = "Essa rota é responsavel por lista aluno por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<AlunosDTO> listarPorId(@PathVariable Long id) {
        AlunosDTO alunos = alunosService.listarPorId(id);
        if (alunos != null) {
            return ResponseEntity.ok(alunos);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Criar aluno", description = "Essa rota fica responsavel por criar aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de alunso Invalidos")
    })
    @PostMapping("/criar")
    public ResponseEntity<AlunosDTO> criarAluno(@RequestBody AlunosDTO alunosDTO) {
        AlunosDTO aluno = alunosService.criar(alunosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @Operation(summary = "Deletar aluno", description = "Essa rota fica responsavle por deletar aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Aluno deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        alunosService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza aluno", description = "Essa rota fica responsavel por atualizar aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de aluno invalidos"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AlunosDTO> atualizarAluno(@PathVariable Long id, @RequestBody AlunosDTO alunosDTO){
        AlunosDTO aluno = alunosService.atualizar(id,  alunosDTO);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
