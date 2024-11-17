package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.IndicadorCommand;
import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.domain.repository.IndicadorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class IndicadorUCTest {

    @Mock
    private IndicadorRepository indicadorRepository;

    @InjectMocks
    private IndicadorUC indicadorUC;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void postar() throws JsonProcessingException {
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
                + "\"descricao\": \"descricao\""
                + "}";

        IndicadorCommand indicadorCommand = objectMapper.readValue(json, IndicadorCommand.class);
        Indicador indicadorRetornado = new Indicador();
        when(indicadorRepository.salvar(any(Indicador.class))).thenReturn(indicadorRetornado);
        Indicador resultado = indicadorUC.postar(indicadorCommand);
        assertEquals(indicadorRetornado, resultado);
        verify(indicadorRepository).salvar(any(Indicador.class));
    }

    @Test
    void listar() {
        Indicador indicador1 = new Indicador();
        indicador1.setUsuario("usuario1");
        indicador1.setIndicadorComparador("=");

        Indicador indicador2 = new Indicador();
        indicador2.setUsuario("usuario2");
        indicador2.setIndicadorComparador("+");

        List<Indicador> indicadoresSimulados = Arrays.asList(indicador1, indicador2);
        when(indicadorRepository.listarTodos()).thenReturn(indicadoresSimulados);
        List<Indicador> resultado = indicadorUC.listar();
        verify(indicadorRepository).listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("usuario1", resultado.get(0).getUsuario());
        assertEquals("usuario2", resultado.get(1).getUsuario());
    }
    @Test
    void buscarPorId() {
        Indicador indicadorSimulado = new Indicador();
        indicadorSimulado.setUsuario("usuario1");
        when(indicadorRepository.buscarPorId(1)).thenReturn(Optional.of(indicadorSimulado));
        Optional<Indicador> resultado = indicadorUC.buscarPorId(1);
        verify(indicadorRepository).buscarPorId(1);
        assertTrue(resultado.isPresent());
        assertEquals("usuario1", resultado.get().getUsuario());
    }

    @Test
    void testarDeletarPorId() {
        int id = 1;
        doNothing().when(indicadorRepository).deletarPorId(id);

        indicadorUC.deletar(id);

        verify(indicadorRepository, times(1)).deletarPorId(id);
    }
}