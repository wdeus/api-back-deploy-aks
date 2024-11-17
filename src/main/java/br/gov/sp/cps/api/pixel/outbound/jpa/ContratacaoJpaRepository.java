package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.Contratacao;
import br.gov.sp.cps.api.pixel.core.domain.repository.ContratacaoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratacaoJpaRepository extends JpaRepository<Contratacao, Integer>, ContratacaoRepository {

    default List<Contratacao> salvar(List<Contratacao> contratacoes) {
        return saveAll(contratacoes);
    }
}
