package com.br.checkAluno.Resonsaveis;

import com.br.checkAluno.Alunos.AlunosModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ResponsaveisServiceTest {

    @InjectMocks
    private ResponsaveisService responsaveisService;

    @Mock
    private ResponsaveisRepository responsaveisRepository;

    @Mock
    private ResponsaveisMapper responsaveisMapper;

    @Test
    public void deveRetornarUmaListaDeUsuarios() {

        ResponsaveisModel responsaveisModel = new ResponsaveisModel(
                1L,
                "Julia",
                "555",
                "adm@gmail.com",
                "5555",
                LocalDateTime.now(),
                List.of()
        );

        ResponsavelDTO responsavelDTO = new ResponsavelDTO(
                1L,
                "Julia",
                "555",
                "adm@gmail.com",
                "5555",
                LocalDateTime.now(),
                List.of()
        );

        Mockito.when(responsaveisRepository.findAll()).thenReturn(Collections.singletonList(responsaveisModel));
        Mockito.when(responsaveisMapper.map(Mockito.any(ResponsaveisModel.class))).thenReturn(responsavelDTO);
        List<ResponsavelDTO> responsaveis = responsaveisService.listar();
    }

    @Test
    public void listarResponsavelPorID() {

        Long id = 1L;

        ResponsaveisModel responsaveisModel = new ResponsaveisModel(
                1L,
                "Julia",
                "555",
                "adm@gmail.com",
                "5555",
                LocalDateTime.now(),
                List.of()
        );

        ResponsavelDTO responsavelDTO = new ResponsavelDTO(
                1L,
                "Julia",
                "555",
                "adm@gmail.com",
                "5555",
                LocalDateTime.now(),
                List.of()
        );


        Mockito.when(responsaveisRepository.findById(id)).thenReturn(Optional.of(responsaveisModel));
        Mockito.when(responsaveisMapper.map(responsaveisModel)).thenReturn(responsavelDTO);

        ResponsavelDTO responsavelEncontrado = responsaveisService.listarPorId(id);

        assertNotNull(responsavelEncontrado);
        assertEquals(id, responsavelEncontrado.getId());
        assertEquals("Julia", responsaveisModel.getNome());

        Mockito.verify(responsaveisRepository, Mockito.times(1)).findById(id);
        Mockito.verify(responsaveisMapper, Mockito.times(1)).map(responsaveisModel);

    }

    @Test
    public void criarResponsavel() {

        ResponsaveisModel responsaveisModel = new ResponsaveisModel(
                1L,
                "Julia",
                "555",
                "adm@gmail.com",
                "5555",
                LocalDateTime.now(),
                List.of()
        );

        ResponsavelDTO responsavelDTO = new ResponsavelDTO(
                1L,
                "Julia",
                "555",
                "adm@gmail.com",
                "5555",
                LocalDateTime.now(),
                List.of()
        );

        ResponsavelDTO responsavelDTOEntrada = new ResponsavelDTO(
                null,
                "Julia",
                "555",
                "adm@gmail.com",
                "5555",
                null,
                List.of()
        );

        Mockito.when(responsaveisMapper.map(Mockito.any(ResponsavelDTO.class))).thenReturn(responsaveisModel);
        Mockito.when(responsaveisRepository.save(Mockito.any(ResponsaveisModel.class))).thenReturn(responsaveisModel);
        Mockito.when(responsaveisMapper.map(Mockito.any(ResponsaveisModel.class))).thenReturn(responsavelDTO);

        ResponsavelDTO responsavelConvertidoComID = responsaveisService.criar(responsavelDTOEntrada);

        assertNotNull(responsavelConvertidoComID);
        assertEquals("Julia", responsavelConvertidoComID.getNome());
        assertEquals(1L, responsavelConvertidoComID.getId());


    }

    @Test
    public void atualizarResponsavel() {

        ResponsaveisModel responsaveisModel = new ResponsaveisModel(
                1L,
                "Julia",
                "555",
                "adm@gmail.com",
                "5555",
                LocalDateTime.now(),
                List.of()
        );

        ResponsaveisModel responsaveisModelSalvado = new ResponsaveisModel(
                1L,
                "Carla",
                "552",
                "adm@gmail.com",
                "1555",
                LocalDateTime.now(),
                List.of()
        );

        ResponsavelDTO responsavelDTO = new ResponsavelDTO(
                1L,
                "Carla",
                "552",
                "adm@gmail.com",
                "1555",
                LocalDateTime.now(),
                List.of()
        );


        Mockito.when(responsaveisRepository.findById(responsaveisModel.getId())).thenReturn(Optional.of(responsaveisModel));

        Mockito.when(responsaveisMapper.map(Mockito.any(ResponsavelDTO.class))).thenReturn(responsaveisModel);

        Mockito.when(responsaveisRepository.save(Mockito.any(ResponsaveisModel.class))).thenReturn(responsaveisModelSalvado);

        Mockito.when(responsaveisMapper.map(Mockito.any(ResponsaveisModel.class))).thenReturn(responsavelDTO);

        ResponsavelDTO responsavelAtualizado = responsaveisService.atualizar(1L, responsavelDTO);


        assertNotNull(responsavelAtualizado);
        assertEquals("Carla", responsavelAtualizado.getNome());
        assertEquals(1L, responsavelAtualizado.getId());

    }

    @Test
    public void deletarResponsavel() {

        Long id = 1L;

        ResponsaveisModel responsaveisModel = new ResponsaveisModel(
                1L,
                "Julia",
                "555",
                "adm@gmail.com",
                "5555",
                LocalDateTime.now(),
                List.of()
        );

        responsaveisService.deletar(id);

        Mockito.verify(responsaveisRepository, Mockito.times(1)).deleteById(id);


    }

}