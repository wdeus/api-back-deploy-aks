package br.gov.sp.cps.api.pixel.outbound.parser;


import br.gov.sp.cps.api.pixel.core.domain.repository.DocumentoRepository;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.parser.apache.poi.ApachePoiDocumentParser;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class DocumentRepositoryImpl implements DocumentoRepository {
    @Override
    public String extrair(InputStream inputStream) {
        DocumentParser parser = new ApachePoiDocumentParser();
        Document document = parser.parse(inputStream);
        return document.text();
    }
}
