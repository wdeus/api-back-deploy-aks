package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.ProcessoSeletivo;

import java.util.List;

public interface ProcessoSeletivoRepository {
    List<ProcessoSeletivo> salvar(List<ProcessoSeletivo> processosSeletivos);
}
