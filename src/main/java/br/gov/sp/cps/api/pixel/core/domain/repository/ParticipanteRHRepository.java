package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.ParticipanteRH;

import java.util.List;

public interface ParticipanteRHRepository {
    List<ParticipanteRH> salvar(List<ParticipanteRH> participantesRH);
}
