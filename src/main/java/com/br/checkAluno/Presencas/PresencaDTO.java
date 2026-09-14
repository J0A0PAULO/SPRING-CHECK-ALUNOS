package com.br.checkAluno.Presencas;
import com.br.checkAluno.Alunos.AlunosModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresencaDTO {

    private Long id;
    private LocalDateTime dataHora;

    @NotNull(message = "O estatu da presença é obrigatorio (True para presente e false para ausente")
    private Boolean status;

    @NotNull(message = "O aluno é necessario para registrar presença")
    @Valid
    @JsonIgnoreProperties("responsavel")
    private AlunosModel aluno;

}
