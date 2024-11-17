package br.gov.sp.cps.api.pixel.inbound.rest;


import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import br.gov.sp.cps.api.pixel.PixelApplication;
import br.gov.sp.cps.api.pixel.core.domain.dto.Eixo;
import br.gov.sp.cps.api.pixel.core.domain.dto.Filtro;
import br.gov.sp.cps.api.pixel.core.domain.dto.command.VisualizarCardCommand;
import br.gov.sp.cps.api.pixel.core.usecase.VisualizarCardUC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = PixelApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username="admin",roles={"USER","ADMIN"})
class CardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisualizarCardUC visualizarCardUC;

    private VisualizarCardCommand command;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Criação de um Eixo para o comando
        Eixo eixoX = new Eixo();
        eixoX.setNome("Eixo de Teste");
        eixoX.setCampo("campoTeste");

        // Criação de filtros
        Filtro filtro1 = new Filtro();
        filtro1.setNome("Filtro Status");
        filtro1.setCampo("status");
        filtro1.setComparador("igual a");
        filtro1.setValor("aprovado");

        Filtro filtro2 = new Filtro();
        filtro2.setNome("Filtro Data");
        filtro2.setCampo("dataCriacao");
        filtro2.setComparador("maior que");
        filtro2.setValor("2024-01-01");

        List<Filtro> filtros = new ArrayList<>();
        filtros.add(filtro1);
        filtros.add(filtro2);

        // Criação do comando com Eixo e Filtros
        command = new VisualizarCardCommand();
        command.setEixoX(eixoX);
        command.setFiltros(filtros);
    }

    @Test
    public void devCardController() throws Exception{
        // Arrange

        // Casting explícito para List<?>
        when(visualizarCardUC.executar(command)).thenReturn(List.of());

        // Converte o comando para JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String commandJson = objectMapper.writeValueAsString(command);

        // Act & Assert
        mockMvc.perform(post("/visualizacao/card")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(commandJson))
                .andExpect(status().isOk()); // Verifica se o status da resposta é 200 OK

        // Verifica se o método executar foi chamado exatamente uma vez
        verify(visualizarCardUC, times(1)).executar(command);
    }
}