package com.br.checkAluno.Resonsaveis;

import com.br.checkAluno.Alunos.AlunosRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResponsaveisService {

    private ResponsaveisRepository responsaveisRepository;
    private ResponsaveisMapper  responsaveisMapper;

    public ResponsaveisService(ResponsaveisRepository responsaveisRepository, ResponsaveisMapper responsaveisMapper) {
        this.responsaveisRepository = responsaveisRepository;
        this.responsaveisMapper = responsaveisMapper;
    }

    public List<ResponsavelDTO> listar() {
        List<ResponsaveisModel> responsaveis = responsaveisRepository.findAll();
        return responsaveis.stream().map(responsaveisMapper::map).collect(Collectors.toList());
    }

    public ResponsavelDTO criar(ResponsavelDTO responsavelDTO) {
        ResponsaveisModel responsaveisModel = responsaveisMapper.map(responsavelDTO);
        ResponsaveisModel salvo = responsaveisRepository.save(responsaveisModel);
        return responsaveisMapper.map(salvo);
    }

    public ResponsavelDTO listarPorId(Long id) {
        Optional<ResponsaveisModel> responsavel = responsaveisRepository.findById(id);
        ResponsaveisModel resposavel = responsavel.orElse(null);
        ResponsavelDTO responsavelDTO = responsaveisMapper.map(resposavel);
        return responsavelDTO;
    }

    public ResponsavelDTO atualizar(Long id, ResponsavelDTO responsavelDTO) {
        Optional<ResponsaveisModel> responsavelPorID = responsaveisRepository.findById(id);
        if (responsavelPorID.isPresent()) {
          ResponsaveisModel responsavelEnviadoPeloUsuario = responsaveisMapper.map(responsavelDTO);
            responsavelEnviadoPeloUsuario.setId(id);
          ResponsaveisModel responsavelDTOSalvo= responsaveisRepository.save(responsavelEnviadoPeloUsuario);
          return responsaveisMapper.map(responsavelDTOSalvo);
        }
        return null;
    }

    public void deletar(Long id) {
        responsaveisRepository.deleteById(id);
    }

}
