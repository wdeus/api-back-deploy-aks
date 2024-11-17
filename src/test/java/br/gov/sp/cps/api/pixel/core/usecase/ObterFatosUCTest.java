package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.dto.projection.ComboboxProjection;
import br.gov.sp.cps.api.pixel.core.domain.repository.FiltroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ObterFatosUCTest {

    private FiltroRepository filtroRepository;
    private ObterFatosUC obterFatosUC;

    @BeforeEach
    void setUp() {
        filtroRepository = Mockito.mock(FiltroRepository.class);
        obterFatosUC = new ObterFatosUC(filtroRepository);
    }

    @Test
    void testExecutar() {
        List<ComboboxProjection> expected = Collections.singletonList(Mockito.mock(ComboboxProjection.class));
        when(filtroRepository.buscarFatos()).thenReturn(expected);

        List<ComboboxProjection> result = obterFatosUC.executar();

        assertEquals(expected, result);
    }
}
