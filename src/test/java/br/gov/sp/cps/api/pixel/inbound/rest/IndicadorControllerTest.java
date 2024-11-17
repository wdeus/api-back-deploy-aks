package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.PixelApplication;
import br.gov.sp.cps.api.pixel.core.domain.dto.command.IndicadorCommand;
import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.usecase.IndicadorUC;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PixelApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username="admin",roles={"USER","ADMIN"})
class IndicadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IndicadorUC indicadorUC;

    @Test
    void criarIndicador() throws Exception{
        String json = "{"
                + "\"indicador\": {"
                + "\"nome\": \"Indicador B\","
                + "\"campo\": \"campoB\","
                + "\"comparador\": \"<\","
                + "\"valor\": \"101\""
                + "},"
                + "\"filtro\": {"
                + "\"nome\": \"Filtro B\","
                + "\"campo\": \"campoFiltroB\","
                + "\"comparador\": \"+\","
                + "\"valor\": \"201\""
                + "},"
                + "\"usuario\": \"usuario2\","
                + "\"descricao\": \"vou sim\""
                + "}";

        Indicador indicadorRetornado = new Indicador();
        when(indicadorUC.postar(any(IndicadorCommand.class))).thenReturn(indicadorRetornado);
        mockMvc.perform(post("/indicadores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(indicadorUC).postar(any(IndicadorCommand.class));

    }

    @Test
    void listarTodos() throws Exception {
        Indicador indicador1 = new Indicador();
        indicador1.setUsuario("usuario1");

        Indicador indicador2 = new Indicador();
        indicador2.setUsuario("usuario2");

        List<Indicador> indicadores = Arrays.asList(indicador1, indicador2);

        when(indicadorUC.listar()).thenReturn(indicadores);
        mockMvc.perform(get("/indicadores"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(indicadorUC, times(1)).listar();
    }

    @Test
    void buscarPorId() throws Exception {
        Indicador indicador = new Indicador();
        indicador.setUsuario("usuario2");
        when(indicadorUC.buscarPorId(1)).thenReturn(Optional.of(indicador));

        mockMvc.perform(get("/indicadores/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(indicadorUC, times(1)).buscarPorId(1);
    }

    @Test
    void deletarIndicador() throws Exception {
        int id = 1;
        when(indicadorUC.buscarPorId(id)).thenReturn(Optional.of(new Indicador()));
        doNothing().when(indicadorUC).deletar(id);
        mockMvc.perform(delete("/indicadores/{id}", id))
                .andExpect(status().isNoContent());
        verify(indicadorUC, times(1)).deletar(id);
    }



}