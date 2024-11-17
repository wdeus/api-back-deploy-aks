package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.dto.Eixo;
import br.gov.sp.cps.api.pixel.core.domain.dto.Filtro;
import br.gov.sp.cps.api.pixel.core.domain.dto.command.VisualizarCardCommand;
import br.gov.sp.cps.api.pixel.core.domain.repository.CardRepository;
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

import static org.junit.jupiter.api.Assertions.*;


class VisualizarCardUCTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
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
        filtro1.setComparador("igual a"); // na realidade é =
        filtro1.setValor("aprovado");

        Filtro filtro2 = new Filtro();
        filtro2.setNome("Filtro Data");
        filtro2.setCampo("dataCriacao");
        filtro2.setComparador("maior que"); // o certo é >
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
    @SuppressWarnings({"unchecked"})
    void devVisualizarCard() { // mudar nome do teste
        // Arrange
        List<Object> expectedCards = new ArrayList<>(); // Especifica um tipo concreto para a lista
        expectedCards.add(new Object()); // Simula um retorno válido

        // Casting explícito do tipo de retorno
        when(cardRepository.getCard(command)).thenReturn((List) expectedCards);

        // Act
        List<?> result = visualizarCardUC.executar(command);

        // Assert
        assertEquals(expectedCards, result);
        verify(cardRepository, times(1)).getCard(command);
    }
}