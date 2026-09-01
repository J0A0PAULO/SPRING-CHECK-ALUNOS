package com.br.checkAluno.Presencas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresencaService {

    PresencaRepository presencaRepository;

    public PresencaService(PresencaRepository presencaRepository) {
        this.presencaRepository = presencaRepository;
    }

    public List<PresencaModel> listarPresenca() {
        return presencaRepository.findAll();
    }

    public PresencaModel listarPorId(Long id) {
        Optional<PresencaModel> encontradoPorID = presencaRepository.findById(id);
        PresencaModel encontrado = encontradoPorID.orElse(null);
        return encontrado;
    }

    public PresencaModel Atualizar(Long id, PresencaModel presencaModel) {
        Optional<PresencaModel> encontradoPorId = presencaRepository.findById(id);
        if (encontradoPorId.isPresent()) {
            presencaModel.setId(id);
             presencaRepository.save(presencaModel);
             return presencaModel;
        }
            return null;
    }

    public void deletar(Long id) {
           presencaRepository.deleteById(id);
    }

    public PresencaModel criar(PresencaModel presencaModel) {
        return presencaRepository.save(presencaModel);
    }

    public PresencaModel atualizar(Long id, PresencaModel presencaModel) {
        Optional<PresencaModel> PresensaPorID = presencaRepository.findById(id);
        if (PresensaPorID.isPresent()) {
            presencaModel.setId(id);
           return presencaRepository.save(presencaModel);
        }
        return null;
    }
}
