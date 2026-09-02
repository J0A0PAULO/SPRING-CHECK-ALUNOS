package com.br.checkAluno.Presencas;

import com.br.checkAluno.Alunos.AlunosModel;
import com.br.checkAluno.Alunos.AlunosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresencaService {

    PresencaRepository presencaRepository;
    AlunosRepository alunosRepository;

    public PresencaService(PresencaRepository presencaRepository, AlunosRepository alunosRepository) {
        this.presencaRepository = presencaRepository;
        this.alunosRepository = alunosRepository;
    }

    public List<PresencaModel> listarPresenca() {
        return presencaRepository.findAll();
    }

    public PresencaModel listarPorId(Long id) {
        Optional<PresencaModel> encontradoPorID = presencaRepository.findById(id);
        PresencaModel encontrado = encontradoPorID.orElse(null);
        return encontrado;
    }


    public void deletar(Long id) {
           presencaRepository.deleteById(id);
    }

    public PresencaModel criar(PresencaModel presencaModel) {
        Optional<AlunosModel> alunoReferencia = alunosRepository.findById(presencaModel.getAlunosModel().getId());
        AlunosModel alunoEncontrado = alunoReferencia.orElse(null);
        presencaModel.setAlunosModel(alunoEncontrado);
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
