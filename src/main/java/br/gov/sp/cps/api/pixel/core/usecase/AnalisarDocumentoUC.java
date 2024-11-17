package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.repository.AnaliseRepository;
import br.gov.sp.cps.api.pixel.core.domain.repository.DocumentoRepository;
import br.gov.sp.cps.api.pixel.core.domain.repository.MapeamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class AnalisarDocumentoUC {

    private final AnaliseRepository analiseRepository;
    private final DocumentoRepository documentoRepository;
    private final MapeamentoRepository mapeamentoRepository;

    public void executar(byte[] document){
        InputStream inputStream = converte(document);
        String documentoFormatado = documentoRepository.extrair(inputStream);
        String mapeamento = analiseRepository.processarDados(documentoFormatado);
        mapeamentoRepository.popularEntidades(mapeamento);
    }

    private InputStream converte(byte[] document) {
        return new ByteArrayInputStream(document);
    }
}
