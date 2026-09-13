package com.br.checkAluno.Responsaveis;

import com.br.checkAluno.Alunos.AlunosModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsavelDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatorio")
    private String nome;

    @NotBlank(message = "O cpf é obrigatorio")
    @CPF(message = "CPF invalido")
    private String cpf;

    @NotBlank(message = "Email é obrigatorio")
    @Email(message = "e-mail precisa ser valido")
    private String email;


    @NotBlank(message = "Telefone é obrigatorio")
    private String telefone;

    LocalDateTime localDateTime;
    @JsonIgnoreProperties("responsavel")
    @Valid
    private List<AlunosModel> Aluno;

}
