package com.br.checkAluno.Email;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Email para destinatario é obrigatorio")
    private String emailParaDestinatario;

    @NotBlank(message = "Asseunto é obrigatorio")
    private String assunto;

    @NotBlank(message = "Texto é obrigatorio")
    private String texto;

    private LocalDateTime horarioEnvio;

    private String statusEmail;

}
