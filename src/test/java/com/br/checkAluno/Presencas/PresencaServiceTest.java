package com.br.checkAluno.Presencas;

import com.br.checkAluno.Alunos.AlunosDTO;
import com.br.checkAluno.Alunos.AlunosModel;
import com.br.checkAluno.Alunos.AlunosRepository;
import com.br.checkAluno.Email.EmailSerivce;
import com.br.checkAluno.Resonsaveis.ResponsaveisModel;
import com.br.checkAluno.Resonsaveis.ResponsavelDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PresencaServiceTest {

    @InjectMocks
    private PresencaService presencaService;

    @Mock
    private PresencaRepository presencaRepository;

    @Mock
    private PresencaMapper presencaMapper;

    @Mock
    private AlunosRepository alunosRepository;

    @Mock
    private EmailSerivce emailSerivce;

    @Test
    public void retornarUmaListaDePresenca() {

        PresencaModel presencaModel = new PresencaModel(
                1L,
                LocalDateTime.now(),
                true,
                new AlunosModel(
                        1L,
                        "Pedro",
                        "555",
                        "adm@gmail.com",
                        "1198888888",
                        1111L,
                        LocalDate.now(),
                        new ResponsaveisModel(
                                1L,
                                "Julia",
                                "555",
                                "adm@gmail.com",
                                "5555",
                                LocalDateTime.now(),
                                List.of()
                        )
                )
        );

        PresencaDTO presencaDTO = new PresencaDTO(
                1L,
                LocalDateTime.now(),
                true,
                new AlunosModel(
                        1L,
                        "Pedro",
                        "555",
                        "adm@gmail.com",
                        "1198888888",
                        1111L,
                        LocalDate.now(),
                        new ResponsaveisModel(
                                1L,
                                "Julia",
                                "555",
                                "adm@gmail.com",
                                "5555",
                                LocalDateTime.now(),
                                List.of()
                        )
                )
        );

        Mockito.when(presencaRepository.findAll()).thenReturn(Collections.singletonList(presencaModel));
        Mockito.when(presencaMapper.map(Mockito.any(PresencaModel.class))).thenReturn(presencaDTO);

        List<PresencaDTO> presenca = presencaService.listarPresenca();

        assertNotNull(presenca);
        assertEquals(1L, presenca.get(0).getId());
        assertEquals("Pedro", presenca.get(0).getAluno().getNome());
        assertEquals(1, presenca.size());
    }

    @Test
    public void listarPresencaPorID() {

        Long id = 1L;

        PresencaModel presencaModel = new PresencaModel(
                1L,
                LocalDateTime.now(),
                true,
                new AlunosModel(
                        1L,
                        "Pedro",
                        "555",
                        "adm@gmail.com",
                        "1198888888",
                        1111L,
                        LocalDate.now(),
                        new ResponsaveisModel(
                                1L,
                                "Julia",
                                "555",
                                "adm@gmail.com",
                                "5555",
                                LocalDateTime.now(),
                                List.of()
                        )
                )
        );

        PresencaDTO presencaDTO = new PresencaDTO(
                1L,
                LocalDateTime.now(),
                true,
                new AlunosModel(
                        1L,
                        "Pedro",
                        "555",
                        "adm@gmail.com",
                        "1198888888",
                        1111L,
                        LocalDate.now(),
                        new ResponsaveisModel(
                                1L,
                                "Julia",
                                "555",
                                "adm@gmail.com",
                                "5555",
                                LocalDateTime.now(),
                                List.of()
                        )
                )
        );

        Mockito.when(presencaRepository.findById(id)).thenReturn(Optional.of(presencaModel));
        Mockito.when(presencaMapper.map(Mockito.any(PresencaModel.class))).thenReturn(presencaDTO);

        PresencaDTO presencaEncontrada = presencaService.listarPorId(id);

        assertNotNull(presencaEncontrada);
        assertEquals(id, presencaEncontrada.getId());
        assertEquals("Pedro", presencaEncontrada.getAluno().getNome());

        Mockito.verify(presencaRepository, Mockito.times(1)).findById(id);
        Mockito.verify(presencaMapper, Mockito.times(1)).map(Mockito.any(PresencaModel.class));
    }

    @Test
    public void criarPresenca() {

        Long id = 1L;

        AlunosModel alunosModel = new AlunosModel();
        ResponsaveisModel responsaveisModel = new ResponsaveisModel();
        responsaveisModel.setEmail("adm@gmail.com");

        alunosModel.setId(id);
        alunosModel.setNome("Pedro");
        alunosModel.setCpf("555");
        alunosModel.setMatricula(1555L);
        alunosModel.setResponsavel(responsaveisModel);

        AlunosDTO alunosDTO = new AlunosDTO();

        alunosDTO.setId(id);
        alunosDTO.setNome("Pedro");
        alunosDTO.setCpf("555");
        alunosDTO.setMatricula(1555L);


        PresencaModel presencaModel = new PresencaModel();

        presencaModel.setAluno(alunosModel);
        presencaModel.setStatus(false);

        PresencaDTO presencaDTO = new PresencaDTO();

        presencaDTO.setAluno(alunosModel);
        presencaDTO.setStatus(false);


        Mockito.when(alunosRepository.findById(id)).thenReturn(Optional.of(alunosModel));
        Mockito.when(presencaMapper.map(Mockito.any(PresencaDTO.class))).thenReturn(presencaModel);
        Mockito.when(presencaRepository.save(Mockito.any(PresencaModel.class))).thenReturn(presencaModel);
        Mockito.when(presencaMapper.map(Mockito.any(PresencaModel.class))).thenReturn(presencaDTO);

        PresencaDTO presencaEnviado = presencaService.criar(presencaDTO);

        assertNotNull(presencaEnviado);
        assertEquals(1, presencaEnviado.getAluno().getId());
        assertEquals("Pedro", presencaEnviado.getAluno().getNome());
        Mockito.verify(presencaRepository, Mockito.times(1)).save(presencaModel);
        Mockito.verify(emailSerivce, Mockito.times(1)).enviarEmail(
                "adm@gmail.com",
                "Aviso de Ausência Pedro",
                "Olá, informamos que o aluno Pedro registrou uma falta"
        );
    }

    @Test
    public void atualizarPresenca() {

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

        AlunosModel alunosModel = new AlunosModel(
                1L,
                "Pedro",
                "adm@gmail.com",
                "555",
                "1198888888",
                1111L,
                LocalDate.now(),
                responsaveisModel
        );

        AlunosDTO alunosDTO = new AlunosDTO(
                1L,
                "Pedro",
                "adm@gmail.com",
                "555",
                "1198888888",
                1111L,
                LocalDate.now(),
                responsaveisModel
        );

        PresencaModel presencaModel = new PresencaModel(
                1L,
                LocalDateTime.now(),
                true,
                alunosModel
        );

        PresencaDTO presencaDTO = new PresencaDTO(
                1L,
                LocalDateTime.now(),
                true,
                alunosModel
        );

        Mockito.when(presencaRepository.findById(id)).thenReturn(Optional.of(presencaModel));
        Mockito.when(presencaRepository.save(Mockito.any(PresencaModel.class))).thenReturn(presencaModel);

        Mockito.when(presencaMapper.map(Mockito.any(PresencaDTO.class))).thenReturn(presencaModel);
        Mockito.when(presencaMapper.map(Mockito.any(PresencaModel.class))).thenReturn(presencaDTO);

        PresencaDTO presencaAtualizada = presencaService.atualizar(id, presencaDTO);

        assertNotNull(presencaAtualizada);
        assertEquals(1, presencaAtualizada.getId());
        assertEquals("Julia", presencaAtualizada.getAluno().getResponsavel().getNome());
        assertTrue(presencaAtualizada.getStatus());

        Mockito.verify(presencaRepository, Mockito.times(1)).save(Mockito.any(PresencaModel.class));
        Mockito.verify(presencaRepository, Mockito.times(1)).findById(presencaAtualizada.getId());

    }

    @Test
    public void deletarPresenca() {

        Long id = 1L;

        presencaService.deletar(id);

        Mockito.verify(presencaRepository, Mockito.times(1)).deleteById(id);


    }

}

