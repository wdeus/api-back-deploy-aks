package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.Entrevista;

import java.util.List;

public interface EntrevistaRepository {
    List<Entrevista> salvar(List<Entrevista> entrevistas);
}
