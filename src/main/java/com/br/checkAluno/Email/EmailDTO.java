package com.br.checkAluno.Email;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {

    private Long id;
    private String emailParaDestinatario;
    private String assunto;
    private String texto;
    private LocalDateTime horarioEnvio;
    private String statusEmail;

}
