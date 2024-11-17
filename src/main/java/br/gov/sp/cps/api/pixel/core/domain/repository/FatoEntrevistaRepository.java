package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.*;

import java.util.List;

public interface FatoEntrevistaRepository {
    List<FatoEntrevista> salvar(List<FatoEntrevista> fatoEntrevistas);

    void popularEntidades(List<FatoEntrevista> fatoEntrevistas, List<Entrevista> entrevistas, List<Vaga> vagas,
                          List<Feedback> feedbacks, List<AcaoSeletiva> acaoSeletivas, List<ParticipanteRH> participantesRH,
                          List<Contratacao> contratacoes);
}
