package com.br.checkAluno.Email;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailSerivce {


    private JavaMailSender javaMailSender;
    private EmailRepository emailRepository;
    private EmailMapper emailMapper;

    public EmailSerivce(JavaMailSender javaMailSender, EmailRepository emailRepository, EmailMapper emailMapper) {
        this.javaMailSender = javaMailSender;
        this.emailRepository = emailRepository;
        this.emailMapper = emailMapper;
    }

    public List<EmailDTO> listar() {
        List<EmailModel> emails =  emailRepository.findAll();
        return emails.stream().map(emailMapper::map).collect(Collectors.toList());
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

    public EmailDTO listarPorId(Long id) {
        Optional<EmailModel> emailPorId = emailRepository.findById(id);
        if (emailPorId.isPresent()) {
        EmailModel emailEncontrado = emailPorId.get();
            EmailDTO emailDTO = emailMapper.map(emailEncontrado);
            return emailDTO;
        }
        return null;
    }



    public void deletar(Long id) {
        emailRepository.deleteById(id);
    }
}
