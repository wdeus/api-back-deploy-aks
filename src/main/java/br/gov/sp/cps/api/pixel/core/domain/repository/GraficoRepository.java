package br.gov.sp.cps.api.pixel.core.domain.repository;

import java.util.List;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.VisualizarGraficoCommand;

public interface GraficoRepository {
    List<?> getGrafico(VisualizarGraficoCommand command);
}


