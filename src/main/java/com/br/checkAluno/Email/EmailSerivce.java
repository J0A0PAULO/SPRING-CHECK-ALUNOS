package com.br.checkAluno.Email;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailSerivce {

    EmailRepository emailRepository;

    public EmailSerivce(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<EmailModel> listar() {
        return emailRepository.findAll();
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
