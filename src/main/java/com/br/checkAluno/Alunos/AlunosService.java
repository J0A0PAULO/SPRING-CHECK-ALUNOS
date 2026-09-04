package com.br.checkAluno.Alunos;

import com.br.checkAluno.Resonsaveis.ResponsaveisMapper;
import com.br.checkAluno.Resonsaveis.ResponsaveisModel;
import com.br.checkAluno.Resonsaveis.ResponsaveisRepository;
import com.br.checkAluno.Resonsaveis.ResponsavelDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunosService {

    AlunosRepository alunosRepository;
    ResponsaveisRepository responsaveisRepository;
    ResponsaveisMapper responsaveisMapper;
    AlunosMapper alunosMapper;

    public AlunosService(AlunosRepository alunosRepository, ResponsaveisRepository responsaveisRepository,AlunosMapper alunosMapper, ResponsaveisMapper responsaveisMapper) {
        this.alunosRepository = alunosRepository;
        this.responsaveisRepository = responsaveisRepository;
        this.responsaveisMapper = responsaveisMapper;
        this.alunosMapper = alunosMapper;
    }

    public List<AlunosDTO> listar() {
        List<AlunosModel> alunos = alunosRepository.findAll();
        return alunos.stream().map(alunosMapper::map).collect(Collectors.toList());
    }


    public AlunosDTO listarPorId(Long id){
        Optional<AlunosModel> alunos = alunosRepository.findById(id);
        if (alunos.isPresent()) {
        AlunosModel alunoEncontrado = alunos.get();
        return alunosMapper.map(alunoEncontrado);
        }
        return null;
    }

    public AlunosDTO criar(AlunosDTO alunosDTO) {
        Optional<ResponsaveisModel> responsavelReferencia = responsaveisRepository.findById(alunosDTO.getResponsavel().getId());
        if (responsavelReferencia.isPresent()) {
            ResponsaveisModel responsavelEncontrado = responsavelReferencia.get();
            AlunosModel alunosModel = alunosMapper.map(alunosDTO);
            alunosModel.setResponsavel(responsavelEncontrado);
            alunosRepository.save(alunosModel);
            return alunosMapper.map(alunosModel);
        }
        return null;
    }

    public AlunosDTO atualizar(Long id, AlunosDTO alunosDTO) {
        Optional<AlunosModel> alunoEncontrado = alunosRepository.findById(id);
        Optional<ResponsaveisModel> responsavelReferencia = responsaveisRepository.findById(alunosDTO.getResponsavel().getId());
        if (alunoEncontrado.isPresent() && responsavelReferencia.isPresent()) {
            ResponsaveisModel responsavelEncontrado = responsavelReferencia.get();
            AlunosModel alunosModel = alunosMapper.map(alunosDTO);
            alunosModel.setResponsavel(responsavelEncontrado);
            alunosModel.setId(id);
            alunosRepository.save(alunosModel);
            AlunosDTO aluno = alunosMapper.map(alunosModel);
             return aluno;
        }
        return null;
    }

    public void deletar(Long id) {
        alunosRepository.deleteById(id);
    }

}
