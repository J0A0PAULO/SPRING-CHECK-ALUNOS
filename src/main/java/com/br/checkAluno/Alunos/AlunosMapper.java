package com.br.checkAluno.Alunos;

import org.springframework.stereotype.Component;

@Component
public class AlunosMapper {

    public AlunosModel map(AlunosDTO alunosDTO) {

        AlunosModel alunosModel = new AlunosModel();

        alunosModel.setResponsavel(alunosDTO.getResponsavel());
        alunosModel.setId(alunosDTO.getId());
        alunosModel.setCpf(alunosDTO.getCpf());
        alunosModel.setEmail(alunosDTO.getEmail());
        alunosModel.setNome(alunosDTO.getEmail());
        alunosModel.setMatricula(alunosDTO.getMatricula());
        alunosModel.setDataNascimento(alunosDTO.getDataNascimento());

        return alunosModel;

    }

    public AlunosDTO map(AlunosModel alunosModel) {

        AlunosDTO alunosDTO = new AlunosDTO();

        alunosDTO.setCpf(alunosModel.getCpf());
        alunosDTO.setMatricula(alunosModel.getMatricula());
        alunosDTO.setResponsavel(alunosModel.getResponsavel());
        alunosDTO.setDataNascimento(alunosModel.getDataNascimento());
        alunosDTO.setEmail(alunosModel.getEmail());
        alunosDTO.setNome(alunosModel.getNome());
        alunosDTO.setTelefone(alunosModel.getTelefone());

        return alunosDTO;

    }



}
