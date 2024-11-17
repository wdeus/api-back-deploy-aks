package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.AcaoSeletiva;

import java.util.List;

public interface AcaoSeletivaRepository {
    List<AcaoSeletiva> salvar(List<AcaoSeletiva> acoesSeletivas);
}
