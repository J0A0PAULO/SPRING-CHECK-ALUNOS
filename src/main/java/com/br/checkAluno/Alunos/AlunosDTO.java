package com.br.checkAluno.Alunos;

import com.br.checkAluno.Responsaveis.ResponsaveisModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunosDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Long matricula;
    private LocalDate dataNascimento;
    @JsonIgnoreProperties("aluno")
    @Schema(hidden = true)
    private ResponsaveisModel responsavel;
}
