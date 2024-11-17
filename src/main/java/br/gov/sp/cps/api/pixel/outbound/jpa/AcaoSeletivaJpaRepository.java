package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.AcaoSeletiva;
import br.gov.sp.cps.api.pixel.core.domain.repository.AcaoSeletivaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcaoSeletivaJpaRepository extends JpaRepository<AcaoSeletiva, Integer>, AcaoSeletivaRepository {

    default List<AcaoSeletiva> salvar(List<AcaoSeletiva> acoesSeletivas) {
        return saveAll(acoesSeletivas);
    }
}
