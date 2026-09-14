package com.br.checkAluno.Alunos;

import com.br.checkAluno.Responsaveis.ResponsaveisModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunosDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatorio")
    private String nome;

    @NotBlank(message = "CPF é obrigatorio")
    @CPF(message = "CPF invalido")
    private String cpf;

    @NotBlank(message = "Email é obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "Telefone é obrigatorio")
    private String telefone;

    @NotNull(message = "Matricula é obrigatoria")
    private Long matricula;

    private LocalDate dataNascimento;

    @JsonIgnoreProperties("aluno")
    @Schema(hidden = true)
    @Valid
    private ResponsaveisModel responsavel;
}
