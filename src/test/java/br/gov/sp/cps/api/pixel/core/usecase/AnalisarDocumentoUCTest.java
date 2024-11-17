package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.repository.AnaliseRepository;
import br.gov.sp.cps.api.pixel.core.domain.repository.DocumentoRepository;
import br.gov.sp.cps.api.pixel.core.domain.repository.MapeamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AnalisarDocumentoUCTest {

    @Mock
    private AnaliseRepository analiseRepository;

    @Mock
    private DocumentoRepository documentoRepository;

    @Mock
    private MapeamentoRepository mapeamentoRepository;

    @InjectMocks
    private AnalisarDocumentoUC analisarDocumentoUC;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executarTest() {
        // Dados de entrada
        byte[] documento = "conteudo".getBytes();
        InputStream inputStream = new ByteArrayInputStream(documento);
        String documentoFormatado = null;
        String mapeamento = "mapeamento";

        // Configurando o comportamento dos mocks
        when(documentoRepository.extrair(inputStream)).thenReturn(documentoFormatado);
        when(analiseRepository.processarDados(documentoFormatado)).thenReturn(mapeamento);

        // Executar o método que será testado
        analisarDocumentoUC.executar(documento);

        // Verificando se os métodos dos mocks foram chamados corretamente
        verify(documentoRepository).extrair(any(InputStream.class));
        verify(analiseRepository).processarDados(documentoFormatado);
        verify(mapeamentoRepository).popularEntidades(mapeamento);
    }
}
