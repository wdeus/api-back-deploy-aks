package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.Vaga;
import br.gov.sp.cps.api.pixel.core.domain.repository.VagaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VagaJpaRepository extends JpaRepository<Vaga, Integer>, VagaRepository {

    default List<Vaga> salvar(List<Vaga> vagas){
        return saveAll(vagas);
    }
}
