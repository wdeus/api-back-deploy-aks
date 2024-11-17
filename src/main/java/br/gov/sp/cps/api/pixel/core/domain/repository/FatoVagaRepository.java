package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.FatoVaga;
import br.gov.sp.cps.api.pixel.core.domain.entity.Periodo;
import br.gov.sp.cps.api.pixel.core.domain.entity.ProcessoSeletivo;
import br.gov.sp.cps.api.pixel.core.domain.entity.Vaga;

import java.util.List;

public interface FatoVagaRepository {
    List<FatoVaga> salvar(List<FatoVaga> fatoVagas);

    void popularEntidades(List<FatoVaga> fatoVagas, List<ProcessoSeletivo> processoSeletivos,
                          List<Periodo> periodos, List<Vaga> vagas);
}
