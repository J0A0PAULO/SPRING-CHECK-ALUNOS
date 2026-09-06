package com.br.checkAluno.Presencas;

import com.br.checkAluno.Alunos.AlunosDTO;
import com.br.checkAluno.Alunos.AlunosMapper;
import com.br.checkAluno.Alunos.AlunosModel;
import com.br.checkAluno.Alunos.AlunosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PresencaService {

    PresencaRepository presencaRepository;
    AlunosRepository alunosRepository;
    PresencaMapper presencaMapper;
    AlunosMapper alunosMapper;

    public PresencaService(PresencaRepository presencaRepository, AlunosRepository alunosRepository, PresencaMapper presencaMapper, AlunosMapper alunosMapper) {
        this.presencaRepository = presencaRepository;
        this.alunosRepository = alunosRepository;
        this.presencaMapper = presencaMapper;
        this.alunosMapper = alunosMapper;
    }

    public List<PresencaDTO> listarPresenca() {
        List<PresencaModel> presencas = presencaRepository.findAll();
        return presencas.stream().map(presencaMapper::map).collect(Collectors.toList());
    }


    public PresencaDTO listarPorId(Long id) {
        Optional<PresencaModel> encontradoPorID = presencaRepository.findById(id);
        if (encontradoPorID.isPresent()) {
        PresencaModel presencaEncontrada = encontradoPorID.get();
        PresencaDTO presencaDTO = presencaMapper.map(presencaEncontrada);
        return presencaDTO;
        }
        return null;
    }


    public PresencaDTO criar(PresencaDTO presencaDTO) {
        Optional<AlunosModel> alunoReferencia = alunosRepository.findById(presencaDTO.getAlunosModel().getId());
        if (alunoReferencia.isPresent()){
            AlunosModel aluno = alunoReferencia.get();
            PresencaModel presencaModel = presencaMapper.map(presencaDTO);
            presencaModel.setAlunosModel(aluno);
            presencaRepository.save(presencaModel);
            PresencaDTO presencaSalva = presencaMapper.map(presencaModel);
            return presencaSalva;
        }
        return null;
    }

    public PresencaDTO atualizar(Long id, PresencaDTO presencaDTO) {

        Optional<PresencaModel> PresensaPorID = presencaRepository.findById(id);
        if (PresensaPorID.isPresent()) {
        PresencaModel presencaEncontrada = PresensaPorID.get();

        PresencaModel presecaConvertidoParaModel = presencaMapper.map(presencaDTO);
        presecaConvertidoParaModel.setId(presencaEncontrada.getId());

            if (presecaConvertidoParaModel.getStatus() == null) {
                presecaConvertidoParaModel.setStatus(presencaEncontrada.getStatus());
            }

            if (presecaConvertidoParaModel.getDataHora() == null) {
                presecaConvertidoParaModel.setDataHora(presencaEncontrada.getDataHora());
            }

            if (presecaConvertidoParaModel.getAlunosModel() == null) {
                presecaConvertidoParaModel.setAlunosModel(presencaEncontrada.getAlunosModel());
            }

        presencaRepository.save(presecaConvertidoParaModel);
        PresencaDTO presencaConvertidaParaDTO = presencaMapper.map(presecaConvertidoParaModel);


            return presencaConvertidaParaDTO;
        }
        return null;
    }

    public void deletar(Long id) {
        presencaRepository.deleteById(id);
    }
}
