package com.br.checkAluno.Resonsaveis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsaveisService {

    private ResponsaveisRepository responsaveisRepository;

    //a
    public ResponsaveisService(ResponsaveisRepository responsaveisRepository) {
        this.responsaveisRepository = responsaveisRepository;
    }

    public List<ResponsaveisModel> listarResponsaveis() {
        return responsaveisRepository.findAll();
    }

    public ResponsaveisModel listarPorId(Long id) {
        Optional<ResponsaveisModel> responsavel = responsaveisRepository.findById(id);
        ResponsaveisModel resposavel = responsavel.orElse(null);
        return resposavel;
    }

    public ResponsaveisModel alterarPorID(Long id, ResponsaveisModel responsavelAlterado) {
        Optional<ResponsaveisModel> responsavelPorID = responsaveisRepository.findById(id);
        if (responsavelPorID.isPresent()) {
            responsavelPorID.orElse(null);
        responsavelAlterado.setId(id);
        responsaveisRepository.save(responsavelAlterado);
        return responsavelAlterado;
        }
        return null;
    }

    public void deletar(Long id) {
        responsaveisRepository.deleteById(id);
    }


}
