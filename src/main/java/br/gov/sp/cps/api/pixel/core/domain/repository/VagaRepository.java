package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.Vaga;

import java.util.List;

public interface VagaRepository {
    List<Vaga> salvar(List<Vaga> vaga);
}
