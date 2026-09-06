package com.br.checkAluno.Email;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailSerivce {


    private JavaMailSender javaMailSender;
    private EmailRepository emailRepository;

    public EmailSerivce(JavaMailSender javaMailSender, EmailRepository emailRepository) {
        this.javaMailSender = javaMailSender;
        this.emailRepository = emailRepository;
    }

    public List<EmailModel> listar() {
        return emailRepository.findAll();
    }

    public void enviarEmail(String destinatario, String assunto, String texto ){

        EmailModel emailLog = new EmailModel();
        emailLog.setEmailParaDestinatario(destinatario);
        emailLog.setAssunto(assunto);
        emailLog.setTexto(texto);

        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(destinatario);
            message.setSubject(assunto);
            message.setText(texto);

            javaMailSender.send(message);
            emailLog.setStatusEmail("ENVIADO");

        }catch (Exception e){
            emailLog.setStatusEmail("ERRO");
            System.err.println("Erro ao enviar e-mail:" + e.getMessage());
        } finally {
            emailRepository.save(emailLog);

        }

    }

    public EmailModel listarPorId(Long id) {
        Optional<EmailModel> emailPorId = emailRepository.findById(id);
        EmailModel emailEncontrado = emailPorId.orElse(null);
        return emailEncontrado;
    }

    public EmailModel atualizar (Long id, EmailModel emailModel) {
        Optional<EmailModel> emailEncontrado = emailRepository.findById(id);
        if (emailEncontrado.isPresent()) {
            emailModel.setId(id);
           return emailRepository.save(emailModel);
        }
        return null;
    }

    public EmailModel criar(EmailModel emailModel) {
        return emailRepository.save(emailModel);
    }

    public void deletar(Long id) {
        emailRepository.deleteById(id);
    }
}
