package sync.spctrum.apispring.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sync.spctrum.apispring.domain.Objetivo.Objetivo;
import sync.spctrum.apispring.domain.Treino.Treino;
import sync.spctrum.apispring.domain.Usuario.Usuario;
import sync.spctrum.apispring.service.treino.TreinoService;
import sync.spctrum.apispring.service.treino.dto.treino.TreinoCreateDTO;

@ContextConfiguration(classes = {TreinoController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class TreinoControllerTest {
    @Autowired
    private TreinoController treinoController;

    @MockBean
    private TreinoService treinoService;

    /**
     * Method under test: {@link TreinoController#getListarTudo()}
     */
    @Test
    void testGetListarTudo() throws Exception {
        // Arrange
        when(treinoService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/treinos");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link TreinoController#getTreinosPorDiaDaSemana(Long)}
     */
    @Test
    void testGetTreinosPorDiaDaSemana() throws Exception {
        // Arrange
        when(treinoService.getTreinosPorDiaDaSemana(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/treinos/por-dia-da-semana/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link TreinoController#putStatusTreino(Long)}
     */
    @Test
    void testPutStatusTreino() throws Exception {
        // Arrange
        Objetivo objetivo = new Objetivo();
        objetivo.setId(1L);
        objetivo.setObjetivo("Objetivo");
        objetivo.setUsuario(new Usuario());

        Usuario usuario = new Usuario();
        usuario.preUpdate();
        usuario.setAltura(1);
        usuario.setContaAtiva(true);
        usuario.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario.setEmail("jane.doe@example.org");
        usuario.setGenero("Genero");
        usuario.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario.setId(1L);
        usuario.setImg("Img");
        usuario.setMeta("Meta");
        usuario.setNivelCondicao("Nivel Condicao");
        usuario.setNome("Nome");
        usuario.setObjetivo(objetivo);
        usuario.setPeso(10.0d);
        usuario.setPontuacao(1);
        usuario.setReceitas(new ArrayList<>());
        usuario.setSenha("Senha");

        Objetivo objetivo2 = new Objetivo();
        objetivo2.setId(1L);
        objetivo2.setObjetivo("Objetivo");
        objetivo2.setUsuario(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.preUpdate();
        usuario2.setAltura(1);
        usuario2.setContaAtiva(true);
        usuario2.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setGenero("Genero");
        usuario2.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario2.setId(1L);
        usuario2.setImg("Img");
        usuario2.setMeta("Meta");
        usuario2.setNivelCondicao("Nivel Condicao");
        usuario2.setNome("Nome");
        usuario2.setObjetivo(objetivo2);
        usuario2.setPeso(10.0d);
        usuario2.setPontuacao(1);
        usuario2.setReceitas(new ArrayList<>());
        usuario2.setSenha("Senha");

        Treino treino = new Treino();
        treino.setDataTreino(LocalDate.of(1970, 1, 1));
        treino.setDescricao("Descricao");
        treino.setId(1L);
        treino.setStatus("Status");
        treino.setTipoTreino("Tipo Treino");
        treino.setUsuario(usuario2);
        when(treinoService.putStatusTreino(Mockito.<Long>any())).thenReturn(treino);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/treinos/{id}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<TreinoResponseDTO><id>1</id><descricao>Descricao</descricao><dataTreino>1970</dataTreino><dataTreino"
                                        + ">1</dataTreino><dataTreino>1</dataTreino><tipoTreino>Tipo Treino</tipoTreino><status>Status</status>"
                                        + "<usuario><id>1</id><nome>Nome</nome><email>jane.doe@example.org</email><img>Img</img><dataNascimento"
                                        + ">0</dataNascimento><genero>Genero</genero><peso>10.0</peso><altura>1</altura><nivelCondicao>Nivel"
                                        + " Condicao</nivelCondicao><meta>Meta</meta><contaAtiva>true</contaAtiva><pontuacao>1</pontuacao><objetivo"
                                        + "><id>1</id><objetivo>Objetivo</objetivo></objetivo></usuario></TreinoResponseDTO>"));
    }

    /**
     * Method under test: {@link TreinoController#getListarTudo()}
     */
    @Test
    void testGetListarTudo2() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.preUpdate();
        usuario.setAltura(1);
        usuario.setContaAtiva(true);
        usuario.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario.setEmail("jane.doe@example.org");
        usuario.setGenero("Genero");
        usuario.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario.setId(1L);
        usuario.setImg("Img");
        usuario.setMeta("Meta");
        usuario.setNivelCondicao("Nivel Condicao");
        usuario.setNome("Nome");
        usuario.setObjetivo(new Objetivo());
        usuario.setPeso(10.0d);
        usuario.setPontuacao(1);
        usuario.setReceitas(new ArrayList<>());
        usuario.setSenha("Senha");

        Objetivo objetivo = new Objetivo();
        objetivo.setId(1L);
        objetivo.setObjetivo("Objetivo");
        objetivo.setUsuario(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.preUpdate();
        usuario2.setAltura(1);
        usuario2.setContaAtiva(true);
        usuario2.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setGenero("Genero");
        usuario2.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario2.setId(1L);
        usuario2.setImg("Img");
        usuario2.setMeta("Meta");
        usuario2.setNivelCondicao("Nivel Condicao");
        usuario2.setNome("Nome");
        usuario2.setObjetivo(objetivo);
        usuario2.setPeso(10.0d);
        usuario2.setPontuacao(1);
        usuario2.setReceitas(new ArrayList<>());
        usuario2.setSenha("Senha");

        Treino treino = new Treino();
        treino.setDataTreino(LocalDate.of(1970, 1, 1));
        treino.setDescricao("Descricao");
        treino.setId(1L);
        treino.setStatus("Status");
        treino.setTipoTreino("Tipo Treino");
        treino.setUsuario(usuario2);

        ArrayList<Treino> treinoList = new ArrayList<>();
        treinoList.add(treino);
        when(treinoService.findAll()).thenReturn(treinoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/treinos");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<List><item><id>1</id><descricao>Descricao</descricao><dataTreino>1970</dataTreino><dataTreino>1<"
                                + "/dataTreino><dataTreino>1</dataTreino><tipoTreino>Tipo Treino</tipoTreino><status>Status</status>"
                                + "<usuario><id>1</id><nome>Nome</nome><email>jane.doe@example.org</email><img>Img</img><dataNascimento"
                                + ">0</dataNascimento><genero>Genero</genero><peso>10.0</peso><altura>1</altura><nivelCondicao>Nivel"
                                + " Condicao</nivelCondicao><meta>Meta</meta><contaAtiva>true</contaAtiva><pontuacao>1</pontuacao><objetivo"
                                + "><id>1</id><objetivo>Objetivo</objetivo></objetivo></usuario></item></List>"));
    }

    /**
     * Method under test: {@link TreinoController#getListarTudo()}
     */
    @Test
    void testGetListarTudo3() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.preUpdate();
        usuario.setAltura(1);
        usuario.setContaAtiva(true);
        usuario.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario.setEmail("jane.doe@example.org");
        usuario.setGenero("Genero");
        usuario.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario.setId(1L);
        usuario.setImg("Img");
        usuario.setMeta("Meta");
        usuario.setNivelCondicao("Nivel Condicao");
        usuario.setNome("Nome");
        usuario.setObjetivo(new Objetivo());
        usuario.setPeso(10.0d);
        usuario.setPontuacao(1);
        usuario.setReceitas(new ArrayList<>());
        usuario.setSenha("Senha");

        Objetivo objetivo = new Objetivo();
        objetivo.setId(1L);
        objetivo.setObjetivo("Objetivo");
        objetivo.setUsuario(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.preUpdate();
        usuario2.setAltura(1);
        usuario2.setContaAtiva(true);
        usuario2.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setGenero("Genero");
        usuario2.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario2.setId(1L);
        usuario2.setImg("Img");
        usuario2.setMeta("Meta");
        usuario2.setNivelCondicao("Nivel Condicao");
        usuario2.setNome("Nome");
        usuario2.setObjetivo(objetivo);
        usuario2.setPeso(10.0d);
        usuario2.setPontuacao(1);
        usuario2.setReceitas(new ArrayList<>());
        usuario2.setSenha("Senha");

        Treino treino = new Treino();
        treino.setDataTreino(LocalDate.of(1970, 1, 1));
        treino.setDescricao("Descricao");
        treino.setId(1L);
        treino.setStatus("Status");
        treino.setTipoTreino("Tipo Treino");
        treino.setUsuario(usuario2);

        Usuario usuario3 = new Usuario();
        usuario3.preUpdate();
        usuario3.setAltura(0);
        usuario3.setContaAtiva(false);
        usuario3.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario3.setEmail("john.smith@example.org");
        usuario3.setGenero("42");
        usuario3.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario3.setId(2L);
        usuario3.setImg("42");
        usuario3.setMeta("42");
        usuario3.setNivelCondicao("42");
        usuario3.setNome("42");
        usuario3.setObjetivo(new Objetivo());
        usuario3.setPeso(0.5d);
        usuario3.setPontuacao(0);
        usuario3.setReceitas(new ArrayList<>());
        usuario3.setSenha("42");

        Objetivo objetivo2 = new Objetivo();
        objetivo2.setId(2L);
        objetivo2.setObjetivo("42");
        objetivo2.setUsuario(usuario3);

        Usuario usuario4 = new Usuario();
        usuario4.preUpdate();
        usuario4.setAltura(0);
        usuario4.setContaAtiva(false);
        usuario4.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario4.setEmail("john.smith@example.org");
        usuario4.setGenero("42");
        usuario4.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario4.setId(2L);
        usuario4.setImg("42");
        usuario4.setMeta("42");
        usuario4.setNivelCondicao("42");
        usuario4.setNome("42");
        usuario4.setObjetivo(objetivo2);
        usuario4.setPeso(0.5d);
        usuario4.setPontuacao(0);
        usuario4.setReceitas(new ArrayList<>());
        usuario4.setSenha("42");

        Treino treino2 = new Treino();
        treino2.setDataTreino(LocalDate.of(1970, 1, 1));
        treino2.setDescricao("42");
        treino2.setId(2L);
        treino2.setStatus("42");
        treino2.setTipoTreino("42");
        treino2.setUsuario(usuario4);

        ArrayList<Treino> treinoList = new ArrayList<>();
        treinoList.add(treino2);
        treinoList.add(treino);
        when(treinoService.findAll()).thenReturn(treinoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/treinos");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<List><item><id>2</id><descricao>42</descricao><dataTreino>1970</dataTreino><dataTreino>1</dataTreino"
                                        + "><dataTreino>1</dataTreino><tipoTreino>42</tipoTreino><status>42</status><usuario><id>2</id><nome>42"
                                        + "</nome><email>john.smith@example.org</email><img>42</img><dataNascimento>0</dataNascimento><genero>42"
                                        + "</genero><peso>0.5</peso><altura>0</altura><nivelCondicao>42</nivelCondicao><meta>42</meta><contaAtiva"
                                        + ">false</contaAtiva><pontuacao>0</pontuacao><objetivo><id>2</id><objetivo>42</objetivo></objetivo><"
                                        + "/usuario></item><item><id>1</id><descricao>Descricao</descricao><dataTreino>1970</dataTreino><dataTreino"
                                        + ">1</dataTreino><dataTreino>1</dataTreino><tipoTreino>Tipo Treino</tipoTreino><status>Status</status>"
                                        + "<usuario><id>1</id><nome>Nome</nome><email>jane.doe@example.org</email><img>Img</img><dataNascimento"
                                        + ">0</dataNascimento><genero>Genero</genero><peso>10.0</peso><altura>1</altura><nivelCondicao>Nivel"
                                        + " Condicao</nivelCondicao><meta>Meta</meta><contaAtiva>true</contaAtiva><pontuacao>1</pontuacao><objetivo"
                                        + "><id>1</id><objetivo>Objetivo</objetivo></objetivo></usuario></item></List>"));
    }

    /**
     * Method under test: {@link TreinoController#getListarTudoPorDiaEId(Long)}
     */
    @Test
    void testGetListarTudoPorDiaEId() throws Exception {
        // Arrange
        when(treinoService.findByDataTreinoAndUsuarioId(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/treinos/usuario/dia-atual/{id}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link TreinoController#getListarTudoPorDiaEId(Long)}
     */
    @Test
    void testGetListarTudoPorDiaEId2() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.preUpdate();
        usuario.setAltura(1);
        usuario.setContaAtiva(true);
        usuario.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario.setEmail("jane.doe@example.org");
        usuario.setGenero("Genero");
        usuario.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario.setId(1L);
        usuario.setImg("Img");
        usuario.setMeta("Meta");
        usuario.setNivelCondicao("Nivel Condicao");
        usuario.setNome("Nome");
        usuario.setObjetivo(new Objetivo());
        usuario.setPeso(10.0d);
        usuario.setPontuacao(1);
        usuario.setReceitas(new ArrayList<>());
        usuario.setSenha("Senha");

        Objetivo objetivo = new Objetivo();
        objetivo.setId(1L);
        objetivo.setObjetivo("Objetivo");
        objetivo.setUsuario(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.preUpdate();
        usuario2.setAltura(1);
        usuario2.setContaAtiva(true);
        usuario2.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setGenero("Genero");
        usuario2.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario2.setId(1L);
        usuario2.setImg("Img");
        usuario2.setMeta("Meta");
        usuario2.setNivelCondicao("Nivel Condicao");
        usuario2.setNome("Nome");
        usuario2.setObjetivo(objetivo);
        usuario2.setPeso(10.0d);
        usuario2.setPontuacao(1);
        usuario2.setReceitas(new ArrayList<>());
        usuario2.setSenha("Senha");

        Treino treino = new Treino();
        treino.setDataTreino(LocalDate.of(1970, 1, 1));
        treino.setDescricao("Descricao");
        treino.setId(1L);
        treino.setStatus("Status");
        treino.setTipoTreino("Tipo Treino");
        treino.setUsuario(usuario2);

        ArrayList<Treino> treinoList = new ArrayList<>();
        treinoList.add(treino);
        when(treinoService.findByDataTreinoAndUsuarioId(Mockito.<Long>any())).thenReturn(treinoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/treinos/usuario/dia-atual/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<List><item><id>1</id><descricao>Descricao</descricao><dataTreino>1970</dataTreino><dataTreino>1<"
                                + "/dataTreino><dataTreino>1</dataTreino><tipoTreino>Tipo Treino</tipoTreino><status>Status</status>"
                                + "<usuario><id>1</id><nome>Nome</nome><email>jane.doe@example.org</email><img>Img</img><dataNascimento"
                                + ">0</dataNascimento><genero>Genero</genero><peso>10.0</peso><altura>1</altura><nivelCondicao>Nivel"
                                + " Condicao</nivelCondicao><meta>Meta</meta><contaAtiva>true</contaAtiva><pontuacao>1</pontuacao><objetivo"
                                + "><id>1</id><objetivo>Objetivo</objetivo></objetivo></usuario></item></List>"));
    }

    /**
     * Method under test: {@link TreinoController#getListarTudoPorDiaEId(Long)}
     */
    @Test
    void testGetListarTudoPorDiaEId3() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.preUpdate();
        usuario.setAltura(1);
        usuario.setContaAtiva(true);
        usuario.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario.setEmail("jane.doe@example.org");
        usuario.setGenero("Genero");
        usuario.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario.setId(1L);
        usuario.setImg("Img");
        usuario.setMeta("Meta");
        usuario.setNivelCondicao("Nivel Condicao");
        usuario.setNome("Nome");
        usuario.setObjetivo(new Objetivo());
        usuario.setPeso(10.0d);
        usuario.setPontuacao(1);
        usuario.setReceitas(new ArrayList<>());
        usuario.setSenha("Senha");

        Objetivo objetivo = new Objetivo();
        objetivo.setId(1L);
        objetivo.setObjetivo("Objetivo");
        objetivo.setUsuario(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.preUpdate();
        usuario2.setAltura(1);
        usuario2.setContaAtiva(true);
        usuario2.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setGenero("Genero");
        usuario2.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario2.setId(1L);
        usuario2.setImg("Img");
        usuario2.setMeta("Meta");
        usuario2.setNivelCondicao("Nivel Condicao");
        usuario2.setNome("Nome");
        usuario2.setObjetivo(objetivo);
        usuario2.setPeso(10.0d);
        usuario2.setPontuacao(1);
        usuario2.setReceitas(new ArrayList<>());
        usuario2.setSenha("Senha");

        Treino treino = new Treino();
        treino.setDataTreino(LocalDate.of(1970, 1, 1));
        treino.setDescricao("Descricao");
        treino.setId(1L);
        treino.setStatus("Status");
        treino.setTipoTreino("Tipo Treino");
        treino.setUsuario(usuario2);

        Usuario usuario3 = new Usuario();
        usuario3.preUpdate();
        usuario3.setAltura(0);
        usuario3.setContaAtiva(false);
        usuario3.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario3.setEmail("john.smith@example.org");
        usuario3.setGenero("42");
        usuario3.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario3.setId(2L);
        usuario3.setImg("42");
        usuario3.setMeta("42");
        usuario3.setNivelCondicao("42");
        usuario3.setNome("42");
        usuario3.setObjetivo(new Objetivo());
        usuario3.setPeso(0.5d);
        usuario3.setPontuacao(0);
        usuario3.setReceitas(new ArrayList<>());
        usuario3.setSenha("42");

        Objetivo objetivo2 = new Objetivo();
        objetivo2.setId(2L);
        objetivo2.setObjetivo("42");
        objetivo2.setUsuario(usuario3);

        Usuario usuario4 = new Usuario();
        usuario4.preUpdate();
        usuario4.setAltura(0);
        usuario4.setContaAtiva(false);
        usuario4.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario4.setEmail("john.smith@example.org");
        usuario4.setGenero("42");
        usuario4.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario4.setId(2L);
        usuario4.setImg("42");
        usuario4.setMeta("42");
        usuario4.setNivelCondicao("42");
        usuario4.setNome("42");
        usuario4.setObjetivo(objetivo2);
        usuario4.setPeso(0.5d);
        usuario4.setPontuacao(0);
        usuario4.setReceitas(new ArrayList<>());
        usuario4.setSenha("42");

        Treino treino2 = new Treino();
        treino2.setDataTreino(LocalDate.of(1970, 1, 1));
        treino2.setDescricao("42");
        treino2.setId(2L);
        treino2.setStatus("42");
        treino2.setTipoTreino("42");
        treino2.setUsuario(usuario4);

        ArrayList<Treino> treinoList = new ArrayList<>();
        treinoList.add(treino2);
        treinoList.add(treino);
        when(treinoService.findByDataTreinoAndUsuarioId(Mockito.<Long>any())).thenReturn(treinoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/treinos/usuario/dia-atual/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<List><item><id>2</id><descricao>42</descricao><dataTreino>1970</dataTreino><dataTreino>1</dataTreino"
                                        + "><dataTreino>1</dataTreino><tipoTreino>42</tipoTreino><status>42</status><usuario><id>2</id><nome>42"
                                        + "</nome><email>john.smith@example.org</email><img>42</img><dataNascimento>0</dataNascimento><genero>42"
                                        + "</genero><peso>0.5</peso><altura>0</altura><nivelCondicao>42</nivelCondicao><meta>42</meta><contaAtiva"
                                        + ">false</contaAtiva><pontuacao>0</pontuacao><objetivo><id>2</id><objetivo>42</objetivo></objetivo><"
                                        + "/usuario></item><item><id>1</id><descricao>Descricao</descricao><dataTreino>1970</dataTreino><dataTreino"
                                        + ">1</dataTreino><dataTreino>1</dataTreino><tipoTreino>Tipo Treino</tipoTreino><status>Status</status>"
                                        + "<usuario><id>1</id><nome>Nome</nome><email>jane.doe@example.org</email><img>Img</img><dataNascimento"
                                        + ">0</dataNascimento><genero>Genero</genero><peso>10.0</peso><altura>1</altura><nivelCondicao>Nivel"
                                        + " Condicao</nivelCondicao><meta>Meta</meta><contaAtiva>true</contaAtiva><pontuacao>1</pontuacao><objetivo"
                                        + "><id>1</id><objetivo>Objetivo</objetivo></objetivo></usuario></item></List>"));
    }

    /**
     * Method under test: {@link TreinoController#getListarTudoPorId(Long)}
     */
    @Test
    void testGetListarTudoPorId() throws Exception {
        // Arrange
        when(treinoService.findAllByUsuarioId(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/treinos/usuario/{id}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link TreinoController#getListarTudoPorId(Long)}
     */
    @Test
    void testGetListarTudoPorId2() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.preUpdate();
        usuario.setAltura(1);
        usuario.setContaAtiva(true);
        usuario.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario.setEmail("jane.doe@example.org");
        usuario.setGenero("Genero");
        usuario.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario.setId(1L);
        usuario.setImg("Img");
        usuario.setMeta("Meta");
        usuario.setNivelCondicao("Nivel Condicao");
        usuario.setNome("Nome");
        usuario.setObjetivo(new Objetivo());
        usuario.setPeso(10.0d);
        usuario.setPontuacao(1);
        usuario.setReceitas(new ArrayList<>());
        usuario.setSenha("Senha");

        Objetivo objetivo = new Objetivo();
        objetivo.setId(1L);
        objetivo.setObjetivo("Objetivo");
        objetivo.setUsuario(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.preUpdate();
        usuario2.setAltura(1);
        usuario2.setContaAtiva(true);
        usuario2.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setGenero("Genero");
        usuario2.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario2.setId(1L);
        usuario2.setImg("Img");
        usuario2.setMeta("Meta");
        usuario2.setNivelCondicao("Nivel Condicao");
        usuario2.setNome("Nome");
        usuario2.setObjetivo(objetivo);
        usuario2.setPeso(10.0d);
        usuario2.setPontuacao(1);
        usuario2.setReceitas(new ArrayList<>());
        usuario2.setSenha("Senha");

        Treino treino = new Treino();
        treino.setDataTreino(LocalDate.of(1970, 1, 1));
        treino.setDescricao("Descricao");
        treino.setId(1L);
        treino.setStatus("Status");
        treino.setTipoTreino("Tipo Treino");
        treino.setUsuario(usuario2);

        ArrayList<Treino> treinoList = new ArrayList<>();
        treinoList.add(treino);
        when(treinoService.findAllByUsuarioId(Mockito.<Long>any())).thenReturn(treinoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/treinos/usuario/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<List><item><id>1</id><descricao>Descricao</descricao><dataTreino>1970</dataTreino><dataTreino>1<"
                                + "/dataTreino><dataTreino>1</dataTreino><tipoTreino>Tipo Treino</tipoTreino><status>Status</status>"
                                + "<usuario><id>1</id><nome>Nome</nome><email>jane.doe@example.org</email><img>Img</img><dataNascimento"
                                + ">0</dataNascimento><genero>Genero</genero><peso>10.0</peso><altura>1</altura><nivelCondicao>Nivel"
                                + " Condicao</nivelCondicao><meta>Meta</meta><contaAtiva>true</contaAtiva><pontuacao>1</pontuacao><objetivo"
                                + "><id>1</id><objetivo>Objetivo</objetivo></objetivo></usuario></item></List>"));
    }

    /**
     * Method under test: {@link TreinoController#getListarTudoPorId(Long)}
     */
    @Test
    void testGetListarTudoPorId3() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.preUpdate();
        usuario.setAltura(1);
        usuario.setContaAtiva(true);
        usuario.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario.setEmail("jane.doe@example.org");
        usuario.setGenero("Genero");
        usuario.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario.setId(1L);
        usuario.setImg("Img");
        usuario.setMeta("Meta");
        usuario.setNivelCondicao("Nivel Condicao");
        usuario.setNome("Nome");
        usuario.setObjetivo(new Objetivo());
        usuario.setPeso(10.0d);
        usuario.setPontuacao(1);
        usuario.setReceitas(new ArrayList<>());
        usuario.setSenha("Senha");

        Objetivo objetivo = new Objetivo();
        objetivo.setId(1L);
        objetivo.setObjetivo("Objetivo");
        objetivo.setUsuario(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.preUpdate();
        usuario2.setAltura(1);
        usuario2.setContaAtiva(true);
        usuario2.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setGenero("Genero");
        usuario2.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario2.setId(1L);
        usuario2.setImg("Img");
        usuario2.setMeta("Meta");
        usuario2.setNivelCondicao("Nivel Condicao");
        usuario2.setNome("Nome");
        usuario2.setObjetivo(objetivo);
        usuario2.setPeso(10.0d);
        usuario2.setPontuacao(1);
        usuario2.setReceitas(new ArrayList<>());
        usuario2.setSenha("Senha");

        Treino treino = new Treino();
        treino.setDataTreino(LocalDate.of(1970, 1, 1));
        treino.setDescricao("Descricao");
        treino.setId(1L);
        treino.setStatus("Status");
        treino.setTipoTreino("Tipo Treino");
        treino.setUsuario(usuario2);

        Usuario usuario3 = new Usuario();
        usuario3.preUpdate();
        usuario3.setAltura(0);
        usuario3.setContaAtiva(false);
        usuario3.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario3.setEmail("john.smith@example.org");
        usuario3.setGenero("42");
        usuario3.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario3.setId(2L);
        usuario3.setImg("42");
        usuario3.setMeta("42");
        usuario3.setNivelCondicao("42");
        usuario3.setNome("42");
        usuario3.setObjetivo(new Objetivo());
        usuario3.setPeso(0.5d);
        usuario3.setPontuacao(0);
        usuario3.setReceitas(new ArrayList<>());
        usuario3.setSenha("42");

        Objetivo objetivo2 = new Objetivo();
        objetivo2.setId(2L);
        objetivo2.setObjetivo("42");
        objetivo2.setUsuario(usuario3);

        Usuario usuario4 = new Usuario();
        usuario4.preUpdate();
        usuario4.setAltura(0);
        usuario4.setContaAtiva(false);
        usuario4.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario4.setEmail("john.smith@example.org");
        usuario4.setGenero("42");
        usuario4.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario4.setId(2L);
        usuario4.setImg("42");
        usuario4.setMeta("42");
        usuario4.setNivelCondicao("42");
        usuario4.setNome("42");
        usuario4.setObjetivo(objetivo2);
        usuario4.setPeso(0.5d);
        usuario4.setPontuacao(0);
        usuario4.setReceitas(new ArrayList<>());
        usuario4.setSenha("42");

        Treino treino2 = new Treino();
        treino2.setDataTreino(LocalDate.of(1970, 1, 1));
        treino2.setDescricao("42");
        treino2.setId(2L);
        treino2.setStatus("42");
        treino2.setTipoTreino("42");
        treino2.setUsuario(usuario4);

        ArrayList<Treino> treinoList = new ArrayList<>();
        treinoList.add(treino2);
        treinoList.add(treino);
        when(treinoService.findAllByUsuarioId(Mockito.<Long>any())).thenReturn(treinoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/treinos/usuario/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<List><item><id>2</id><descricao>42</descricao><dataTreino>1970</dataTreino><dataTreino>1</dataTreino"
                                        + "><dataTreino>1</dataTreino><tipoTreino>42</tipoTreino><status>42</status><usuario><id>2</id><nome>42"
                                        + "</nome><email>john.smith@example.org</email><img>42</img><dataNascimento>0</dataNascimento><genero>42"
                                        + "</genero><peso>0.5</peso><altura>0</altura><nivelCondicao>42</nivelCondicao><meta>42</meta><contaAtiva"
                                        + ">false</contaAtiva><pontuacao>0</pontuacao><objetivo><id>2</id><objetivo>42</objetivo></objetivo><"
                                        + "/usuario></item><item><id>1</id><descricao>Descricao</descricao><dataTreino>1970</dataTreino><dataTreino"
                                        + ">1</dataTreino><dataTreino>1</dataTreino><tipoTreino>Tipo Treino</tipoTreino><status>Status</status>"
                                        + "<usuario><id>1</id><nome>Nome</nome><email>jane.doe@example.org</email><img>Img</img><dataNascimento"
                                        + ">0</dataNascimento><genero>Genero</genero><peso>10.0</peso><altura>1</altura><nivelCondicao>Nivel"
                                        + " Condicao</nivelCondicao><meta>Meta</meta><contaAtiva>true</contaAtiva><pontuacao>1</pontuacao><objetivo"
                                        + "><id>1</id><objetivo>Objetivo</objetivo></objetivo></usuario></item></List>"));
    }

    /**
     * Method under test: {@link TreinoController#getTreinoPorId(Long)}
     */
    @Test
    void testGetTreinoPorId() throws Exception {
        // Arrange
        Objetivo objetivo = new Objetivo();
        objetivo.setId(1L);
        objetivo.setObjetivo("Objetivo");
        objetivo.setUsuario(new Usuario());

        Usuario usuario = new Usuario();
        usuario.preUpdate();
        usuario.setAltura(1);
        usuario.setContaAtiva(true);
        usuario.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario.setEmail("jane.doe@example.org");
        usuario.setGenero("Genero");
        usuario.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario.setId(1L);
        usuario.setImg("Img");
        usuario.setMeta("Meta");
        usuario.setNivelCondicao("Nivel Condicao");
        usuario.setNome("Nome");
        usuario.setObjetivo(objetivo);
        usuario.setPeso(10.0d);
        usuario.setPontuacao(1);
        usuario.setReceitas(new ArrayList<>());
        usuario.setSenha("Senha");

        Objetivo objetivo2 = new Objetivo();
        objetivo2.setId(1L);
        objetivo2.setObjetivo("Objetivo");
        objetivo2.setUsuario(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.preUpdate();
        usuario2.setAltura(1);
        usuario2.setContaAtiva(true);
        usuario2.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setGenero("Genero");
        usuario2.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario2.setId(1L);
        usuario2.setImg("Img");
        usuario2.setMeta("Meta");
        usuario2.setNivelCondicao("Nivel Condicao");
        usuario2.setNome("Nome");
        usuario2.setObjetivo(objetivo2);
        usuario2.setPeso(10.0d);
        usuario2.setPontuacao(1);
        usuario2.setReceitas(new ArrayList<>());
        usuario2.setSenha("Senha");

        Treino treino = new Treino();
        treino.setDataTreino(LocalDate.of(1970, 1, 1));
        treino.setDescricao("Descricao");
        treino.setId(1L);
        treino.setStatus("Status");
        treino.setTipoTreino("Tipo Treino");
        treino.setUsuario(usuario2);
        when(treinoService.findById(Mockito.<Long>any())).thenReturn(treino);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/treinos/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<TreinoResponseDTO><id>1</id><descricao>Descricao</descricao><dataTreino>1970</dataTreino><dataTreino"
                                        + ">1</dataTreino><dataTreino>1</dataTreino><tipoTreino>Tipo Treino</tipoTreino><status>Status</status>"
                                        + "<usuario><id>1</id><nome>Nome</nome><email>jane.doe@example.org</email><img>Img</img><dataNascimento"
                                        + ">0</dataNascimento><genero>Genero</genero><peso>10.0</peso><altura>1</altura><nivelCondicao>Nivel"
                                        + " Condicao</nivelCondicao><meta>Meta</meta><contaAtiva>true</contaAtiva><pontuacao>1</pontuacao><objetivo"
                                        + "><id>1</id><objetivo>Objetivo</objetivo></objetivo></usuario></TreinoResponseDTO>"));
    }

    /**
     * Method under test: {@link TreinoController#getVerificarDia(Long)}
     */
    @Test
    void testGetVerificarDia() throws Exception {
        // Arrange
        when(treinoService.findByTreinoAndUser(Mockito.<Long>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/treinos/verificar/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<Boolean>true</Boolean>"));
    }

    /**
     * Method under test: {@link TreinoController#getVerificarTreino(Long)}
     */
    @Test
    void testGetVerificarTreino() throws Exception {
        // Arrange
        Objetivo objetivo = new Objetivo();
        objetivo.setId(1L);
        objetivo.setObjetivo("Objetivo");
        objetivo.setUsuario(new Usuario());

        Usuario usuario = new Usuario();
        usuario.preUpdate();
        usuario.setAltura(1);
        usuario.setContaAtiva(true);
        usuario.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario.setEmail("jane.doe@example.org");
        usuario.setGenero("Genero");
        usuario.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario.setId(1L);
        usuario.setImg("Img");
        usuario.setMeta("Meta");
        usuario.setNivelCondicao("Nivel Condicao");
        usuario.setNome("Nome");
        usuario.setObjetivo(objetivo);
        usuario.setPeso(10.0d);
        usuario.setPontuacao(1);
        usuario.setReceitas(new ArrayList<>());
        usuario.setSenha("Senha");

        Objetivo objetivo2 = new Objetivo();
        objetivo2.setId(1L);
        objetivo2.setObjetivo("Objetivo");
        objetivo2.setUsuario(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.preUpdate();
        usuario2.setAltura(1);
        usuario2.setContaAtiva(true);
        usuario2.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setGenero("Genero");
        usuario2.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario2.setId(1L);
        usuario2.setImg("Img");
        usuario2.setMeta("Meta");
        usuario2.setNivelCondicao("Nivel Condicao");
        usuario2.setNome("Nome");
        usuario2.setObjetivo(objetivo2);
        usuario2.setPeso(10.0d);
        usuario2.setPontuacao(1);
        usuario2.setReceitas(new ArrayList<>());
        usuario2.setSenha("Senha");

        Treino treino = new Treino();
        treino.setDataTreino(LocalDate.of(1970, 1, 1));
        treino.setDescricao("Descricao");
        treino.setId(1L);
        treino.setStatus("Status");
        treino.setTipoTreino("Tipo Treino");
        treino.setUsuario(usuario2);
        when(treinoService.existsByDataTreinoAndId(Mockito.<Long>any())).thenReturn(treino);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/treinos/validar/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<TreinoResponseDTO><id>1</id><descricao>Descricao</descricao><dataTreino>1970</dataTreino><dataTreino"
                                        + ">1</dataTreino><dataTreino>1</dataTreino><tipoTreino>Tipo Treino</tipoTreino><status>Status</status>"
                                        + "<usuario><id>1</id><nome>Nome</nome><email>jane.doe@example.org</email><img>Img</img><dataNascimento"
                                        + ">0</dataNascimento><genero>Genero</genero><peso>10.0</peso><altura>1</altura><nivelCondicao>Nivel"
                                        + " Condicao</nivelCondicao><meta>Meta</meta><contaAtiva>true</contaAtiva><pontuacao>1</pontuacao><objetivo"
                                        + "><id>1</id><objetivo>Objetivo</objetivo></objetivo></usuario></TreinoResponseDTO>"));
    }

    /**
     * Method under test: {@link TreinoController#postTreino(TreinoCreateDTO)}
     */
    @Test
    void testPostTreino() throws Exception {
        // Arrange
        Objetivo objetivo = new Objetivo();
        objetivo.setId(1L);
        objetivo.setObjetivo("Objetivo");
        objetivo.setUsuario(new Usuario());

        Usuario usuario = new Usuario();
        usuario.preUpdate();
        usuario.setAltura(1);
        usuario.setContaAtiva(true);
        usuario.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario.setEmail("jane.doe@example.org");
        usuario.setGenero("Genero");
        usuario.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario.setId(1L);
        usuario.setImg("Img");
        usuario.setMeta("Meta");
        usuario.setNivelCondicao("Nivel Condicao");
        usuario.setNome("Nome");
        usuario.setObjetivo(objetivo);
        usuario.setPeso(10.0d);
        usuario.setPontuacao(1);
        usuario.setReceitas(new ArrayList<>());
        usuario.setSenha("Senha");

        Objetivo objetivo2 = new Objetivo();
        objetivo2.setId(1L);
        objetivo2.setObjetivo("Objetivo");
        objetivo2.setUsuario(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.preUpdate();
        usuario2.setAltura(1);
        usuario2.setContaAtiva(true);
        usuario2.setDataNascimento(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setGenero("Genero");
        usuario2.setHoraSenhaAtualizacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        usuario2.setId(1L);
        usuario2.setImg("Img");
        usuario2.setMeta("Meta");
        usuario2.setNivelCondicao("Nivel Condicao");
        usuario2.setNome("Nome");
        usuario2.setObjetivo(objetivo2);
        usuario2.setPeso(10.0d);
        usuario2.setPontuacao(1);
        usuario2.setReceitas(new ArrayList<>());
        usuario2.setSenha("Senha");

        Treino treino = new Treino();
        treino.setDataTreino(LocalDate.of(1970, 1, 1));
        treino.setDescricao("Descricao");
        treino.setId(1L);
        treino.setStatus("Status");
        treino.setTipoTreino("Tipo Treino");
        treino.setUsuario(usuario2);
        when(treinoService.save(Mockito.<Treino>any(), Mockito.<Long>any())).thenReturn(treino);

        TreinoCreateDTO treinoCreateDTO = new TreinoCreateDTO();
        treinoCreateDTO.setDataTreino(null);
        treinoCreateDTO.setDescricao("Descricao");
        treinoCreateDTO.setStatus("Status");
        treinoCreateDTO.setTipoTreino("Tipo Treino");
        treinoCreateDTO.setUsuarioId(1L);
        String content = (new ObjectMapper()).writeValueAsString(treinoCreateDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/treinos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(treinoController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<TreinoResponseDTO><id>1</id><descricao>Descricao</descricao><dataTreino>1970</dataTreino><dataTreino"
                                        + ">1</dataTreino><dataTreino>1</dataTreino><tipoTreino>Tipo Treino</tipoTreino><status>Status</status>"
                                        + "<usuario><id>1</id><nome>Nome</nome><email>jane.doe@example.org</email><img>Img</img><dataNascimento"
                                        + ">0</dataNascimento><genero>Genero</genero><peso>10.0</peso><altura>1</altura><nivelCondicao>Nivel"
                                        + " Condicao</nivelCondicao><meta>Meta</meta><contaAtiva>true</contaAtiva><pontuacao>1</pontuacao><objetivo"
                                        + "><id>1</id><objetivo>Objetivo</objetivo></objetivo></usuario></TreinoResponseDTO>"));
    }
}
