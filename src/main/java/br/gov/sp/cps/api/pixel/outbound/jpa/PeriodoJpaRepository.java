package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.Periodo;
import br.gov.sp.cps.api.pixel.core.domain.repository.PeriodoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeriodoJpaRepository extends JpaRepository<Periodo, Integer>, PeriodoRepository {

    default List<Periodo> salvar(List<Periodo> periodos) {
        return saveAll(periodos);
    }
}
