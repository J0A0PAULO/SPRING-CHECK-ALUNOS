package com.br.checkAluno.Alunos;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunosService {

    AlunosRepository alunosRepository;

    public AlunosService(AlunosRepository alunosRepository) {
        this.alunosRepository = alunosRepository;
    }

    public List<AlunosModel> listar() {
        return alunosRepository.findAll();
    }

    public AlunosModel listarPorId(Long id){
        Optional<AlunosModel> alunos = alunosRepository.findById(id);
        AlunosModel alunoEncontrado = alunos.orElse(null);
        return alunoEncontrado;

    }

    public AlunosModel criar(AlunosModel alunosModel) {
       return alunosRepository.save(alunosModel);
    }

    public AlunosModel atualizar(Long id, AlunosModel alunosModel) {
        Optional<AlunosModel> alunoEncontrado = alunosRepository.findById(id);
        if (alunoEncontrado.isPresent()) {
            alunosModel.setId(id);
             alunosRepository.save(alunosModel);
             return alunosModel;
        }
        return null;
    }

    public void deletar(Long id) {
        alunosRepository.deleteById(id);
    }

}
