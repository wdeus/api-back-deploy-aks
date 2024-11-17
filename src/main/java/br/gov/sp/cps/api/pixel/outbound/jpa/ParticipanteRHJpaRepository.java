package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.ParticipanteRH;
import br.gov.sp.cps.api.pixel.core.domain.repository.ParticipanteRHRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipanteRHJpaRepository extends JpaRepository<ParticipanteRH, Integer>, ParticipanteRHRepository {

    default List<ParticipanteRH> salvar(List<ParticipanteRH> participantesRH) {
        return saveAll(participantesRH);
    }
}
