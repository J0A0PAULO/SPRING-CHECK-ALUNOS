package com.br.checkAluno.Alunos;

import com.br.checkAluno.Resonsaveis.ResponsaveisMapper;
import com.br.checkAluno.Resonsaveis.ResponsaveisModel;
import com.br.checkAluno.Resonsaveis.ResponsaveisRepository;
import com.br.checkAluno.Resonsaveis.ResponsavelDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AlunosServiceTest {


    @InjectMocks
    private AlunosService alunosService;

    @Mock
    private AlunosRepository alunosRepository;

    @Mock
    private AlunosMapper alunosMapper;

    @Mock
    private ResponsaveisRepository responsaveisRepository;

    @Mock
    private ResponsaveisMapper responsaveisMapper;

    @Test
    public void listarTodosAlunos(){

        ResponsaveisModel responsaveisModel = new ResponsaveisModel(
                1L,
                "Julia",
                "5555",
                "adm@gmail.com",
                "1198888",
                LocalDateTime.now(),
                List.of()
        );
        ResponsavelDTO responsavelDTO= new ResponsavelDTO(
                1L,
                "Julia",
                "5555",
                "adm@gmail.com",
                "1198888",
                LocalDateTime.now(),
                List.of()
        );

        AlunosModel alunosModel = new AlunosModel(
                1L,
                "Pedro",
                "5555",
                "adm@gmail.com",
                "1198888",
                1111L,
                LocalDate.now(),
                responsaveisModel
        );

        AlunosDTO alunosDTO = new AlunosDTO(
                1L,
                "Pedro",
                "5555",
                "adm@gmail.com",
                "1198888",
                1111L,
                LocalDate.now(),
                responsaveisModel
        );

        Mockito.when(alunosRepository.findAll()).thenReturn(Collections.singletonList(alunosModel));
        Mockito.when(alunosMapper.map(Mockito.any(AlunosModel.class))).thenReturn(alunosDTO);

        List<AlunosDTO> alunos = alunosService.listar();

        assertNotNull(alunos);
        assertEquals(1,alunos.get(0).getId());
        assertEquals("Pedro", alunos.get(0).getNome());

        Mockito.verify(alunosRepository, Mockito.times(1)).findAll();
        Mockito.verify(alunosMapper, Mockito.times(1)).map(alunosModel);


    }

    @Test
    public void listarTodosAlunosPorID() {

        Long id = 1L;

        ResponsaveisModel responsaveisModel = new ResponsaveisModel(
                1L,
                "Julia",
                "5555",
                "adm@gmail.com",
                "1198888",
                LocalDateTime.now(),
                List.of()
        );
        ResponsavelDTO responsavelDTO= new ResponsavelDTO(
                1L,
                "Julia",
                "5555",
                "adm@gmail.com",
                "1198888",
                LocalDateTime.now(),
                List.of()
        );

        AlunosModel alunosModel = new AlunosModel(
                1L,
                "Pedro",
                "5555",
                "adm@gmail.com",
                "1198888",
                1111L,
                LocalDate.now(),
                responsaveisModel
        );

        AlunosDTO alunosDTO = new AlunosDTO(
                1L,
                "Pedro",
                "5555",
                "adm@gmail.com",
                "1198888",
                1111L,
                LocalDate.now(),
                responsaveisModel
        );


        Mockito.when(alunosRepository.findById(id)).thenReturn(Optional.of(alunosModel));
        Mockito.when(alunosMapper.map(Mockito.any(AlunosModel.class))).thenReturn(alunosDTO);

        AlunosDTO alunoEncontrado = alunosService.listarPorId(alunosDTO.getId());

        assertNotNull(alunoEncontrado);
        assertEquals(1L, alunoEncontrado.getId());
        assertEquals("Pedro", alunoEncontrado.getNome());

        Mockito.verify(alunosRepository, Mockito.times(1)).findById(alunoEncontrado.getId());
        Mockito.verify(alunosMapper, Mockito.times(1)).map(alunosModel);



    }

    @Test
    public void atualizarAlunoPorID( ){

        Long id = 1L;

        ResponsaveisModel responsaveisModel = new ResponsaveisModel(
                1L,
                "Julia",
                "5555",
                "adm@gmail.com",
                "1198888",
                LocalDateTime.now(),
                List.of()
        );
        ResponsavelDTO responsavelDTO= new ResponsavelDTO(
                1L,
                "Julia",
                "5555",
                "adm@gmail.com",
                "1198888",
                LocalDateTime.now(),
                List.of()
        );

        AlunosModel alunosModel = new AlunosModel(
                1L,
                "Pedro",
                "5555",
                "adm@gmail.com",
                "1198888",
                1111L,
                LocalDate.now(),
                responsaveisModel
        );

        AlunosDTO alunosDTO = new AlunosDTO(
                1L,
                "Pedro",
                "5555",
                "adm@gmail.com",
                "1198888",
                1111L,
                LocalDate.now(),
                responsaveisModel
        );

        AlunosModel alunosModelAtualizado = new AlunosModel(
                1L,
                "Marcelo",
                "5555",
                "adm@gmail.com",
                "1198888",
                1111L,
                LocalDate.now(),
                responsaveisModel
        );


        AlunosDTO alunoAtualizadoDTO = new AlunosDTO(
                1L,
                "Marcelo",
                "5555",
                "adm@gmail.com",
                "1198888",
                1111L,
                LocalDate.now(),
                responsaveisModel
        );

        Mockito.when(alunosRepository.findById(id)).thenReturn(Optional.of(alunosModel));
        Mockito.when(responsaveisRepository.findById(alunosDTO.getResponsavel().getId())).thenReturn(Optional.of(responsaveisModel));
        Mockito.when(alunosMapper.map(Mockito.any(AlunosDTO.class))).thenReturn(alunosModel);
        Mockito.when(alunosRepository.save(Mockito.any(AlunosModel.class))).thenReturn(alunosModelAtualizado);
        Mockito.when(alunosMapper.map(Mockito.any(AlunosModel.class))).thenReturn(alunoAtualizadoDTO);

        AlunosDTO alunoAtualizado = alunosService.atualizar(id, alunoAtualizadoDTO);

        assertNotNull(alunoAtualizado);
        assertEquals(id, alunoAtualizado.getId());
        assertEquals("Marcelo", alunoAtualizado.getNome());


    }

    @Test
    public void deletarAluno() {

          Long id = 1L;

          alunosService.deletar(id);

          Mockito.verify(alunosRepository, Mockito.times(1)).deleteById(id);



    }

}