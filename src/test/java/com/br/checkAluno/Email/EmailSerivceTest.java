package com.br.checkAluno.Email;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmailSerivceTest {

    @InjectMocks
    private EmailSerivce emailSerivce;

    @Mock
    private EmailMapper emailMapper;

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void listarLogsDeEmails() {


        EmailModel emailModel = new EmailModel(
                1L,
                "Carlos",
                "assunto",
                "Texto",
                LocalDateTime.now(),
                "Enviado"
        );

        EmailDTO emailDTO = new EmailDTO(
                1L,
                "adm@gmail.com",
                "assunto",
                "Texto",
                LocalDateTime.now(),
                "Enviado"
        );

        Mockito.when(emailRepository.findAll()).thenReturn(Collections.singletonList(emailModel));
        Mockito.when(emailMapper.map(Mockito.any(EmailModel.class))).thenReturn(emailDTO);

        List<EmailDTO> emailsLog = emailSerivce.listar();

        assertNotNull(emailsLog);
        assertEquals("adm@gmail.com", emailsLog.get(0).getEmailParaDestinatario());
        assertEquals(1L, emailsLog.get(0).getId());

        Mockito.verify(emailRepository, Mockito.times(1)).findAll();
        Mockito.verify(emailMapper, Mockito.times(1)).map(emailModel);


    }

        @Test
        public void enviarEmail() {

            String destinatario = "adm@gmail.com";
            String assunto = "Teste assunto";
            String texto = "testando";


            Mockito.doThrow(new RuntimeException("Falha ao conectar ao SMTP"))
                    .when(javaMailSender).send(Mockito.any(SimpleMailMessage.class));

            assertThrows(RuntimeException.class, () -> {
                emailSerivce.enviarEmail(destinatario,assunto, texto);
            });

            emailSerivce.enviarEmail(destinatario,assunto,texto);

            Mockito.verify(javaMailSender, Mockito.times(1)).send(Mockito.any(SimpleMailMessage.class));

        }

        @Test
        public  void ListarLogPorID() {

            Long id = 1L;

            EmailModel emailModel = new EmailModel(
                    1L,
                    "Carlos",
                    "assunto",
                    "Texto",
                    LocalDateTime.now(),
                    "Enviado"
            );

            EmailDTO emailDTO = new EmailDTO(
                    1L,
                    "adm@gmail.com",
                    "assunto",
                    "Texto",
                    LocalDateTime.now(),
                    "Enviado"
            );

            Mockito.when(emailRepository.findById(id)).thenReturn(Optional.of(emailModel));
            Mockito.when(emailMapper.map(Mockito.any(EmailModel.class))).thenReturn(emailDTO);

            EmailDTO email = emailSerivce.listarPorId(1L);

            assertNotNull(email);
            assertEquals(id, email.getId());
            assertEquals("Texto", email.getTexto());

            Mockito.verify(emailRepository, Mockito.times(1)).findById(id);
            Mockito.verify(emailMapper, Mockito.times(1)).map(emailModel);




        }

    @Test
    public void deletarLog() {

        Long id = 1L;

        emailSerivce.deletar(id);

        Mockito.verify(emailRepository, Mockito.times(1)).deleteById(id);

    }




}