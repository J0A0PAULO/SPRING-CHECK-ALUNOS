package com.br.checkAluno.Email;

import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

    public EmailDTO map(EmailModel emailModel) {

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setStatusEmail(emailModel.getStatusEmail());
        emailDTO.setId(emailModel.getId());
        emailDTO.setAssunto(emailModel.getAssunto());
        emailDTO.setEmailParaDestinatario(emailModel.getEmailParaDestinatario());
        emailDTO.setHorarioEnvio(emailModel.getHorarioEnvio());
        emailDTO.setTexto(emailModel.getTexto());

        return emailDTO;

    }

    public EmailModel map(EmailDTO emailDTO) {

        EmailModel emailModel = new EmailModel();
        emailModel.setStatusEmail(emailDTO.getStatusEmail());
        emailModel.setId(emailDTO.getId());
        emailModel.setAssunto(emailDTO.getAssunto());
        emailModel.setEmailParaDestinatario(emailDTO.getEmailParaDestinatario());
        emailModel.setHorarioEnvio(emailDTO.getHorarioEnvio());
        emailModel.setTexto(emailDTO.getTexto());

        return emailModel;

    }

}
