package com.br.checkAluno.Resonsaveis;

import org.springframework.stereotype.Component;

@Component
public class ResponsaveisMapper {

    public ResponsaveisModel map(ResponsavelDTO responsavelDTO) {

        ResponsaveisModel responsaveisModel = new ResponsaveisModel();

        responsaveisModel.setTelefone(responsavelDTO.getTelefone());
        responsaveisModel.setId(responsavelDTO.getId());
        responsaveisModel.setNome(responsavelDTO.getNome());
        responsaveisModel.setEmail(responsavelDTO.getEmail());
        responsaveisModel.setLocalDateTime(responsavelDTO.getLocalDateTime());
        responsaveisModel.setCpf(responsavelDTO.getCpf());
        responsaveisModel.setAluno(responsavelDTO.getAluno());

        return responsaveisModel;
    }

    public ResponsavelDTO map(ResponsaveisModel responsaveisModel) {

        ResponsavelDTO responsavelDTO = new ResponsavelDTO();

        responsavelDTO.setNome(responsaveisModel.getNome());
        responsavelDTO.setCpf(responsaveisModel.getCpf());
        responsavelDTO.setEmail(responsaveisModel.getEmail());
        responsavelDTO.setTelefone(responsaveisModel.getTelefone());
        responsavelDTO.setId(responsaveisModel.getId());
        responsavelDTO.setLocalDateTime(responsaveisModel.getLocalDateTime());
        responsavelDTO.setAluno(responsaveisModel.getAluno());

        return responsavelDTO;
    }

}
