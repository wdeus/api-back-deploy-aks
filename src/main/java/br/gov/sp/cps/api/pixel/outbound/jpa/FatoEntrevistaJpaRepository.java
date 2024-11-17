package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.*;
import br.gov.sp.cps.api.pixel.core.domain.repository.FatoEntrevistaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FatoEntrevistaJpaRepository extends JpaRepository<FatoEntrevista, Integer>, FatoEntrevistaRepository {

    default List<FatoEntrevista> salvar(List<FatoEntrevista> fatoEntrevistas) {
        return saveAll(fatoEntrevistas);
    }

    default void popularEntidades(List<FatoEntrevista> fatoEntrevistas, List<Entrevista> entrevistas, List<Vaga> vagas,
                                  List<Feedback> feedbacks, List<AcaoSeletiva> acaoSeletivas,
                                  List<ParticipanteRH> participantesRH, List<Contratacao> contratacoes){
        for(int i=0; i<fatoEntrevistas.size();i++){
            FatoEntrevista fato = fatoEntrevistas.get(i);
            fato.setEntrevista(entrevistas.get(i));
            fato.setVaga(vagas.get(i));
            fato.setFeedback(feedbacks.get(i));
            fato.setAcaoSeletiva(acaoSeletivas.get(i));
            fato.setParticipanteRh(participantesRH.get(i));
            fato.setContratacao(contratacoes.get(i));
        }
    }
}
