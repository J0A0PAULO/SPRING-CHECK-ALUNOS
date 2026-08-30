package com.br.checkAluno.Resonsaveis;

import com.br.checkAluno.Alunos.AlunosModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "responsaveis")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsaveisModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String telefone;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    LocalDateTime localDateTime;

    @OneToMany
    @JoinColumn(name = "responsavel_id")
    private List<AlunosModel> alunosModels;

}
