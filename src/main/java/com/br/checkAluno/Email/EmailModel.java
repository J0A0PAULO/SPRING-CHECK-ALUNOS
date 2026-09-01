package com.br.checkAluno.Email;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "emails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email_destinatario")
    private String emailParaDestinatario;

    @Column(name = "assunto")
    private String assunto;

    @Column(name = "texto")
    private String texto;

    @CreationTimestamp
    @Column(name = "horario_envio", nullable = false, updatable = false)
    private LocalDateTime horarioEnvio;

    @Column(name = "status_email")
    private String statusEmail;

}
