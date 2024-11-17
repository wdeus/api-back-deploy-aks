package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.VisualizarCardCommand;

import java.util.List;

public interface CardRepository {
    List<?> getCard(VisualizarCardCommand command);
}
