package br.gov.sp.cps.api.pixel.core.domain.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseRepository {

    String processarDados(String documento);
}
