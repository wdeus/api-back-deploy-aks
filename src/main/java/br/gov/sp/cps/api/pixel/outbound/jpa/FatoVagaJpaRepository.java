package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.FatoVaga;
import br.gov.sp.cps.api.pixel.core.domain.entity.Periodo;
import br.gov.sp.cps.api.pixel.core.domain.entity.ProcessoSeletivo;
import br.gov.sp.cps.api.pixel.core.domain.entity.Vaga;
import br.gov.sp.cps.api.pixel.core.domain.repository.FatoVagaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FatoVagaJpaRepository extends JpaRepository<FatoVaga, Integer>, FatoVagaRepository {

    default List<FatoVaga> salvar(List<FatoVaga> fatoVagas) {
        return saveAll(fatoVagas);
    }

    default void popularEntidades(List<FatoVaga> fatoVagas, List<ProcessoSeletivo> processoSeletivos, List<Periodo> periodos,
                                  List<Vaga> vagas){
        for(int i=0; i<fatoVagas.size();i++){
            FatoVaga fato = fatoVagas.get(i);
            fato.setProcessoSeletivo(processoSeletivos.get(i));
            fato.setPeriodo(periodos.get(i));
            fato.setVaga(vagas.get(i));
        }
    }
}
