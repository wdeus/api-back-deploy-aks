package br.gov.sp.cps.api.pixel.core.domain.repository;

import org.springframework.stereotype.Repository;

import java.io.InputStream;

@Repository
public interface DocumentoRepository {
    String extrair(InputStream inputStream);
}
