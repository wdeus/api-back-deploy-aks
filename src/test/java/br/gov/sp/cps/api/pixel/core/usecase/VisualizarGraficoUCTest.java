package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.dto.Eixo;
import br.gov.sp.cps.api.pixel.core.domain.dto.Filtro;
import br.gov.sp.cps.api.pixel.core.domain.dto.command.VisualizarGraficoCommand;
import br.gov.sp.cps.api.pixel.core.domain.repository.GraficoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import java.util.ArrayList;
import java.util.List;

class VisualizarGraficoUCTest {
    @Mock
    private GraficoRepository graficoRepository;

    @InjectMocks
    private VisualizarGraficoUC visualizarGraficoUC;

    private VisualizarGraficoCommand command;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);

        // Criação dos objetos Eixo
        Eixo eixoX = new Eixo();
        eixoX.setNome("Eixo X Teste");
        eixoX.setCampo("campoX");

        Eixo eixoY = new Eixo();
        eixoY.setNome("Eixo Y Teste");
        eixoY.setCampo("campoY");

        // Criação de filtros
        Filtro filtro1 = new Filtro();
        filtro1.setNome("Filtro Categoria");
        filtro1.setCampo("categoria");
        filtro1.setComparador("igual a"); // =
        filtro1.setValor("financeiro");

        List<Filtro> filtros = new ArrayList<>();
        filtros.add(filtro1);

        // Criação do comando
        command = new VisualizarGraficoCommand();
        command.setEixoX(eixoX);
        command.setEixoY(eixoY);
        command.setFiltros(filtros);
    }


    @Test
    @SuppressWarnings("unchecked")
    void devVisualizarGrafico() {
        // Arrange
        List<Object> expectedResponse = new ArrayList<>();
        expectedResponse.add(new Object()); // Simula um retorno válido

        when(graficoRepository.getGrafico(command)).thenReturn((List) expectedResponse);

        // Act
        List<?> result = visualizarGraficoUC.executar(command);

        // Assert
        verify(graficoRepository, times(1)).getGrafico(command);

    }
}