package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.PixelApplication;
import br.gov.sp.cps.api.pixel.core.domain.dto.projection.ComboboxProjection;
import br.gov.sp.cps.api.pixel.core.usecase.ObterDimensoesUC;
import br.gov.sp.cps.api.pixel.core.usecase.ObterFatosUC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = PixelApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username="admin",roles={"USER","ADMIN"})
public class FiltroControllerTest {

    private ObterFatosUC obterFatosUC;
    private ObterDimensoesUC obterDimensoesUC;
    private FiltroController filtroController;

    @BeforeEach
    void setUp() {
        obterFatosUC = Mockito.mock(ObterFatosUC.class);
        obterDimensoesUC = Mockito.mock(ObterDimensoesUC.class);
        filtroController = new FiltroController(obterFatosUC, obterDimensoesUC);
    }

    @Test
    void testListarFatos() {
        List<ComboboxProjection> expected = Collections.singletonList(Mockito.mock(ComboboxProjection.class));
        when(obterFatosUC.executar()).thenReturn(expected);

        ResponseEntity<List<ComboboxProjection>> response = filtroController.listarFatos();

        assertEquals(ResponseEntity.ok(expected), response);
    }

    @Test
    void testListarDimensoesPorFato() {
        String fato = "fato_vaga";

        List<ComboboxProjection> expected = Collections.singletonList(Mockito.mock(ComboboxProjection.class));
        when(obterDimensoesUC.executar(fato)).thenReturn(expected);

        ResponseEntity<List<ComboboxProjection>> response = filtroController.listarDimensoesPorFato(fato);

        assertEquals(ResponseEntity.ok(expected), response);
    }
}
