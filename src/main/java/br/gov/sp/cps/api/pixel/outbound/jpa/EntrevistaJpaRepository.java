package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.Entrevista;
import br.gov.sp.cps.api.pixel.core.domain.repository.EntrevistaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntrevistaJpaRepository extends JpaRepository<Entrevista, Integer>, EntrevistaRepository {

    default List<Entrevista> salvar(List<Entrevista> entrevistas) {
        return saveAll(entrevistas);
    }
}
