package com.br.checkAluno.Presencas;

import com.br.checkAluno.Alunos.AlunosModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresencaDTO {

    private Long id;
    private LocalDateTime dataHora;
    private String status;
    @JsonIgnoreProperties("responsavel")
    private AlunosModel alunosModel;

}
