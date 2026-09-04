package com.br.checkAluno.Presencas;

import org.springframework.stereotype.Component;

@Component
public class PresencaMapper {

    public PresencaModel map(PresencaDTO presencaDTO) {

        PresencaModel presencaModel = new PresencaModel();
        presencaModel.setAlunosModel(presencaDTO.getAlunosModel());
        presencaModel.setId(presencaDTO.getId());
        presencaModel.setStatus(presencaDTO.getStatus());
        presencaModel.setDataHora(presencaDTO.getDataHora());

        return presencaModel;

    }

    public PresencaDTO map(PresencaModel presencaModel) {

        PresencaDTO presencaDTO = new PresencaDTO();

        presencaDTO.setAlunosModel(presencaModel.getAlunosModel());
        presencaDTO.setStatus(presencaModel.getStatus());
        presencaDTO.setId(presencaModel.getId());
        presencaDTO.setDataHora(presencaModel.getDataHora());

        return presencaDTO;

    }
}
