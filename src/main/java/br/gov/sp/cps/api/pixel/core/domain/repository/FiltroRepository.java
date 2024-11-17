package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.dto.projection.ComboboxProjection;

import java.util.List;

public interface FiltroRepository {

    List<ComboboxProjection> buscarFatos();

    List<ComboboxProjection> buscarDimensoesPorFato(String fato);
}
