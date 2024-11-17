package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.PixelApplication;
import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.domain.repository.IndicadorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PixelApplication.class)
@ActiveProfiles("test")
class NotificacaoUCTest {

    @Autowired
    private NotificacaoUC notificacaoUC;

    @Autowired
    private IndicadorRepository indicadorRepository;

    @Test
    void deveRetornarIndicadoresEEnviarEmail() {
        Indicador indicador = new Indicador();
        indicador.setIndicadorNome("fato_vaga");
        indicador.setIndicadorCampo("tempo_medio_processo");
        indicador.setIndicadorComparador(">");
        indicador.setIndicadorValor("100");
        indicador.setFiltroNome("dim_vaga");
        indicador.setFiltroCampo("titulo");
        indicador.setFiltroComparador("=");
        indicador.setFiltroValor("Dev Java");
        indicador.setUsuario("ADMIN");
        indicador.setDescricao("Indicador Atingido");
        indicadorRepository.salvar(indicador);

        notificacaoUC.verificarIndicadores("ADMIN");

        List<Indicador> indicadores = indicadorRepository.localizarPorUsuario("ADMIN");

        assertNotNull(indicadores);
        assertFalse(indicadores.isEmpty());
    }

    @Test
    void naoDeveEnviarEmailQuandoNaoHouverIndicador() {
        notificacaoUC.verificarIndicadores("usuarioSemIndicadores");
        List<Indicador> indicadores = indicadorRepository.localizarPorUsuario("usuarioSemIndicadores");
        assertTrue(indicadores.isEmpty());
    }
}
