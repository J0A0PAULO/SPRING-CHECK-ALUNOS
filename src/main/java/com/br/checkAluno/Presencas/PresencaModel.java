package com.br.checkAluno.Presencas;

import com.br.checkAluno.Alunos.AlunosModel;
import com.br.checkAluno.Alunos.AlunosService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "presencas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresencaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "data_hora", nullable = false, updatable = false)
    private LocalDateTime dataHora;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JsonIgnoreProperties("responsavel")
    @JoinColumn(name = "alunos_id")
    private AlunosModel alunosModel;

}
