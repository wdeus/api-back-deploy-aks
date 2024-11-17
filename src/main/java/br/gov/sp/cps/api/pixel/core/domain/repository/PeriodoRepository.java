package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.Periodo;

import java.util.List;

public interface PeriodoRepository {
    List<Periodo> salvar(List<Periodo> periodos);
}
